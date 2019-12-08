package com.lenovo.example.zhihu_project.presenter.zhihu;

import com.lenovo.example.zhihu_project.component.CommonSubscriber;
import com.lenovo.example.zhihu_project.interfaces.zhihu.ZhihuContract;
import com.lenovo.example.zhihu_project.model.HttpManager;
import com.lenovo.example.zhihu_project.model.bean.SectionListBean;
import com.lenovo.example.zhihu_project.presenter.BasePersenter;
import com.lenovo.example.zhihu_project.utils.RxUtils;

/**
 * Created by lenovo on 2019/9/5.
 */

public class ColumnPresenter extends BasePersenter<ZhihuContract.View>implements ZhihuContract.Presenter {

    @Override
    public void getHttp() {
        addSubscribe(HttpManager.getZhihuApi().getSectionList()
        .compose(RxUtils.<SectionListBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<SectionListBean>(mView) {
            @Override
            public void onNext(SectionListBean sectionListBean) {
                mView.getHttpResult(sectionListBean);
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
