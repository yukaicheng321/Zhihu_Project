package com.lenovo.example.zhihu_project.fragment.juejin;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.lenovo.example.zhihu_project.adapters.juejin.MyImpTouchAdapter;
import com.lenovo.example.zhihu_project.adapters.juejin.SelectorAdapter;

/**
 * Created by lenovo on 2019/9/10.
 */

public class MyItemTouchHelp extends ItemTouchHelper.Callback {

    private MyImpTouchAdapter selectorAdapter;

    public MyItemTouchHelp(MyImpTouchAdapter selectorAdapter) {
        this.selectorAdapter = selectorAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;        //允许上下的拖动
        int swipeFlags = ItemTouchHelper.LEFT;   //只允许从右向左侧滑
        return makeMovementFlags(dragFlags,swipeFlags);


    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        selectorAdapter.itemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        selectorAdapter.itemDelete(viewHolder.getAdapterPosition());
    }
}
