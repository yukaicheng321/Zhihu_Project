package com.lenovo.example.zhihu_project.presenter.zhihu;

import com.lenovo.example.zhihu_project.component.CommonSubscriber;
import com.lenovo.example.zhihu_project.interfaces.zhihu.ColumnParticularsContract;
import com.lenovo.example.zhihu_project.interfaces.zhihu.ZhihuContract;
import com.lenovo.example.zhihu_project.model.HttpManager;
import com.lenovo.example.zhihu_project.model.bean.ColumnTalkBean;
import com.lenovo.example.zhihu_project.model.bean.SectionChildListBean;
import com.lenovo.example.zhihu_project.model.bean.ZhihuDetailBean;
import com.lenovo.example.zhihu_project.presenter.BasePersenter;
import com.lenovo.example.zhihu_project.utils.RxUtils;

import retrofit2.http.HTTP;

/**
 * Created by lenovo on 2019/9/6.
 */

public class ColumnShowPresenter extends BasePersenter<ColumnParticularsContract.View> implements ColumnParticularsContract.Presenter,ColumnParticularsContract.PresenterTwo {


    @Override
    public void getHttp(int id) {
        addSubscribe(HttpManager.getZhihuApi().getSectionChildList(id)
        .compose(RxUtils.<SectionChildListBean>rxScheduler()).subscribeWith(new CommonSubscriber
                        <SectionChildListBean>(mView) {
                    @Override
                    public void onNext(SectionChildListBean sectionChildListBean) {
                        mView.getHttpResult(sectionChildListBean);
                    }
                }));
    }


    @Override
    public void getTalk(int id) {
        addSubscribe(HttpManager.getZhihuApi().getDetailInfo(id)
        .compose(RxUtils.<ZhihuDetailBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<ZhihuDetailBean>(mView) {
            @Override
            public void onNext(ZhihuDetailBean zhihuDetailBean) {
                mView.getHttpResult(zhihuDetailBean);
            }
        }));
    }
}
