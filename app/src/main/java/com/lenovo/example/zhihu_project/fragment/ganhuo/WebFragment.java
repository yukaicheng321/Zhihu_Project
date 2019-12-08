package com.lenovo.example.zhihu_project.fragment.ganhuo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.adapters.ganhuo.GanhuoAndroidAdapter;
import com.lenovo.example.zhihu_project.adapters.ganhuo.GanhuoSearch;
import com.lenovo.example.zhihu_project.base.BaseFragment;
import com.lenovo.example.zhihu_project.constants.Constant;
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

public class WebFragment extends BaseFragment implements GanHuoContract.View{
    @BindView(R.id.iv_tech_blur)
    ImageView ivTechBlur;
    @BindView(R.id.tv_tech_author)
    TextView tvTechAuthor;
    @BindView(R.id.recyc_ganhuo_android)
    RecyclerView recycGanhuoAndroid;
    Unbinder unbinder;
    private List<GankItemBean> results;
    private GanhuoAndroidAdapter ganhuoAndroidAdapter;

    @Override
    public void showErrMsg(String err) {

    }

    @Override
    protected int getLayout() {
        return R.layout.layout_ganhuo_android;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        ((GanhuoPresenter) persenter).getHttp(Constant.TECH_FIRST, 20, 1);
    }

    @Override
    protected IPersenter createPersenter() {
        return new GanhuoPresenter();
    }


    @Override
    public void getHttpResult(GankHttpResponse<List<GankItemBean>> listGankHttpResponse) {
        results = listGankHttpResponse.getResults();


        tvTechAuthor.setText(results.get(0).getWho());
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recycGanhuoAndroid.setLayoutManager(manager);
        ganhuoAndroidAdapter = new GanhuoAndroidAdapter(results, Constant.TECH_FIRST);
        recycGanhuoAndroid.setAdapter(ganhuoAndroidAdapter);
    }

    @Override
    public void getSearchResult(GankHttpResponse<List<GankSearchItemBean>> listGankHttpResponse) {
        results.clear();

        ganhuoAndroidAdapter.notifyDataSetChanged();
        List<GankSearchItemBean> results1 = listGankHttpResponse.getResults();
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recycGanhuoAndroid.setLayoutManager(manager);
        GanhuoSearch ganhuoSearch = new GanhuoSearch(results1, Constant.TECH_FIRST);
        recycGanhuoAndroid.setAdapter(ganhuoSearch);
    }

    public void searchWord(String searchString) {
        ((GanhuoPresenter) persenter).getHttpSearch(searchString,Constant.TECH_FIRST,20,1);
    }
}
