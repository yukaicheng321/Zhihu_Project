package com.lenovo.example.zhihu_project.adapters.juejin;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;
import com.lenovo.example.zhihu_project.interfaces.juejin.ItemTouchHelpAdapter;
import com.lenovo.example.zhihu_project.model.bean.StateInfo;

import java.util.Collections;
import java.util.List;

/**
 * Created by lenovo on 2019/9/10.
 */

public class SelectorAdapter extends BaseAdapter implements ItemTouchHelpAdapter{
    public SelectorAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_juejin_activity_item;
    }

    @Override
    protected void bindData(final BaseViewHolder holder, final int positon, Object o) {
        final StateInfo stateInfo = (StateInfo) mDatas.get(positon);
        TextView tv = (TextView) holder.getView(R.id.tv_juejin_item);
        tv.setText(stateInfo.getType());
        final SwitchCompat switchCompat = (SwitchCompat) holder.getView(R.id.switchCompat_juejin);
        switchCompat.setChecked(stateInfo.isState());
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               stateInfo.setState(isChecked);

            }
        });



    }



    @Override
    public void itemMove(int fromPosition, int toPosition) {
        Collections.swap(mDatas,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);

    }

    @Override
    public void itemDelete(int position) {

        mDatas.remove(position);
        notifyItemRemoved(position);

    }
}
