package com.lenovo.example.zhihu_project.adapters.v2ex;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;

import java.util.List;

/**
 * Created by lenovo on 2019/9/11.
 */

public class NodeListAdapter extends BaseAdapter {
    public NodeListAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {

    }


}
