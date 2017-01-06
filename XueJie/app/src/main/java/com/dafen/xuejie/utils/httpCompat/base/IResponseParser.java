package com.dafen.xuejie.utils.httpCompat.base;

/**
 * HttpResponse解析器接口
 * Created by _Ms on 2016/12/9.
 */
public interface IResponseParser<T> {

    /**
     * 解析方法
     * @param response    被解析response
     * @return 解析后结果
     */
    T parse(IHttpResponse response);

    public static class DefaultParser implements IResponseParser<IHttpResponse> {

        @Override
        public IHttpResponse parse(IHttpResponse response) {
            return response;
        }
    }
}
