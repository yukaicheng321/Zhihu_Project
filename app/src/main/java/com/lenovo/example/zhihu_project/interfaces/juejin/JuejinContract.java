package com.lenovo.example.zhihu_project.interfaces.juejin;

import com.lenovo.example.zhihu_project.interfaces.IBaseView;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;

/**
 * Created by lenovo on 2019/9/10.
 */

public interface JuejinContract {

    interface View extends IBaseView{
        void getResult();
    }

    interface Presenter extends IPersenter<View>{
        void requestHttp();
    }

}
