package com.lenovo.example.zhihu_project.fragment.zhihu.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.adapters.zhihu.ColumnParticularsAdapter;
import com.lenovo.example.zhihu_project.base.BaseActivity;
import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;
import com.lenovo.example.zhihu_project.interfaces.zhihu.ColumnParticularsContract;
import com.lenovo.example.zhihu_project.model.bean.SectionChildListBean;
import com.lenovo.example.zhihu_project.presenter.zhihu.ColumnShowPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2019/9/6.
 */

public class ColumnShowActivity extends BaseActivity implements ColumnParticularsContract.View, BaseAdapter.OnItemClickListener {
    @BindView(R.id.recyc_column_particulars)
    RecyclerView recycColumnParticulars;
    private List<SectionChildListBean.StoriesBean> stories;
    private SectionChildListBean t1;

    @Override
    public <T> void getHttpResult(T t) {
        t1 = (SectionChildListBean) t;
        stories = t1.getStories();
        recycColumnParticulars.setLayoutManager(new LinearLayoutManager(this));
        ColumnParticularsAdapter columnParticularsAdapter = new ColumnParticularsAdapter(stories);
        recycColumnParticulars.setAdapter(columnParticularsAdapter);
        columnParticularsAdapter.setOnItemClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_column_show;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        ((ColumnShowPresenter) persenter).getHttp(id);
    }

    @Override
    protected IPersenter initPersenter() {
        return new ColumnShowPresenter();
    }


    @Override
    public void onItemClick(View v, int position) {
        int id = stories.get(position).getId();

        String img = stories.get(position).getImages().get(0);
        String title = stories.get(position).getTitle();
        Log.i("tag", "onItemClick: "+id+"====");
        Intent intent = new Intent(this, ColumnTalkActivity.class);
        String name = t1.getName();
        Log.i("tag", "onItemClick: "+name);
        intent.putExtra("id",id);
        intent.putExtra("img",img);
        intent.putExtra("name",name);
        intent.putExtra("title",title);
        startActivity(intent);
    }
}
