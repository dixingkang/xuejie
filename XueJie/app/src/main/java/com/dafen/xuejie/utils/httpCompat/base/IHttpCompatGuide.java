package com.dafen.xuejie.utils.httpCompat.base;

/**
 * HTTP兼容引导类，自定义兼容第三方网络框架时使用
 * Created by _Ms on 2016/12/12.
 */
public interface IHttpCompatGuide {

    /**
     * 创建GetRequest
     * @param url    url
     * @return request
     */
    IHttpRequest createGetRequest(String url);

    /**
     * 创建HeadRequest
     * @param url    url
     * @return request
     */
    IHttpRequest createHeadRequest(String url);

    /**
     * 创建PostRequest
     * @param url    url
     * @return request
     */
    IHttpBodyRequest createPostRequest(String url);

    /**
     * 创建PutRequest
     * @param url    url
     * @return request
     */
    IHttpBodyRequest createPutRequest(String url);

    /**
     * 创建OptionsRequest
     * @param url    url
     * @return request
     */
    IHttpBodyRequest createOptionsRequest(String url);

    /**
     * 创建DeleteRequest
     * @param url    url
     * @return request
     */
    IHttpBodyRequest createDeleteRequest(String url);
}
