package com.lenovo.example.zhihu_project.fragment.V2exFragment;


import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.base.BaseActivity;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;
import com.lenovo.example.zhihu_project.interfaces.V2ex.V2exContract;
import com.lenovo.example.zhihu_project.model.bean.NodeBean;
import com.lenovo.example.zhihu_project.presenter.v2em.V2Presenter;

public class NodeListActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_nodelist;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected IPersenter initPersenter() {
        return new V2Presenter();
    }

}
