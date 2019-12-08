package com.lenovo.example.zhihu_project.interfaces.V2ex;


import com.lenovo.example.zhihu_project.interfaces.IBaseView;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;
import com.lenovo.example.zhihu_project.model.bean.TopicListBean;

import java.util.List;

public interface ExContract {
    //微信精选返回的接口定义
    interface View extends IBaseView {

    }

    //微信的p层接口
    interface Persenter extends IPersenter<View> {
        void getNodeInfo(String name);
        void getTopicList(String name);
        void getTopicInfo(String id);
        void getRepliesList(String id);
    }

    /**
     * 获取Vtex数据
     */
    interface VtexView extends IBaseView{
        void getContentReturn(List<TopicListBean> list);
    }

    interface VtexPersenter extends IPersenter<VtexView>{
        void getContent(String type);
    }
}
