package com.lenovo.example.zhihu_project.fragment.ganhuo;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.adapters.ganhuo.GanhuoGrilAdapter;
import com.lenovo.example.zhihu_project.base.BaseFragment;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;
import com.lenovo.example.zhihu_project.interfaces.ganhuo.GanHuoContract;
import com.lenovo.example.zhihu_project.model.bean.GankHttpResponse;
import com.lenovo.example.zhihu_project.model.bean.GankItemBean;
import com.lenovo.example.zhihu_project.model.bean.GankSearchItemBean;
import com.lenovo.example.zhihu_project.presenter.ganhuo.GanhuoPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2019/9/9.
 */

public class FuliFragment extends BaseFragment implements GanHuoContract.View {
    @BindView(R.id.recyc_ganhuo_fuli)
    RecyclerView recycGanhuoFuli;
    Unbinder unbinder;

    @Override
    public void showErrMsg(String err) {

    }

    @Override
    protected int getLayout() {
        return R.layout.layout_ganhuo_fuli;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        ((GanhuoPresenter) persenter).gethttpGrils(20, 1);
    }

    @Override
    protected IPersenter createPersenter() {
        return new GanhuoPresenter();
    }

    @Override
    public void getHttpResult(GankHttpResponse<List<GankItemBean>> listGankHttpResponse) {
        List<GankItemBean> results = listGankHttpResponse.getResults();
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        recycGanhuoFuli.setLayoutManager(manager);
        GanhuoGrilAdapter ganhuoGrilAdapter = new GanhuoGrilAdapter(results);
        recycGanhuoFuli.setAdapter(ganhuoGrilAdapter);

    }

    @Override
    public void getSearchResult(GankHttpResponse<List<GankSearchItemBean>> listGankHttpResponse) {

    }

}
