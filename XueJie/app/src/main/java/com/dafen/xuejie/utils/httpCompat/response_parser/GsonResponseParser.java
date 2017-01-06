package com.dafen.xuejie.utils.httpCompat.response_parser;

import com.dafen.xuejie.utils.CheckUtils;
import com.dafen.xuejie.utils.Debug;
import com.dafen.xuejie.utils.GsonUtil;
import com.dafen.xuejie.utils.StreamUtils;
import com.dafen.xuejie.utils.httpCompat.base.IHttpResponse;
import com.dafen.xuejie.utils.httpCompat.base.IResponseParser;

import java.io.InputStream;

/**
 * GsonResponse解析器
 * Created by _Ms on 2016/12/9.
 */
public class GsonResponseParser<T> implements IResponseParser<T> {

    private Class<T> mBeanClass;

    public GsonResponseParser(Class<T> beanClass) {
        CheckUtils.notNull(beanClass);

        mBeanClass = beanClass;
    }

    @Override
    public T parse(IHttpResponse response) {

         /*
        获取结果json
         */
        InputStream inputStream = response.byteStream();
        String jsonStr = StreamUtils.inputStream2String(inputStream, null);

        Debug.d("HttpResponse, ToString: %s", jsonStr);

        // json2Bean
        return GsonUtil.getInstance().fromJson(jsonStr, mBeanClass);
    }

}
