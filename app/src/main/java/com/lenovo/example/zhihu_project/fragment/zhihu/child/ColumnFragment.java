package com.lenovo.example.zhihu_project.fragment.zhihu.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.adapters.zhihu.ColumnAdapter;
import com.lenovo.example.zhihu_project.base.BaseFragment;
import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;
import com.lenovo.example.zhihu_project.interfaces.zhihu.ZhihuContract;
import com.lenovo.example.zhihu_project.model.bean.SectionListBean;
import com.lenovo.example.zhihu_project.presenter.zhihu.ColumnPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2019/9/5.
 */

public class ColumnFragment extends BaseFragment implements ZhihuContract.View, BaseAdapter
        .OnItemClickListener {
    @BindView(R.id.recyc_column)
    RecyclerView recycColumn;
    Unbinder unbinder;
    private ColumnAdapter columnAdapter;
    private List<SectionListBean.DataBean> data;

    @Override
    public void showErrMsg(String err) {

    }

    @Override
    public <T> void getHttpResult(T t) {
        SectionListBean t1 = (SectionListBean) t;
        data = t1.getData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recycColumn.setLayoutManager(gridLayoutManager);
        columnAdapter = new ColumnAdapter(data);
        recycColumn.setAdapter(columnAdapter);
        columnAdapter.setOnItemClickListener(this);

    }

    @Override
    protected int getLayout() {
        return R.layout.layout_zhihu_column;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        ((ColumnPresenter) persenter).getHttp();
    }

    @Override
    protected IPersenter createPersenter() {
        return new ColumnPresenter();
    }


    @Override
    public void onItemClick(View v, int position) {
        Log.i("tag", "onItemClick: " + position);
        int id = data.get(position).getId();
        Intent intent = new Intent(getContext(), ColumnShowActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);


    }
}
