package com.lenovo.example.zhihu_project.fragment.V2exFragment;

import android.content.res.XmlResourceParser;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;


import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.adapters.v2ex.NodeAdapter;
import com.lenovo.example.zhihu_project.base.BaseActivity;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;
import com.lenovo.example.zhihu_project.presenter.zhihu.ColumnShowPresenter;
import com.lenovo.example.zhihu_project.utils.XmlUtil;

import butterknife.BindView;

public class NodeActivity extends BaseActivity {

    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.tv_node_title)
    TextView tvNodeTitle;

    NodeAdapter mAdapter;
    private LinearLayoutManager mManager;
    private ArrayMap<String, ArrayMap<String,String>> map;
    private int mTitleHeight;
    private int mCurrentPosition;

    @Override
    protected int getLayout() {
        return R.layout.activity_node;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        XmlResourceParser xmlParser = this.getResources().getXml(R.xml.nodes);
        try {
            map = XmlUtil.parseNodes(xmlParser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAdapter = new NodeAdapter(this , map);
        mManager = new LinearLayoutManager(this);
        rvContent.setLayoutManager(mManager);
        rvContent.setAdapter(mAdapter);
        rvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mTitleHeight = tvNodeTitle.getHeight();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View view = mManager.findViewByPosition(mCurrentPosition + 1);
                if (view != null) {
                    if (view.getTop() <= mTitleHeight) {
                        tvNodeTitle.setY(-(mTitleHeight - view.getTop()));
                    } else {
                        tvNodeTitle.setY(0);
                    }
                }
                if (mCurrentPosition != mManager.findFirstVisibleItemPosition()) {
                    mCurrentPosition = mManager.findFirstVisibleItemPosition();
                    tvNodeTitle.setY(0);
                    if (map != null) {
                        tvNodeTitle.setText(map.keyAt(mCurrentPosition));
                    }
                }
            }
        });
        tvNodeTitle.setText(map.keyAt(0));
    }

    @Override
    protected IPersenter initPersenter() {
        return new ColumnShowPresenter();
    }
}
