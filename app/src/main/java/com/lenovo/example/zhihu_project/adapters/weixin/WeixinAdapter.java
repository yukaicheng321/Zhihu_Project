package com.lenovo.example.zhihu_project.adapters.weixin;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;
import com.lenovo.example.zhihu_project.model.bean.WXItemBean;

import java.util.List;

/**
 * Created by lenovo on 2019/9/8.
 */

public class WeixinAdapter extends BaseAdapter {
    public WeixinAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_weixin_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        WXItemBean wxItemBean = (WXItemBean) mDatas.get(positon);
        ImageView iv = (ImageView) holder.getView(R.id.iv_weixin_item);
        Glide.with(mContext).load(wxItemBean.getPicUrl()).into(iv);
        TextView tv_title = (TextView) holder.getView(R.id.tv_weixin_title);
        TextView tv_description = (TextView) holder.getView(R.id.tv_weixin_description);
        TextView tv_time = (TextView) holder.getView(R.id.tv_weixin_time);
        tv_description.setText(wxItemBean.getDescription());
        tv_title.setText(wxItemBean.getTitle());
        tv_time.setText(wxItemBean.getCtime());

    }

}
