package com.lenovo.example.zhihu_project.interfaces;

public interface IPersenter<V extends IBaseView> {

    //p层关联V层
    void attchView(V view);

    void detachView();

}
