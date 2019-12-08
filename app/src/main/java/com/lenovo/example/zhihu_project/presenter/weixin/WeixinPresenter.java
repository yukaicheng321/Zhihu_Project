package com.lenovo.example.zhihu_project.presenter.weixin;

import com.lenovo.example.zhihu_project.component.CommonSubscriber;
import com.lenovo.example.zhihu_project.interfaces.zhihu.WeixinContract;
import com.lenovo.example.zhihu_project.model.HttpManager;
import com.lenovo.example.zhihu_project.model.bean.WXHttpResponse;
import com.lenovo.example.zhihu_project.model.bean.WXItemBean;
import com.lenovo.example.zhihu_project.presenter.BasePersenter;
import com.lenovo.example.zhihu_project.utils.RxUtils;

import java.util.List;

/**
 * Created by lenovo on 2019/9/5.
 */

public class WeixinPresenter extends BasePersenter<WeixinContract.View> implements WeixinContract.Presenter{

    @Override
    public void requestHttp(String key,int number,int page) {
            addSubscribe(HttpManager.getWeixinApi().getWXHot(key,number,page)
            .compose(RxUtils.<WXHttpResponse<List<WXItemBean>>>rxScheduler()).subscribeWith(new CommonSubscriber<WXHttpResponse<List<WXItemBean>>>(mView) {
                        @Override
                        public void onNext(WXHttpResponse<List<WXItemBean>> listWXHttpResponse) {
                            mView.getWeixinResult(listWXHttpResponse);
                        }
                    }));
    }

    @Override
    public void searchRequest(String key, int number, int page, String word) {
        addSubscribe(HttpManager.getWeixinApi().getWXHotSearch(key,number,page,word)
        .compose(RxUtils.<WXHttpResponse<List<WXItemBean>>>rxScheduler())
        .subscribeWith(new CommonSubscriber<WXHttpResponse<List<WXItemBean>>>(mView) {
            @Override
            public void onNext(WXHttpResponse<List<WXItemBean>> listWXHttpResponse) {
                mView.getWeixinResult(listWXHttpResponse);
            }
        }));
    }
}
