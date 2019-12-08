package com.lenovo.example.zhihu_project.fragment.zhihu.child;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lenovo.example.zhihu_project.MainActivity;
import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.base.BaseActivity;
import com.lenovo.example.zhihu_project.interfaces.IPersenter;
import com.lenovo.example.zhihu_project.interfaces.zhihu.ColumnParticularsContract;
import com.lenovo.example.zhihu_project.model.bean.ZhihuDetailBean;
import com.lenovo.example.zhihu_project.presenter.zhihu.ColumnShowPresenter;
import com.lenovo.example.zhihu_project.utils.HtmlUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2019/9/9.
 */

public class ColumnTalkActivity extends BaseActivity implements ColumnParticularsContract.View {

    @BindView(R.id.iv_ribao_particulars)
    ImageView ivRibaoParticulars;
    @BindView(R.id.tv_collapsin)
    TextView tvCollapsin;
    @BindView(R.id.view_toolbar)
    Toolbar viewToolbar;
    @BindView(R.id.ctl)
    CollapsingToolbarLayout ctl;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.view_main)
    WebView viewMain;
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    @BindView(R.id.tv_detail_bottom_like)
    TextView tvDetailBottomLike;
    @BindView(R.id.tv_detail_bottom_comment)
    TextView tvDetailBottomComment;
    @BindView(R.id.tv_detail_bottom_share)
    TextView tvDetailBottomShare;
    @BindView(R.id.ll_detail_bottom)
    FrameLayout llDetailBottom;
    @BindView(R.id.fab_like)
    FloatingActionButton fabLike;
    boolean isBottomShow = true;
    @Override
    protected int getLayout() {
        return R.layout.layout_ribao_particulars;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String img = intent.getStringExtra("img");
        String title = intent.getStringExtra("title");
        String name = intent.getStringExtra("name");
        //  设置滑动监听 下拉隐藏 上拉显示
        nsvScroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY - oldScrollY > 0 && isBottomShow) {  //下移隐藏
                    isBottomShow = false;
                    llDetailBottom.animate().translationY(llDetailBottom.getHeight());
                } else if(scrollY - oldScrollY < 0 && !isBottomShow){    //上移出现
                    isBottomShow = true;
                    llDetailBottom.animate().translationY(0);
                }
            }
        });

        ((ColumnShowPresenter) persenter).getTalk(id);
        viewToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && viewMain.canGoBack()) {
            viewMain.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected IPersenter initPersenter() {
        return new ColumnShowPresenter();
    }

    @Override
    public <T> void getHttpResult(T t) {
        ZhihuDetailBean t1 = (ZhihuDetailBean) t;


        String title = t1.getTitle();
        Glide.with(this).load(t1.getImage()).into(ivRibaoParticulars);
        ctl.setTitle(title);
        tvCollapsin.setText(t1.getGa_prefix());
        //折叠前的颜色
        ctl.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
        //折叠后的颜色
        ctl.setCollapsedTitleTextColor(getResources().getColor(R.color.colorBlack));
        WebViewClient webViewClient = new WebViewClient();
        viewMain.setWebViewClient(webViewClient);
        String htmlData = HtmlUtil.createHtmlData(t1.getBody(), t1.getCss(), t1.getJs());
        viewMain.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);



    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ColumnTalkActivity.this,"成功了",Toast.LENGTH_LONG).show();           }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ColumnTalkActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ColumnTalkActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };


    @OnClick({R.id.tv_detail_bottom_like, R.id.tv_detail_bottom_comment, R.id
            .tv_detail_bottom_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_detail_bottom_like:
                break;
            case R.id.tv_detail_bottom_comment:
                Toast.makeText(this, "123", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_detail_bottom_share:

                new ShareAction(this).withText("hello").setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(shareListener).open();
                break;
        }
    }
}
