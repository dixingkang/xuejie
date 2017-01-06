package com.dafen.xuejie.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by 94239 on 2016/12/12.
 */

public class MyList extends ListView {

    public MyList(Context context) {
        super(context);
    }
    public MyList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
