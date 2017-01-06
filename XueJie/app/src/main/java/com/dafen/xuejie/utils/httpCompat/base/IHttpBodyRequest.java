package com.dafen.xuejie.utils.httpCompat.base;

import java.io.File;

/**
 * Created by _Ms on 2016/12/25.
 */

public interface IHttpBodyRequest extends IHttpRequest<IHttpBodyRequest> {

    /**
     * 添加文件参数
     * @param key     key
     * @param file    file
     */
    IHttpBodyRequest addParams(String key, File file);
}
