package com.dafen.xuejie.utils.httpCompat.base;

import java.util.List;
import java.util.Map;

/**
 * Http第三方框架二次封装·Header转换接口
 * Created by _Ms on 2016/12/24.
 */
public interface IHttpHeader {

    /** Returns the last value corresponding to the specified field, or null. */
    String get(String name);

    /** Returns the number of field values. */
    int size();

    /** Returns the field at {@code position}. */
    String name(int index);

    /** Returns the value at {@code index}. */
    String value(int index);

    Map<String, List<String>> toMultimap();
}
