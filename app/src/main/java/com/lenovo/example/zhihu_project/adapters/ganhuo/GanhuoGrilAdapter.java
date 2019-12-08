package com.lenovo.example.zhihu_project.adapters.ganhuo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;
import com.lenovo.example.zhihu_project.model.bean.GankItemBean;

import java.util.List;

/**
 * Created by lenovo on 2019/9/9.
 */

public class GanhuoGrilAdapter extends BaseAdapter {
    public GanhuoGrilAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_ganhuo_fuli_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        GankItemBean gankItemBean = (GankItemBean) mDatas.get(positon);
        ImageView iv = (ImageView) holder.getView(R.id.iv_ganhuo_fuli);
        Glide.with(mContext).load(gankItemBean.getUrl()).into(iv);


    }


}
