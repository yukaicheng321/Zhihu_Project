package com.lenovo.example.zhihu_project.interfaces.juejin;

/**
 * Created by lenovo on 2019/9/10.
 */

public interface ItemTouchHelpAdapter {
    //定义移动方法
    void itemMove(int fromPosition,int toPosition);
    //定义移除方法
    void itemDelete(int position);
}
