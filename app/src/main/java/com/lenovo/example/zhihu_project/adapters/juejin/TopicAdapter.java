package com.lenovo.example.zhihu_project.adapters.juejin;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;


import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;
import com.lenovo.example.zhihu_project.component.ImageLoader;
import com.lenovo.example.zhihu_project.model.bean.TopicListBean;


import java.util.List;

public class TopicAdapter extends BaseAdapter {
    public TopicAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_vtex;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        TopicListBean bean = (TopicListBean) mDatas.get(positon);
        ImageView topicFace = (ImageView) holder.getView(R.id.iv_topic_face);
        ImageLoader.load(mContext,bean.getImgUrl(),topicFace);
        TextView txtTopicName = (TextView)holder.getView(R.id.tv_topic_name);
        txtTopicName.setText(bean.getName());
        TextView txtTopicTips = (TextView)holder.getView(R.id.tv_topic_tips);
        txtTopicTips.setText(bean.getUpdateTime()+" • 最后回复 "+bean.getLastUser());
        TextView txtTopicComment = (TextView)holder.getView(R.id.tv_topic_comment);
        txtTopicComment.setText(String.valueOf(bean.getCommentNum()));
        TextView txtTopicNode = (TextView)holder.getView(R.id.tv_topic_node);
        txtTopicNode.setText(bean.getNode());
        TextView txtTopicTitle = (TextView)holder.getView(R.id.tv_topic_title);
        txtTopicTitle.setText(bean.getTitle());

    }
}
