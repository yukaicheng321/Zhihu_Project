package com.lenovo.example.zhihu_project.interfaces.zhihu;

import com.lenovo.example.zhihu_project.interfaces.IBaseView;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;

/**
 * Created by lenovo on 2019/9/6.
 */

public interface ColumnParticularsContract {

    interface  View extends IBaseView{
       <T> void getHttpResult(T t);

    }
    interface Presenter extends IPersenter<View>{
        void getHttp(int id);
    }

    interface PresenterTwo extends IPersenter<View>{
        void getTalk(int id);
    }

}
