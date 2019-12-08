package com.lenovo.example.zhihu_project.adapters.zhihu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;

import java.util.List;

/**
 * Created by lenovo on 2019/9/9.
 */

public class ColumnTalkAdapter extends BaseAdapter {
    public ColumnTalkAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }
}
