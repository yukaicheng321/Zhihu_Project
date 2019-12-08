package com.lenovo.example.zhihu_project.fragment.V2exFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.adapters.juejin.TopicAdapter;
import com.lenovo.example.zhihu_project.base.BaseFragment;
import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;
import com.lenovo.example.zhihu_project.component.CommonItemDecoration;
import com.lenovo.example.zhihu_project.constants.Constant;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;
import com.lenovo.example.zhihu_project.interfaces.V2ex.ExContract;
import com.lenovo.example.zhihu_project.model.bean.TopicListBean;
import com.lenovo.example.zhihu_project.presenter.v2em.VtexPersenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class VtexPageFragment extends BaseFragment implements ExContract.VtexView, BaseAdapter.OnItemClickListener {

    @BindView(R.id.view_main)
    RecyclerView rvContent;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private String type;
    private TopicAdapter topicAdapter;
    private List<TopicListBean> list;

    public static VtexPageFragment newInstance(String type){
        VtexPageFragment fragment = new VtexPageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("vtex_type",type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.view_common_list;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        type = bundle.getString("vtex_type");
        list = new ArrayList<>();
        topicAdapter = new TopicAdapter(list);
        CommonItemDecoration mDecoration = new CommonItemDecoration(1, CommonItemDecoration.UNIT_DP);
        rvContent.setLayoutManager(new LinearLayoutManager(context));
        rvContent.setAdapter(topicAdapter);
        rvContent.addItemDecoration(mDecoration);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ((VtexPersenter)persenter).getContent(type);
            }
        });
        ((VtexPersenter)persenter).getContent(type);
    }

    @Override
    protected IPersenter createPersenter() {
        return new VtexPersenter();
    }

    @Override
    public void showErrMsg(String err) {

    }

    @Override
    public void getContentReturn(List<TopicListBean> list) {
        this.list.addAll(list);
        topicAdapter.notifyDataSetChanged();
    }

    /**
     * 条目点击
     * @param v
     * @param position
     */
    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent();
        intent.setClass(context, RepliesActivity.class);
        intent.putExtra(Constant.IT_VTEX_TOPIC_ID, list.get(position).getTopicId());
        startActivity(intent);
    }
}
