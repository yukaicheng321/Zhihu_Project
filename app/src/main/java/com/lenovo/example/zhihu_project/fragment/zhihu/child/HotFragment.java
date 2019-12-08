package com.lenovo.example.zhihu_project.fragment.zhihu.child;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.adapters.zhihu.HotAdapter;
import com.lenovo.example.zhihu_project.base.BaseFragment;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;
import com.lenovo.example.zhihu_project.interfaces.zhihu.ZhihuContract;
import com.lenovo.example.zhihu_project.model.bean.HotListBean;
import com.lenovo.example.zhihu_project.presenter.zhihu.HotPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2019/9/5.
 */

public class HotFragment extends BaseFragment implements ZhihuContract.View {
    @BindView(R.id.recyc_hot)
    RecyclerView recycHot;
    Unbinder unbinder;

    @Override
    public void showErrMsg(String err) {

    }

    @Override
    public <T> void getHttpResult(T t) {
        HotListBean t1 = (HotListBean) t;
        List<HotListBean.RecentBean> recent = t1.getRecent();
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recycHot.setLayoutManager(manager);
        HotAdapter hotAdapter = new HotAdapter(recent);
        recycHot.setAdapter(hotAdapter);

    }

    @Override
    protected int getLayout() {
        return R.layout.layout_zhihu_hot;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        ((HotPresenter) persenter).getHttp();
    }

    @Override
    protected IPersenter createPersenter() {
        return new HotPresenter();
    }


}
