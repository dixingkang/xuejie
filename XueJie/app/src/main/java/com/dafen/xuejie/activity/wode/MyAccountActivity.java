package com.dafen.xuejie.activity.wode;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dafen.xuejie.R;
import com.dafen.xuejie.activity.MainApplication;
import com.dafen.xuejie.base.BaseActivity;
import com.dafen.xuejie.bean.AccountBean;
import com.dafen.xuejie.bean.RegisterBean;
import com.dafen.xuejie.gosn.MultipartRequest;
import com.dafen.xuejie.utils.ContentValues;
import com.dafen.xuejie.utils.ToastUtils;
import com.dafen.xuejie.utils.httpCompat.Http;
import com.dafen.xuejie.utils.httpCompat.base.BaseHttpCallBack;
import com.dafen.xuejie.utils.httpCompat.plugin.DialogTipCallBackPlugin;
import com.dafen.xuejie.utils.httpCompat.plugin.ExceptionToastCallBackPlugin;
import com.dafen.xuejie.utils.httpCompat.response_parser.GsonResponseParser;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//账户信息
public class MyAccountActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout rl_back;
    private ImageView iv_icon; //头像
    private TextView tv_name, tv_tel, tv_qq, tv_weChat;
    private EditText et_password;
    private Button but_quit;
    //相机
    private Button btn_picture, btn_photo, btn_cancle;
    private Bitmap head;// 头像Bitmap

    /**
     * 请求码
     */
    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESULT_REQUEST_CODE = 2;
    private File file;
    private File filea;
    private String image, key = "face", imagename;
    private String CachePath = MainApplication.getCachePath() + "/photos/";
    private String userId;
    @SuppressLint("SdCardPath")
    private static String path = "/sdcard/myHead/";// sd路径

    @Override
    public int getLayoutResId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 相机
        return R.layout.activity_my_account;
    }

    @Override
    public void initView() {
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        iv_icon = (ImageView) findViewById(R.id.iv_icon); // 头像
        et_password = (EditText) findViewById(R.id.et_password);
        but_quit = (Button) findViewById(R.id.but_quit);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_tel = (TextView) findViewById(R.id.tv_tel);
        tv_qq = (TextView) findViewById(R.id.tv_qq);
        tv_weChat = (TextView) findViewById(R.id.tv_weChat);

        getNetWork();
    }

    @Override
    public void initListener() {
        iv_icon.setOnClickListener(this);//头像
        rl_back.setOnClickListener(this);
        but_quit.setOnClickListener(this);
//        //修改头像
//        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");// 从Sd中找头像，转换成Bitmap
//        if (bt != null) {
//            @SuppressWarnings("deprecation")
//            Drawable drawable = new BitmapDrawable(toRoundBitmap(bt));// 转换成drawable
//            iv_icon.setImageDrawable(drawable);
//        } else {
//            /**
//             * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
//             *
//             */
//        }
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
//        showDialog();
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.but_quit:
                LogOff();
                break;
            case R.id.iv_icon:
                showPopwindow();
                break;
        }
    }

    public void getNetWork() {
         userId = context.getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, "");
        Http
                .post(ContentValues.BASE_COMMON_USERINFO)
                .addParams("uid", userId)
                .excute(
                        new GsonResponseParser(AccountBean.class),
                        new BaseHttpCallBack<AccountBean>() {
                            @Override
                            public void onSucess(AccountBean result) {
                                super.onSucess(result);
                                if ("success".equals(result.getStatus())) {
                                    tv_name.setText(result.getValue().getUser_nicename());
                                    tv_tel.setText(result.getValue().getUser_login());
                                }
//                                ToastUtils.showLong(result.getExplain());

                            }

                        }.addPlugin(new ExceptionToastCallBackPlugin(context))
                                .addPlugin(new DialogTipCallBackPlugin(mActivity))
                );


    }

    // 退出登录
    private void LogOff() {

        Boolean LoginState = context.getSharedPreferences(ContentValues.SP_NAME, 0).getBoolean(ContentValues.IF_IS_LOGINED, false);
        if (LoginState) {
            AlertDialog.Builder diagLog = new AlertDialog.Builder(this
            );
            diagLog.setTitle("确定安全退出？");
            diagLog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    /*
                    退出成功后将记录退出状态
                     */
            SharedPreferences.Editor spEdit = sharedPreferences.edit();
            // 取消登录状态
            spEdit.putBoolean(ContentValues.IF_IS_LOGINED, false);
            //关闭用户id
            spEdit.putString(ContentValues.USER_ID, null);


            spEdit.apply();

                    dialog.dismiss();
            finish();
        }
            });
            diagLog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                }
            });
            diagLog.create().show();
        } else {
        ToastUtils.showText("您已安全退出，请重新登录！");
        }


    }
    //////////////////////////////////////////////////////
    /**
     * Time: 2016/7/21 0021 下午 4:33
     (
     * param: ${tags}
     * return: ${return_type}
     */
    private void showPopwindow() {

//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        //强制隐藏键盘
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(this, R.layout.pop_take_photo, null);

        Button btnCamera = (Button) popView.findViewById(R.id.pop_paizhao);
        Button btnAlbum = (Button) popView.findViewById(R.id.pop_tuku);
        Button btnCancel = (Button) popView.findViewById(R.id.pop_cancel);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        final PopupWindow popWindow = new PopupWindow(popView, width, height * 2 / 5);
        popWindow.setAnimationStyle(R.style.take_photo_anim);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(false);// 设置允许在外点击消失
        popWindow.setBackgroundDrawable(getDrawable(R.color.bai));
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {

                popWindow.dismiss();
                switch (v.getId()) {
                    case R.id.pop_paizhao:
                        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // 判断存储卡是否可以用，可用进行存储
                        String state = Environment.getExternalStorageState();
                        if (state.equals(Environment.MEDIA_MOUNTED)) {
                            file = new File(CachePath);
                            if (!file.exists())
                                file.mkdirs();
                            filea = new File(CachePath, getPhotoNmae());
                            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filea));
                        } else {
                            ToastUtils.showShort("未找到存储卡，无法存储照片");
                        }
                        startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
                        break;
                    case R.id.pop_tuku:

                        Intent intentFromGallery = new Intent();
                        intentFromGallery.setType("image/*"); // 设置文件类型
                        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intentFromGallery, IMAGE_REQUEST_CODE);
                        break;
                    case R.id.pop_cancel:

                        break;
                }
            }
        };

        btnCamera.setOnClickListener(listener);
        btnAlbum.setOnClickListener(listener);
        btnCancel.setOnClickListener(listener);

        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        // 结果码不等于取消时候
        if (resultCode != this.RESULT_CANCELED) {
            switch (requestCode) {
                case CAMERA_REQUEST_CODE:
                    // 判断存储卡是否可以用，可用进行存储
                    String state = Environment.getExternalStorageState();
                    if (state.equals(Environment.MEDIA_MOUNTED)) {
                        startPhotoZoom(Uri.fromFile(filea));
                    } else {
                        ToastUtils.showShort( "未找到存储卡，无法存储照片！");
                    }
                    break;
                case IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                case RESULT_REQUEST_CODE: // 图片缩放完成后
                    ToastUtils.showShort( "图片缩放完成后");
                    if (data != null) {
                        imagename = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
                        keepImage(data, CachePath);
                        // 开始上传图片
                        String url = ContentValues.BASE_COMMON_UPLOAD;
                        MultipartRequest request = new MultipartRequest(url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Gson gson = new Gson();
//                                ReturnCodeBean returnCodeBean = gson.fromJson(response, ReturnCodeBean.class);
//                                ToastUtil.showTextToast(returnCodeBean.getMessage());
//                                if (returnCodeBean.getCode() == 200) {
                                    sharedPreferences.edit().putBoolean(ContentValues.HAS_FACE, true).apply();
                                    System.out.println("上传图片路径：" + image);
                                    Picasso
                                            .with(context)
                                            .load(new File(image))
                                            .error(R.drawable.ic_error_page)
                                            .into(iv_icon);
//                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                ToastUtils.showShort("网络请求失败");
                            }
                        });
                        request.addParam("userId", userId);
                        request.addFile("head", image);
                        MainApplication.getInstance().getRequestQueue().add(request);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 将所得图片写入缓存文件中
     **/
    private void keepImage(Intent data, String path) {
        Bundle bundle = data.getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
        FileOutputStream b = null;
        File newFile = new File(path);
        if (!newFile.exists()) {
            newFile.mkdirs();// 创建目录
        }
        image = path + imagename;
        System.out.println("上传图片路径：" + image);
        try {
            b = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据100%压缩写入文件
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 170);
        intent.putExtra("outputY", 170);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    /**
     * 设置缓存图片名字
     **/
    private String getPhotoNmae() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }
    //修改头像
//    private void showDialog() {
//        View view = getLayoutInflater().inflate(R.layout.photo_choose_dialog, null);
//        final Dialog dialog = new Dialog(this, R.style.transparentFrameWindowStyle);
//        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        Window window = dialog.getWindow();
//        // 设置显示动画
//        window.setWindowAnimations(R.style.main_menu_animstyle);
//        WindowManager.LayoutParams wl = window.getAttributes();
//        wl.x = 0;
//        wl.y = getWindowManager().getDefaultDisplay().getHeight();
//        // 以下这两句是为了保证按钮可以水平满屏
//        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//
//        // 设置显示位置
//        dialog.onWindowAttributesChanged(wl);
//        // 设置点击外围解散
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.show();
//
//        btn_picture = (Button) window.findViewById(R.id.btn_picture);
//        btn_photo = (Button) window.findViewById(R.id.btn_photo);
//        btn_cancle = (Button) window.findViewById(R.id.btn_cancle);
//
//        btn_picture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
//                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//                startActivityForResult(intent1, 1);
//                dialog.dismiss();
//            }
//        });
//        btn_photo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
//                startActivityForResult(intent2, 2);// 采用ForResult打开
//                dialog.dismiss();
//            }
//        });
//        btn_cancle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case 1:
//                if (resultCode == RESULT_OK) {
//                    cropPhoto(data.getData());// 裁剪图片
//                }
//
//                break;
//            case 2:
//                if (resultCode == RESULT_OK) {
//                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
//                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
//                }
//
//                break;
//            case 3:
//                if (data != null) {
//                    Bundle extras = data.getExtras();
//                    head = extras.getParcelable("data");
//                    if (head != null) {
//                        /**
//                         * 上传服务器代码
//                         */
//                        setPicToView(head);// 保存在SD卡中
//                        iv_icon.setImageBitmap(toRoundBitmap(head));// 用ImageView显示出来
//                    }
//                }
//                break;
//            default:
//                break;
//
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    ;

    /**
     * 调用系统的裁剪
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 把bitmap转成圆形
     */
    public Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int r = 0;
        // 取最短边做边长
        if (width < height) {
            r = width;
        } else {
            r = height;
        }
        // 构建一个bitmap
        Bitmap backgroundBm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // new一个Canvas，在backgroundBmp上画图
        Canvas canvas = new Canvas(backgroundBm);
        Paint p = new Paint();
        // 设置边缘光滑，去掉锯齿
        p.setAntiAlias(true);
        RectF rect = new RectF(0, 0, r, r);
        // 通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
        // 且都等于r/2时，画出来的圆角矩形就是圆形
        canvas.drawRoundRect(rect, r / 2, r / 2, p);
        // 设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // canvas将bitmap画在backgroundBmp上
        canvas.drawBitmap(bitmap, null, rect, p);
        return backgroundBm;
    }
}
