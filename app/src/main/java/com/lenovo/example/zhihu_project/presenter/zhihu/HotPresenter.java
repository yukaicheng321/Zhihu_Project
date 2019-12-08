package com.lenovo.example.zhihu_project.presenter.zhihu;

import com.lenovo.example.zhihu_project.component.CommonSubscriber;
import com.lenovo.example.zhihu_project.interfaces.zhihu.ZhihuContract;
import com.lenovo.example.zhihu_project.model.HttpManager;
import com.lenovo.example.zhihu_project.model.bean.HotListBean;
import com.lenovo.example.zhihu_project.presenter.BasePersenter;
import com.lenovo.example.zhihu_project.utils.RxUtils;

/**
 * Created by lenovo on 2019/9/5.
 */

public class HotPresenter extends BasePersenter<ZhihuContract.View> implements ZhihuContract.Presenter {

    @Override
    public void getHttp() {
        addSubscribe(HttpManager.getZhihuApi().getHotList()
        .compose(RxUtils.<HotListBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<HotListBean>(mView) {
            @Override
            public void onNext(HotListBean hotListBean) {
                mView.getHttpResult(hotListBean);
            }
        }));
    }

    @Override
    public void getDailyBefor(String data) {

    }

    @Override
    public void getItemDatas(int id) {

    }
}
