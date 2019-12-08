package com.lenovo.example.zhihu_project.interfaces.zhihu;

import com.lenovo.example.zhihu_project.interfaces.IBaseView;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;
import com.lenovo.example.zhihu_project.model.bean.WXHttpResponse;
import com.lenovo.example.zhihu_project.model.bean.WXItemBean;

import java.util.List;

/**
 * Created by lenovo on 2019/9/8.
 */

public interface WeixinContract {

    interface View extends IBaseView{
        void getWeixinResult(WXHttpResponse<List<WXItemBean>> listWXHttpResponse);

    }

    interface Presenter extends IPersenter<View>{
        void requestHttp(String key,int number,int page);

        void searchRequest(String key,int number,int page,String word);

    }

}
