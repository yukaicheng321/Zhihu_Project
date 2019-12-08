package com.lenovo.example.zhihu_project.adapters.v2ex;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.constants.Constant;
import com.lenovo.example.zhihu_project.fragment.V2exFragment.NodeListActivity;
import com.lenovo.example.zhihu_project.utils.FlowLayout;
import com.lenovo.example.zhihu_project.utils.SystemUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.POST;

public class NodeAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayMap<String, ArrayMap<String,String>> mMap;

    public NodeAdapter(Context context, ArrayMap<String, ArrayMap<String,String>> mMap) {
        this.mContext = context;
        this.mMap = mMap;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_node,null);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        VH vh = (VH) viewHolder;
        vh.tvTitle.setText(mMap.keyAt(i));
        vh.flContent.removeAllViews();
        ArrayMap<String,String> mNodeBlock = mMap.valueAt(i);
        for (ArrayMap.Entry<String,String> node : mNodeBlock.entrySet()) {
            TextView tvNode = new TextView(mContext);
            tvNode.setText(node.getValue());
            tvNode.setTextColor(ContextCompat.getColor(mContext, R.color.colorText));
            tvNode.setPadding(SystemUtils.dp2px(6f), SystemUtils.dp2px(6f), SystemUtils.dp2px(6f), SystemUtils.dp2px(6f));
            tvNode.setOnClickListener(new OnNodeClickListener(node.getKey()));
            vh.flContent.addView(tvNode);
        }
    }

    @Override
    public int getItemCount() {
        return mMap.size();
    }

    class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_node_title)
        TextView tvTitle;
        @BindView(R.id.fl_node_content)
        FlowLayout flContent;

        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private class OnNodeClickListener implements View.OnClickListener {

        private String nodeName;

        public OnNodeClickListener(String nodeName) {
            this.nodeName = nodeName;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(mContext, NodeListActivity.class);
            intent.putExtra(Constant.IT_VTEX_NODE_NAME, nodeName);
            mContext.startActivity(intent);
        }
    }
}
