package com.lenovo.example.zhihu_project.adapters.zhihu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;
import com.lenovo.example.zhihu_project.model.bean.SectionListBean;

import java.util.List;

/**
 * Created by lenovo on 2019/9/5.
 */

public class ColumnAdapter extends BaseAdapter {

    private LinearLayout view;
    private int mPosition;

    public ColumnAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_zhihu_column_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        SectionListBean.DataBean dataBean = (SectionListBean.DataBean) mDatas.get(positon);
        ImageView iv_column = (ImageView) holder.getView(R.id.iv_column);
        Glide.with(mContext).load(dataBean.getThumbnail()).into(iv_column);
        TextView tvtop = (TextView) holder.getView(R.id.tvtop_column);
        tvtop.setText(dataBean.getDescription());
        TextView tvdown = (TextView) holder.getView(R.id.tvdown_column);
        tvdown.setText(dataBean.getName());


    }


}
