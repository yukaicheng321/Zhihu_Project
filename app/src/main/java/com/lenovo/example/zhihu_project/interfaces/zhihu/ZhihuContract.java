package com.lenovo.example.zhihu_project.interfaces.zhihu;

import com.lenovo.example.zhihu_project.interfaces.IBaseView;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;
import com.lenovo.example.zhihu_project.model.bean.DailyListBean;

/**
 * Created by lenovo on 2019/9/5.
 */

public interface ZhihuContract {

    interface  View extends IBaseView{
       <T> void getHttpResult(T t);
    }
    interface  Presenter extends IPersenter<View>{
        void getHttp();

        void getDailyBefor(String data);

        void getItemDatas(int id);
    }
}
