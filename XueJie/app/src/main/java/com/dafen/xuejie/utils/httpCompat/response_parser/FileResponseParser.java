package com.dafen.xuejie.utils.httpCompat.response_parser;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.dafen.xuejie.constant.GlobalConfig;
import com.dafen.xuejie.utils.Debug;
import com.dafen.xuejie.utils.StreamUtils;
import com.dafen.xuejie.utils.httpCompat.base.IHttpResponse;
import com.dafen.xuejie.utils.httpCompat.base.IResponseParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;



/**
 * FileResponse解析器
 * Created by _Ms on 2016/12/9.
 */
public abstract class FileResponseParser implements IResponseParser<File> {

    private File mTargetFile;

    public FileResponseParser() {this(null);}
    public FileResponseParser(String targetName) {this(null, targetName);}
    public FileResponseParser(String targetDir, String targetName) {

        if (TextUtils.isEmpty(targetDir)) {
            targetDir = GlobalConfig.DIR_DOWNLOAD;
        }

        if (TextUtils.isEmpty(targetName)) {
            targetName = UUID.randomUUID().toString();
        }

        /*
        文件夹权限校验
         */
        File targetFileDir = new File(targetDir);
        if (targetFileDir.exists()) {
            if (targetFileDir.isFile()) {
                throw new IllegalArgumentException("目标文件夹以文件实例存在");
            }
        } else {
            if (!targetFileDir.mkdirs()) {
                throw new IllegalStateException("目标文件夹无读写权限");
            }
        }

        mTargetFile = new File(targetDir, targetName);
    }

    @Override
    public File parse(IHttpResponse response) {

        saveFile(response);

        return mTargetFile;
    }

    /**
     * 将流保存到文件
     * @param response
     */
    private void saveFile(IHttpResponse response) {

        InputStream inputStream = response.byteStream();

        long totalProgress;
        try {
            String length = response.header().get("Content-Length");
            totalProgress = Long.parseLong(length);
        } catch (Exception e) {
            Debug.e(e);
            totalProgress = -1;
        }

        long currentProgress = 0;

        FileOutputStream os = null;

        try {

            byte[] buffer = new byte[4096];
            int len;

            os = new FileOutputStream(mTargetFile);

            while ( (len = inputStream.read(buffer)) != -1 ) {

                os.write(buffer, 0, len);

                currentProgress += len;

                float progress = currentProgress * 1f / totalProgress;

                Message msg = Message.obtain();
                msg.what = HANDLER_UPDATE_PROGRESS;

                msg.obj = new DownloadProgress(currentProgress, totalProgress, progress);

                mHandler.dispatchMessage(msg);

            }

        } catch (IOException e) {
            Debug.e(e);
        } finally {
            StreamUtils.closeStream(os);
        }
    }

    private class DownloadProgress {
        public long currentSize;
        public long totalSize;
        public float progress;

        public DownloadProgress(long currentProgress, long totalSize, float progress) {
            this.currentSize = currentProgress;
            this.totalSize = totalSize;
            this.progress = progress;
        }
    }

    /**
     * 更新进度时的回调
     * @param currentSize 当前大小
     * @param totalSize   总大小
     * @param progress    百分比
     */
    protected abstract void onProgress(long currentSize, long totalSize, float progress);

    /**
     * 更新进度
     */
    private static final int HANDLER_UPDATE_PROGRESS = 0;

    /**
     * Handler
     */
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_UPDATE_PROGRESS:

                    DownloadProgress progress = (DownloadProgress) msg.obj;
                    onProgress(progress.currentSize, progress.totalSize, progress.progress);

                    break;
            }
        }
    };

}
