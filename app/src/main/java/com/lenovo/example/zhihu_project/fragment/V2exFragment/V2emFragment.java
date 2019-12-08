package com.lenovo.example.zhihu_project.fragment.V2exFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.adapters.v2ex.FragmentAdapter;
import com.lenovo.example.zhihu_project.base.BaseFragment;
import com.lenovo.example.zhihu_project.constants.Constant;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;
import com.lenovo.example.zhihu_project.presenter.v2em.VtexPersenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2019/9/5.
 */

public class V2emFragment extends BaseFragment {
    public static String[] typeStr = {"技术", "创意", "好玩", "Apple", "酷工作", "交易", "城市", "问与答", "最热",
            "全部", "R2"};
    public static String[] type = {"tech", "creative", "play", "apple", "jobs", "deals", "city",
            "qna", "hot", "all", "r2"};

    List<Fragment> fragments = new ArrayList<>();
    FragmentAdapter fragmentAdapter;
    List<String> tabTitles;
    @BindView(R.id.tab_juejin)
    TabLayout tabJuejin;
    @BindView(R.id.iv_selector)
    ImageView ivSelector;
    @BindView(R.id.vp_juejin)
    ViewPager vpJuejin;
    Unbinder unbinder;
    Unbinder unbinder1;

    @Override
    public void showErrMsg(String err) {

    }

    @Override
    protected int getLayout() {
        return R.layout.layout_juejin;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        tabTitles = new ArrayList<>();
        tabJuejin.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabJuejin.setupWithViewPager(vpJuejin);
        for (int i = 0; i < type.length; i++) {
            tabTitles.add(type[i]);
            VtexPageFragment fragment = VtexPageFragment.newInstance(type[i]);
            Bundle bundle = new Bundle();
            bundle.putString(Constant.IT_VTEX_TYPE, type[i]);
            fragment.setArguments(bundle);
            tabJuejin.addTab(tabJuejin.newTab());
            fragments.add(fragment);
        }
        fragmentAdapter = new FragmentAdapter(getFragmentManager(), fragments, tabTitles);
        vpJuejin.setAdapter(fragmentAdapter);
    }

    @Override
    protected IPersenter createPersenter() {
        return new VtexPersenter();
    }






    @OnClick(R.id.iv_selector)
    public void onViewClicked() {

                Intent intent = new Intent(getContext(), NodeActivity.class);
                startActivity(intent);




    }
}
