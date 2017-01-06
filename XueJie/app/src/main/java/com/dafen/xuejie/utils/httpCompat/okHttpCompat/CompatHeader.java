package com.dafen.xuejie.utils.httpCompat.okHttpCompat;


import com.dafen.xuejie.utils.CheckUtils;
import com.dafen.xuejie.utils.httpCompat.base.AbsHttpHeader;

import java.util.List;
import java.util.Map;

import okhttp3.Headers;


/**
 * OkHttp兼容Header
 * Created by _Ms on 2016/12/25.
 */
public class CompatHeader extends AbsHttpHeader {

    private Headers mHeader;

    public CompatHeader(Headers header) {
        CheckUtils.notNull(header);

        mHeader = header;
    }

    @Override
    public String get(String name) {
        return mHeader.get(name);
    }

    @Override
    public int size() {
        return mHeader.size();
    }

    @Override
    public String name(int index) {
        return mHeader.name(index);
    }

    @Override
    public String value(int index) {
        return mHeader.value(index);
    }

    @Override
    public Map<String, List<String>> toMultimap() {
        return mHeader.toMultimap();
    }
}
