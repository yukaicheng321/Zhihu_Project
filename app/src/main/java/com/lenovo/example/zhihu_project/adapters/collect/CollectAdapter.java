package com.lenovo.example.zhihu_project.adapters.collect;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;
import com.lenovo.example.zhihu_project.greendao.CollectBean;

import java.util.List;

/**
 * Created by lenovo on 2019/9/11.
 */

public class CollectAdapter extends BaseAdapter {
    public CollectAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_collect_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        CollectBean collectBean = (CollectBean) mDatas.get(positon);

        TextView tv_title = (TextView) holder.getView(R.id.tv_collect_item);
        TextView tv_where = (TextView) holder.getView(R.id.tv_where_collect);
        ImageView iv = (ImageView) holder.getView(R.id.iv_collect_item);
        tv_title.setText(collectBean.getContent());
        tv_where.setText(collectBean.getWhere());
        Glide.with(mContext).load(collectBean.getImg()).into(iv);


    }


}
