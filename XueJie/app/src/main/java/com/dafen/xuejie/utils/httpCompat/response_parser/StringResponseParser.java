package com.dafen.xuejie.utils.httpCompat.response_parser;


import com.dafen.xuejie.utils.StreamUtils;
import com.dafen.xuejie.utils.httpCompat.base.IHttpResponse;
import com.dafen.xuejie.utils.httpCompat.base.IResponseParser;

/**
 * StringResponseParser
 * Created by _Ms on 2016/12/9.
 */
public class StringResponseParser implements IResponseParser<String> {

    @Override
    public String parse(IHttpResponse response) {

        return StreamUtils.inputStream2String(response.byteStream(), null);
    }

}
