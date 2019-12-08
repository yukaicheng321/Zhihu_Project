package com.lenovo.example.zhihu_project.interfaces.V2ex;

import com.lenovo.example.zhihu_project.interfaces.IBaseView;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;
import com.lenovo.example.zhihu_project.model.bean.NodeBean;

/**
 * Created by lenovo on 2019/9/10.
 */

public interface V2exContract {

    interface View extends IBaseView{
        void getResult(NodeBean nodeBean);
    }
    interface Presenter extends IPersenter<View>{
        void requsetMessage(String name);
    }


}
