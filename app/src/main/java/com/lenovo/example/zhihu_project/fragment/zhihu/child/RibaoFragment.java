package com.lenovo.example.zhihu_project.fragment.zhihu.child;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.adapters.zhihu.RibaoAdapter;
import com.lenovo.example.zhihu_project.base.BaseFragment;
import com.lenovo.example.zhihu_project.base.adapter.BaseAdapter;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;
import com.lenovo.example.zhihu_project.interfaces.zhihu.ZhihuContract;
import com.lenovo.example.zhihu_project.model.bean.DailyBeforeListBean;
import com.lenovo.example.zhihu_project.model.bean.DailyListBean;
import com.lenovo.example.zhihu_project.presenter.zhihu.RibaoPresenter;
import com.lenovo.example.zhihu_project.utils.CircularAnimUtil;
import com.lenovo.example.zhihu_project.utils.DateUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2019/9/5.
 */

public class RibaoFragment extends BaseFragment implements ZhihuContract.View, BaseAdapter.OnItemClickListener {


    @BindView(R.id.ban_ribao)
    Banner banRibao;
    @BindView(R.id.recyc_ribao)
    RecyclerView recycRibao;
    Unbinder unbinder;
    @BindView(R.id.tv_ribao)
    TextView tvRibao;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    Unbinder unbinder1;
    private List<DailyListBean.TopStoriesBean> top_stories;
    private List<String> title = new ArrayList<>();
    private SimpleDateFormat df;

    private String newDate;
    private int HTTPTYPE;
    private RibaoAdapter ribaoAdapter;
    private List<DailyListBean.StoriesBean> stories;
    private String mData;
    private Fragment fragment;
    private String image;
    private int id;

    @Override
    public void showErrMsg(String err) {

    }

    @Override
    protected int getLayout() {
        return R.layout.layout_zhihu_ribao;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        this.fragment = this;
////        EventBus.getDefault().register(this);
//        df = new SimpleDateFormat("yyyy年MM月dd日");
        HTTPTYPE=1;
        ((RibaoPresenter) persenter).getHttp();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), CalendarActivity.class);
//                startActivityForResult(intent,2);
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent it = new Intent();
                it.setClass(getActivity(), CalendarActivity.class);
                CircularAnimUtil.fragmentStartActivityForResult(fragment,it,2,fab,R.color.colorAccent);
            }
        });

    }

//    @Subscribe
//    public void getData(String formatDate){
//
////        int day = data.getDay();
////        int month = data.getMonth();
////        int year = data.getYear();
////        newDate = year+""+month+""+day+"";
////        String format = df.format(new Date(newDate));
////        Log.i("tag", "getData: "+format);
//        Log.i("tag", "getData: "+formatDate);
//        if (formatDate!=null) {
//            ((RibaoPresenter) persenter).getDailyBefor(formatDate);
//        }
//
//
//
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2){
            if (data==null) {
                return;
            }
            String date = data.getStringExtra("date");
            if(!TextUtils.isEmpty(date)) {
                  HTTPTYPE=2;
                  mData=date;
                Log.i("tag", "onActivityResult: "+date);
                ((RibaoPresenter) persenter).getDailyBefor(date);
            }
        }
    }

    @Override
    protected IPersenter createPersenter() {
        return new RibaoPresenter();
    }


    @Override
    public <T> void getHttpResult(T t) {
        if (HTTPTYPE == 1) {

            DailyListBean dailyListBean1 = (DailyListBean) t;
            top_stories = dailyListBean1.getTop_stories();
            stories = dailyListBean1.getStories();
            if (title.size() == 0) {
                //只让其走一次
                for (int i = 0; i < top_stories.size(); i++) {
                    title.add(top_stories.get(i).getTitle());
                }
            }

            banRibao.setBannerTitles(title);
            banRibao.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            banRibao.setImages(top_stories);
            banRibao.setImageLoader(new MyImageLoad());
            banRibao.start();

            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            recycRibao.setLayoutManager(manager);
            ribaoAdapter = new RibaoAdapter(stories);
            recycRibao.setAdapter(ribaoAdapter);
            ribaoAdapter.setOnItemClickListener(this);
            banRibao.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    id = top_stories.get(position).getId();
                    image = top_stories.get(position).getImage();
                    Intent intent = new Intent(getContext(), RibaoParticulars.class);
                    intent.putExtra("img", image);
                    intent.putExtra("id", id);
                    startActivity(intent);

                }
            });
        }
        if (HTTPTYPE == 2) {
            tvRibao.setText(mData);
            DailyBeforeListBean t1 = (DailyBeforeListBean) t;

                List<DailyListBean.StoriesBean> stories1 = t1.getStories();
                stories.clear();
                stories.addAll(stories1);
                ribaoAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void onItemClick(View v, int position) {

    }


    class MyImageLoad extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            DailyListBean.TopStoriesBean img = (DailyListBean.TopStoriesBean) path;
            Glide.with(getActivity()).load(img.getImage()).into(imageView);
        }
    }
}
