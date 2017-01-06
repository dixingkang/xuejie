package com.dafen.xuejie.constant;

/**
 * Http网络服务常量类
 * <p/>
 * Created by UserBean on 2016/9/22.
 */
public class HttpConstant {

    /**
     * 服务器协议
     */
    private static final String PROTOCOL = "http";

    /**
     * 服务器服务端口
     */
    private static final String PORT = GlobalConfig.IS_DEBUG ? "8080" : "80";

    /**
     * 服务器项目名称
     */
    private static final String APP = "slrk";

    /**
     * HTTP服务根地址
     */
    public static final String BASE_URL = String.format("%s://%s:%s/%s",
            PROTOCOL, // 协议
            GlobalConfig.SERVER_HOST, // 服务器地址
            PORT, // 协议端口
            APP // 项目名称
    );


    /**
     * 根据接口名称获取Url
     *
     * @param urlInterfaceName 接口名称
     * @return 与服务跟地址拼合后的接口Url
     */
    public static final String getInterfaceUrl(CharSequence urlInterfaceName) {
        return String.format("%s/%s",
                BASE_URL, // 服务根地址
                urlInterfaceName // 接口名称
        );
    }

}
