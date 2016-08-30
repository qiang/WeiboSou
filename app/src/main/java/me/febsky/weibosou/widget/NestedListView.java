package me.febsky.weibosou.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Author: liuqiang
 * Date: 2016-08-29
 * Time: 18:28
 * 嵌套在ScrollView里面的ListView
 */
public class NestedListView extends ListView {
    public NestedListView(Context context) {
        this(context, null);
    }

    public NestedListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
