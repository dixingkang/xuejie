package com.dafen.xuejie.base;

/**
 * Created by _Ms on 2016/10/22.
 */

public interface IBaseHolderDoIt<RETURN, DATA> {
    RETURN doIt(int flag, DATA data);
}
