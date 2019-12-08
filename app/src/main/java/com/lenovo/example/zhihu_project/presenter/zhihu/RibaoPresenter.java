package com.lenovo.example.zhihu_project.presenter.zhihu;

import com.lenovo.example.zhihu_project.base.BaseFragment;
import com.lenovo.example.zhihu_project.component.CommonSubscriber;
import com.lenovo.example.zhihu_project.interfaces.zhihu.ZhihuContract;
import com.lenovo.example.zhihu_project.model.HttpManager;
import com.lenovo.example.zhihu_project.model.bean.DailyBeforeListBean;
import com.lenovo.example.zhihu_project.model.bean.DailyListBean;
import com.lenovo.example.zhihu_project.model.bean.ThemeChildListBean;
import com.lenovo.example.zhihu_project.model.bean.ZhihuDetailBean;
import com.lenovo.example.zhihu_project.presenter.BasePersenter;
import com.lenovo.example.zhihu_project.utils.RxUtils;

/**
 * Created by lenovo on 2019/9/5.
 */

public class RibaoPresenter extends BasePersenter<ZhihuContract.View> implements ZhihuContract.Presenter {

    @Override
    public void getHttp() {
        addSubscribe(HttpManager.getZhihuApi().getDailyList()
        .compose(RxUtils.<DailyListBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<DailyListBean>(mView) {
            @Override
            public void onNext(DailyListBean dailyListBean) {
                mView.getHttpResult(dailyListBean);
            }
        }));
    }

    @Override
    public void getDailyBefor(String data) {
        addSubscribe(HttpManager.getZhihuApi().getDailyBeforeList(data)
        .compose(RxUtils.<DailyBeforeListBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<DailyBeforeListBean>(mView) {
            @Override
            public void onNext(DailyBeforeListBean dailyBeforeListBean) {
                mView.getHttpResult(dailyBeforeListBean);
            }
        }));
    }

    @Override
    public void getItemDatas(int id) {
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
