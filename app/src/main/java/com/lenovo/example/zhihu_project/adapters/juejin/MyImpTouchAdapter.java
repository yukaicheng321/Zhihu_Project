package com.lenovo.example.zhihu_project.adapters.juejin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.interfaces.juejin.ItemTouchHelpAdapter;
import com.lenovo.example.zhihu_project.model.bean.StateInfo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by lenovo on 2019/9/11.
 */

public class MyImpTouchAdapter extends RecyclerView.Adapter<MyImpTouchAdapter.MyHolder> implements ItemTouchHelpAdapter {
    private ArrayList<StateInfo> list;
    private Context context;

    public MyImpTouchAdapter(ArrayList<StateInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.layout_juejin_activity_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.tv.setText(list.get(position).getType());
        holder.sc.setChecked(list.get(position).isState());
        holder.sc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(position).setState(isChecked);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void itemMove(int fromPosition, int toPosition) {
        Collections.swap(list,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void itemDelete(int position) {
        list.remove(position);
        notifyItemRemoved(position);

    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView tv;
        SwitchCompat sc;
        public MyHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tv_juejin_item);
            sc=itemView.findViewById(R.id.switchCompat_juejin);
        }
    }

}
