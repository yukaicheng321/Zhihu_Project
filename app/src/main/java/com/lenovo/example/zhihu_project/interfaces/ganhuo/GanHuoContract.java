package com.lenovo.example.zhihu_project.interfaces.ganhuo;

import com.lenovo.example.zhihu_project.interfaces.IBaseView;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;
import com.lenovo.example.zhihu_project.model.bean.GankHttpResponse;
import com.lenovo.example.zhihu_project.model.bean.GankItemBean;
import com.lenovo.example.zhihu_project.model.bean.GankSearchItemBean;

import java.util.List;

/**
 * Created by lenovo on 2019/9/9.
 */

public interface GanHuoContract {

    interface View extends IBaseView{
        void getHttpResult(GankHttpResponse<List<GankItemBean>> listGankHttpResponse);
        void getSearchResult(GankHttpResponse<List<GankSearchItemBean>> listGankHttpResponse);
    }
    interface  Presenter extends IPersenter<View>{
        void  getHttp(String tech,int num,int page);
    }
    interface  PresenterGril extends IPersenter<View>{
        void gethttpGrils(int num,int page);
    }
    interface  PresenterSearch extends IPersenter<View>{
        void getHttpSearch(String query,String tech,int num,int page);
    }

}
