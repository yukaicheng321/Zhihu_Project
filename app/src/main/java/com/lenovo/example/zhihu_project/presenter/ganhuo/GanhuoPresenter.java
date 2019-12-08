package com.lenovo.example.zhihu_project.presenter.ganhuo;

import com.lenovo.example.zhihu_project.component.CommonSubscriber;
import com.lenovo.example.zhihu_project.interfaces.ganhuo.GanHuoContract;
import com.lenovo.example.zhihu_project.model.HttpManager;
import com.lenovo.example.zhihu_project.model.bean.GankHttpResponse;
import com.lenovo.example.zhihu_project.model.bean.GankItemBean;
import com.lenovo.example.zhihu_project.model.bean.GankSearchItemBean;
import com.lenovo.example.zhihu_project.presenter.BasePersenter;
import com.lenovo.example.zhihu_project.utils.RxUtils;

import java.util.List;

/**
 * Created by lenovo on 2019/9/5.
 */

public class GanhuoPresenter extends BasePersenter<GanHuoContract.View> implements GanHuoContract.Presenter,GanHuoContract.PresenterGril,GanHuoContract.PresenterSearch {

    @Override
    public void getHttp(String tech,int num,int page) {
        addSubscribe(HttpManager.getGanHuoApis().getTechList(tech,num,page)
        .compose(RxUtils.<GankHttpResponse<List<GankItemBean>>>rxScheduler())
        .subscribeWith(new CommonSubscriber<GankHttpResponse<List<GankItemBean>>>(mView) {
            @Override
            public void onNext(GankHttpResponse<List<GankItemBean>> listGankHttpResponse) {
                mView.getHttpResult(listGankHttpResponse);
            }
        }));
    }

    @Override
    public void gethttpGrils(int num, int page) {
        addSubscribe(HttpManager.getGanHuoApis().getGirlList(num,page)
        .compose(RxUtils.<GankHttpResponse<List<GankItemBean>>>rxScheduler())
        .subscribeWith(new CommonSubscriber<GankHttpResponse<List<GankItemBean>>>(mView) {
            @Override
            public void onNext(GankHttpResponse<List<GankItemBean>> listGankHttpResponse) {
                mView.getHttpResult(listGankHttpResponse);
            }
        }));
    }

    @Override
    public void getHttpSearch(String query, String tech, int num, int page) {
        addSubscribe(HttpManager.getGanHuoApis().getSearchList(query,tech,num,page)
        .compose(RxUtils.<GankHttpResponse<List<GankSearchItemBean>>>rxScheduler())
        .subscribeWith(new CommonSubscriber<GankHttpResponse<List<GankSearchItemBean>>>(mView) {
            @Override
            public void onNext(GankHttpResponse<List<GankSearchItemBean>> listGankHttpResponse) {
                mView.getSearchResult(listGankHttpResponse);
            }
        }));
    }
}
