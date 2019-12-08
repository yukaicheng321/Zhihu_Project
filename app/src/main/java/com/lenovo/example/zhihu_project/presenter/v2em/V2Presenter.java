package com.lenovo.example.zhihu_project.presenter.v2em;

import com.lenovo.example.zhihu_project.component.CommonSubscriber;
import com.lenovo.example.zhihu_project.interfaces.V2ex.V2exContract;
import com.lenovo.example.zhihu_project.model.HttpManager;
import com.lenovo.example.zhihu_project.model.bean.NodeBean;
import com.lenovo.example.zhihu_project.presenter.BasePersenter;
import com.lenovo.example.zhihu_project.utils.RxUtils;

/**
 * Created by lenovo on 2019/9/5.
 */

public class V2Presenter extends BasePersenter<V2exContract.View> implements V2exContract.Presenter {

    @Override
    public void requsetMessage(String name) {
        addSubscribe(HttpManager.getV2Node().getNodeInfo(name)
        .compose(RxUtils.<NodeBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<NodeBean>(mView) {
            @Override
            public void onNext(NodeBean nodeBean) {
                mView.getResult(nodeBean);
            }
        }));
    }
}
