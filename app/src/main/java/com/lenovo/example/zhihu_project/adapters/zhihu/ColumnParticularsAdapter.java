package com.lenovo.example.zhihu_project.adapters.zhihu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;
import com.lenovo.example.zhihu_project.model.bean.SectionChildListBean;

import java.util.List;

/**
 * Created by lenovo on 2019/9/6.
 */

public class ColumnParticularsAdapter extends BaseAdapter {
    public ColumnParticularsAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_zhihu_hot_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        SectionChildListBean.StoriesBean storiesBean = (SectionChildListBean.StoriesBean) mDatas
                .get(positon);
        ImageView iv = (ImageView) holder.getView(R.id.iv_hot_item);
        Glide.with(mContext).load(storiesBean.getImages().get(0)).into(iv);
        TextView tv = (TextView) holder.getView(R.id.tv_hot_item);
        tv.setText(storiesBean.getTitle());
    }


}
