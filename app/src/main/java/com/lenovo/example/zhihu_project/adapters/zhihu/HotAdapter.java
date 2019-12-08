package com.lenovo.example.zhihu_project.adapters.zhihu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;
import com.lenovo.example.zhihu_project.model.bean.HotListBean;

import java.util.List;

/**
 * Created by lenovo on 2019/9/5.
 */

public class HotAdapter extends BaseAdapter {
    public HotAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_zhihu_hot_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        HotListBean.RecentBean recentBean = (HotListBean.RecentBean) mDatas.get(positon);
        ImageView iv_hot = (ImageView) holder.getView(R.id.iv_hot_item);
        Glide.with(mContext).load(recentBean.getThumbnail()).into(iv_hot);

        TextView tv_hot = (TextView) holder.getView(R.id.tv_hot_item);
        tv_hot.setText(recentBean.getTitle());
    }


}
