package com.lenovo.example.zhihu_project.adapters.ganhuo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;
import com.lenovo.example.zhihu_project.model.bean.GankSearchItemBean;

import java.util.List;

/**
 * Created by lenovo on 2019/9/12.
 */

public class GanhuoSearch extends BaseAdapter {
    public GanhuoSearch(List mDatas) {
        super(mDatas);
    }
    private String type;
    public GanhuoSearch(List mDatas, String type) {
        super(mDatas);
        this.type = type;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_ganhuo_android_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        GankSearchItemBean gankSearchItemBean = (GankSearchItemBean) mDatas.get(positon);

        TextView tv_content = (TextView) holder.getView(R.id.tv_android_content);
        TextView tv_time = (TextView) holder.getView(R.id.tv_android_time);
        TextView tv_author = (TextView) holder.getView(R.id.tv_andoid_author);
        ImageView iv_android = (ImageView) holder.getView(R.id.iv_android);
        if (type.equals("Android")) {
            iv_android.setImageResource(R.mipmap.ic_android);

        }
        if (type.equals("iOS")) {
            iv_android.setImageResource(R.mipmap.ic_ios);
        }
        if (type.equals("前端")) {
            iv_android.setImageResource(R.mipmap.ic_web);
        }
        tv_author.setText(gankSearchItemBean.getWho());
        tv_content.setText(gankSearchItemBean.getDesc());
        tv_time.setText(gankSearchItemBean.getPublishedAt());

    }


}
