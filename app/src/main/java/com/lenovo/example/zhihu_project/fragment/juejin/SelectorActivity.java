package com.lenovo.example.zhihu_project.fragment.juejin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.View;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.adapters.juejin.MyImpTouchAdapter;
import com.lenovo.example.zhihu_project.adapters.juejin.SelectorAdapter;
import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;
import com.lenovo.example.zhihu_project.model.bean.StateInfo;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectorActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.recyc_juejin)
    RecyclerView recycJuejin;
    @BindView(R.id.toolbar_juejin)
    Toolbar toolbarJuejin;
    private Intent intent;
    private ArrayList<StateInfo> stateInfo;
    private ItemTouchHelper.Callback callback;
    private SelectorAdapter selectorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

        intent = getIntent();
        stateInfo = intent.getParcelableArrayListExtra("stateInfo");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycJuejin.setLayoutManager(manager);
//        selectorAdapter = new SelectorAdapter(stateInfo);
//        recycJuejin.setAdapter(selectorAdapter);
        MyImpTouchAdapter myImpTouchAdapter = new MyImpTouchAdapter(stateInfo, this);
        recycJuejin.setAdapter(myImpTouchAdapter);
        recycJuejin.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        toolbarJuejin.setNavigationOnClickListener(this);
//        构建自定义类对象来多态创建ItemTouchHelper.Callback对象
        ItemTouchHelper.Callback  callback = new MyItemTouchHelp(myImpTouchAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);

        itemTouchHelper.attachToRecyclerView(recycJuejin);
    }


    @Override
    public void onClick(View v) {
        intent.putParcelableArrayListExtra("back",(ArrayList<? extends Parcelable>)stateInfo);
        setResult(101,intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode==KeyEvent.KEYCODE_BACK) {
            intent.putParcelableArrayListExtra("back",(ArrayList<? extends Parcelable>)stateInfo);
            setResult(101,intent);
            finish();

        }
        
        return super.onKeyDown(keyCode, event);
    }
}
