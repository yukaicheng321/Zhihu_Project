package com.lenovo.example.zhihu_project.fragment.juejin;

import android.view.View;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.base.BaseFragment;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;
import com.lenovo.example.zhihu_project.presenter.juejin.JuejinPresenter;

/**
 * Created by lenovo on 2019/9/10.
 */

public class JueJinChild extends BaseFragment {
    @Override
    public void showErrMsg(String err) {

    }

    @Override
    protected int getLayout() {
        return R.layout.layout_juejinchild;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected IPersenter createPersenter() {
        return new JuejinPresenter();
    }
}
