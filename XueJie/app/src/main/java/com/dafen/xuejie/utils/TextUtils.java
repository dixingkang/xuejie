package com.dafen.xuejie.utils;

import java.util.Map;

/**
 * Created by _Ms on 2016/12/12.
 */

public class TextUtils {

    /**
     * 将Map封装的键值对转换为URL参数并拼接
     *
     * @param url          原始URL
     * @param mapParams    包含拼接键值对的MAP
     * @return
     */
    public static String map2httpParams(String url, Map<String, Object> mapParams) {
        CheckUtils.notNull(url);

        StringBuffer sb = new StringBuffer(url);

        if (mapParams != null) {
            if (mapParams.size() != 0) {

                if (sb.indexOf("?") != -1) {
                    // 如果URL中包含参数
                    sb.append("&");
                } else {
                    // 如果不包含参数
                    sb.append("?");
                }

                String params = getUrlParams(mapParams);
                sb.append(params);
            }
        }

        return sb.toString();
    }

    /**
     * 按照URL解析拼接字符串为URL参数
     *
     * @param paramsMap 字符串参数
     * @return 拼接后的URL参数
     */
    private static String getUrlParams(Map<String, Object> paramsMap) {

        if (paramsMap == null) {
            return "";
        }

        StringBuffer sb = null;

        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {

            if (sb == null) {
                sb = new StringBuffer();
            }

            String key = entry.getKey();
            String value = entry.getValue().toString();

            // 按照URL参数规则拼接字符串
            sb.append(String.format("%s=%s&", key, value));
        }

        if (sb == null) {
            return "";
        } else {
            String substring = sb.substring(0, sb.length() - 1);
            return substring;
        }
    }

}
