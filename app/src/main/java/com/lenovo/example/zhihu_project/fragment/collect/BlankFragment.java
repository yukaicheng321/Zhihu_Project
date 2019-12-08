package com.lenovo.example.zhihu_project.fragment.collect;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.adapters.collect.CollectAdapter;
import com.lenovo.example.zhihu_project.apps.MyApp;
import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;
import com.lenovo.example.zhihu_project.fragment.zhihu.child.RibaoParticulars;
import com.lenovo.example.zhihu_project.greendao.CollectBean;
import com.lenovo.example.zhihu_project.greendao.CollectBeanDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements BaseAdapter.OnItemClickListener {


    @BindView(R.id.recyc_collect)
    RecyclerView recycCollect;
    Unbinder unbinder;
    private CollectBeanDao dao;
    private CollectAdapter collectAdapter;
    private List<CollectBean> list;
    private boolean ishint;
    private boolean index;
    private List<CollectBean> collectBeans;


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recycCollect.setLayoutManager(manager);
        dao = MyApp.daoSession.getCollectBeanDao();
            setupRecycler();



    }

    private void setupRecycler() {

        list = new ArrayList<>();
        collectBeans = dao.loadAll();
        collectAdapter = new CollectAdapter(collectBeans);
        recycCollect.setAdapter(collectAdapter);
        collectAdapter.setOnItemClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        collectBeans.clear();
        List<CollectBean> newList = dao.loadAll();
        collectBeans.addAll(newList);
        collectAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(View v, int position) {
        CollectBean collectBean = collectBeans.get(position);
        int nowId = collectBean.getNowId();
        Log.i("tag", "onItemClick: 1111"+nowId);
        String img = collectBean.getImg();
        Intent intent = new Intent(getContext(), RibaoParticulars.class);
        intent.putExtra("img",img);
        intent.putExtra("id",nowId);
        startActivity(intent);

    }
}
