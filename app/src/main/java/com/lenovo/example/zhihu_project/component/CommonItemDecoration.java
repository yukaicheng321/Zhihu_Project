package com.lenovo.example.zhihu_project.component;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lenovo.example.zhihu_project.utils.SystemUtils;


/**
 * Created by codeest on 16/12/24.
 */

public class CommonItemDecoration extends RecyclerView.ItemDecoration{

    private int distance;
    private int unit;

    public static final int UNIT_DP = 0;
    public static final int UNIT_PX = 1;

    public CommonItemDecoration(int distance, int unit) {
        this.distance = distance;
        this.unit = unit;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (position > -1) {
            if (unit == UNIT_DP) {
                outRect.set(0, SystemUtils.dp2px(distance), 0, 0);
            } else {
                outRect.set(0, distance, 0, 0);
            }
        }
    }
}
