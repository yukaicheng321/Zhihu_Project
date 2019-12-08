package com.lenovo.example.zhihu_project.fragment.weixin;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.adapters.weixin.WeixinAdapter;
import com.lenovo.example.zhihu_project.base.BaseFragment;
import com.lenovo.example.zhihu_project.constants.Constant;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;
import com.lenovo.example.zhihu_project.interfaces.zhihu.WeixinContract;
import com.lenovo.example.zhihu_project.model.bean.WXHttpResponse;
import com.lenovo.example.zhihu_project.model.bean.WXItemBean;
import com.lenovo.example.zhihu_project.presenter.weixin.WeixinPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2019/9/5.
 */

public class WeixinFragment extends BaseFragment implements WeixinContract.View {

    @BindView(R.id.recyc_weixin)
    RecyclerView recycWeixin;
    Unbinder unbinder;
    private int REQUEST;
    private List<WXItemBean> newslist;
    private WeixinAdapter weixinAdapter;

    @Override
    public void showErrMsg(String err) {

    }


    @Override
    protected int getLayout() {
        return R.layout.layout_weixin;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        ((WeixinPresenter) persenter).requestHttp(Constant.KEY_API, 20, 1);
    }

    @Override
    protected IPersenter createPersenter() {
        return new WeixinPresenter();
    }

    @Override
    public void getWeixinResult(WXHttpResponse<List<WXItemBean>> listWXHttpResponse) {

        if (REQUEST == 0) {

            newslist = listWXHttpResponse.getNewslist();
            LinearLayoutManager manager = new LinearLayoutManager(context);
            recycWeixin.setLayoutManager(manager);
            weixinAdapter = new WeixinAdapter(newslist);
            recycWeixin.setAdapter(weixinAdapter);

        }
        if (REQUEST>0) {
            newslist.clear();
            List<WXItemBean> searchList = listWXHttpResponse.getNewslist();
            newslist.addAll(searchList);
            weixinAdapter.notifyDataSetChanged();
        }


    }


    public void searchWord(String query) {
        if (query != null) {
            REQUEST++;
            ((WeixinPresenter) persenter).searchRequest(Constant.KEY_API,20,1,query);
        }

    }
}
