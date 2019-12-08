package com.lenovo.example.zhihu_project.adapters.zhihu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;
import com.lenovo.example.zhihu_project.model.bean.DailyListBean;

import java.util.List;

/**
 * Created by lenovo on 2019/9/5.
 */

public class RibaoAdapter extends BaseAdapter {
    public RibaoAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_zhihu_ribao_item;
    }

    @Override
    protected void bindData(BaseAdapter.BaseViewHolder holder, int positon, Object o) {
        DailyListBean.StoriesBean storiesBean = (DailyListBean.StoriesBean) mDatas.get(positon);

        ImageView iv_ribao = (ImageView) holder.getView(R.id.iv_ribao_item);
        Glide.with(mContext).load(storiesBean.getImages().get(0)).into(iv_ribao);
        TextView tv_ribao = (TextView) holder.getView(R.id.tv_ribao_item);
        tv_ribao.setText(storiesBean.getTitle());

    }


}
