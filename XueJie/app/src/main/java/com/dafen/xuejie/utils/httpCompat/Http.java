package com.dafen.xuejie.utils.httpCompat;


import com.dafen.xuejie.utils.CheckUtils;
import com.dafen.xuejie.utils.httpCompat.base.IHttpBodyRequest;
import com.dafen.xuejie.utils.httpCompat.base.IHttpCompatGuide;
import com.dafen.xuejie.utils.httpCompat.base.IHttpRequest;

/**
 * Http请求帮助类
 * Created by _Ms on 2016/10/11.
 */
public class Http {

    /**
     * 兼容HTTP引导类
     */
    private static IHttpCompatGuide sHttpCompatGuide;

    /**
     * 初始化设置兼容Http引导类，此方法必须被调用一次
     *
     * @param guide
     */
    public static void init(IHttpCompatGuide guide) {
        CheckUtils.notNull(guide);

        sHttpCompatGuide = guide;
    }

    /**
     * GET请求
     *
     * @param url url
     * @return 请求实例
     */
    public static IHttpRequest get(String url) {
        checkHttpCompatGuide();
        return sHttpCompatGuide.createGetRequest(url);
    }

    /**
     * HEAD请求
     *
     * @param url url
     * @return
     */
    public static IHttpRequest head(String url) {
        checkHttpCompatGuide();
        return sHttpCompatGuide.createHeadRequest(url);
    }

    /**
     * GET请求
     *
     * @param url url
     * @return 请求实例
     */
    public static IHttpBodyRequest post(String url) {
        checkHttpCompatGuide();
        return sHttpCompatGuide.createPostRequest(url);
    }

    /**
     * PUT请求
     *
     * @param url url
     * @return 请求实例
     */
    public static IHttpBodyRequest put(String url) {
        checkHttpCompatGuide();
        return sHttpCompatGuide.createPutRequest(url);
    }

    /**
     * OPTIONS请求
     *
     * @param url url
     * @return
     */
    public static IHttpBodyRequest options(String url) {
        checkHttpCompatGuide();
        return sHttpCompatGuide.createOptionsRequest(url);
    }

    /**
     * DELETE请求
     *
     * @param url url
     * @return
     */
    public static IHttpBodyRequest delete(String url) {
        checkHttpCompatGuide();
        return sHttpCompatGuide.createDeleteRequest(url);
    }

    private static void checkHttpCompatGuide() {
        if (sHttpCompatGuide == null) {
            throw new IllegalStateException("尚未初始化HttpCompat, 请在程序入口处调用 Http.init() 方法");
        }
    }
}
