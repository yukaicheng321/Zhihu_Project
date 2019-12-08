package com.lenovo.example.zhihu_project;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.lenovo.example.zhihu_project.fragment.V2exFragment.V2emFragment;
import com.lenovo.example.zhihu_project.fragment.collect.BlankFragment;
import com.lenovo.example.zhihu_project.fragment.ganhuo.GanhuoFragment;
import com.lenovo.example.zhihu_project.fragment.juejin.JuejinFragment;
import com.lenovo.example.zhihu_project.fragment.weixin.WeixinFragment;
import com.lenovo.example.zhihu_project.fragment.zhihu.ZhihuFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.umeng.socialize.UMShareAPI;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener {

    @BindView(R.id.tv_tool)
    TextView tvTool;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav)
    NavigationView nav;
    @BindView(R.id.dt)
    DrawerLayout dt;
    @BindView(R.id.msv_main)
    MaterialSearchView msvMain;
    private MenuItem item_search;
    private ZhihuFragment zhihuFragment;
    private WeixinFragment weixinFragment;
    private V2emFragment v2emFragment;
    private GanhuoFragment ganhuoFragment;
    private JuejinFragment juejinFragment;
    private int crrentFragment;
    private BlankFragment collectFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initPremission();
        setupMain();
        setupNavigation();
        setupMsvListener();//设置搜索框监听
    }

    private void initPremission() {
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }

    }

    private void initFragment() {

        zhihuFragment = new ZhihuFragment();
        weixinFragment = new WeixinFragment();
        v2emFragment = new V2emFragment();
        ganhuoFragment = new GanhuoFragment();
        juejinFragment = new JuejinFragment();
        collectFragment = new BlankFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.ft_main,zhihuFragment)

                .add(R.id.ft_main,weixinFragment)
                .add(R.id.ft_main,v2emFragment)
                .add(R.id.ft_main,juejinFragment)
                .add(R.id.ft_main,ganhuoFragment)
                .add(R.id.ft_main,collectFragment)
                .show(zhihuFragment)
                .hide(weixinFragment)
                .hide(v2emFragment)
                .hide(juejinFragment)
                .hide(ganhuoFragment)
                .hide(collectFragment)
                .commit();

    }

    private void setupMsvListener() {

        msvMain.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //按下监听
            }

            @Override
            public void onSearchViewClosed() {
                //关闭监听

            }
        });

        msvMain.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //提交监听 找到当前打开的界面 然后传入用户输入数据
                if (crrentFragment == R.id.nav_weixin) {
                    weixinFragment.searchWord(query);
                }
                if (crrentFragment ==R.id.nav_ganhuo) {
                    ganhuoFragment.searchWord(query);
                }



                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //输入监听

                return false;
            }
        });


    }

    @Override
    public void onBackPressed() {
        //点击回退键 关闭当前打开的搜索窗口
        if (msvMain.isSearchOpen()) {
            msvMain.closeSearch();
        }else{

            super.onBackPressed();
        }


    }

    private void setupNavigation() {
        nav.setNavigationItemSelectedListener(this);

    }

    private void setupMain() {
        setSupportActionBar(toolbar);
        //取消app标题名字
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dt, toolbar, 0, 0);
        toggle.syncState();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        boolean checked;

        int id = item.getItemId();
        crrentFragment=id;
        switch (id) {
            case R.id.nav_zhihu:
                CharSequence title = item.getTitle();
                tvTool.setText(title);
                item_search.setVisible(false);
                checked = item.isChecked();
                if (checked) {
                    item.setChecked(true);
                }else{
                    item.setChecked(false);
                }

                getSupportFragmentManager().beginTransaction()
                        .show(zhihuFragment)
                        .hide(juejinFragment)
                        .hide(v2emFragment)
                        .hide(ganhuoFragment)
                        .hide(weixinFragment)
                        .hide(collectFragment)
                        .commit();
                break;
            case R.id.nav_weixin:
                CharSequence weixin = item.getTitle();
                tvTool.setText(weixin);
                item_search.setVisible(true);
                checked = item.isChecked();
                if (checked) {
                    item.setChecked(true);
                }else{
                    item.setChecked(false);
                }
                getSupportFragmentManager().beginTransaction()
                        .show(weixinFragment)
                        .hide(juejinFragment)
                        .hide(v2emFragment)
                        .hide(ganhuoFragment)
                        .hide(zhihuFragment)
                        .hide(collectFragment)
                        .commit();
                break;
            case R.id.nav_ganhuo:

                CharSequence ganhuo = item.getTitle();
                tvTool.setText(ganhuo);
                item_search.setVisible(true);
                checked = item.isChecked();
                if (checked) {
                    item.setChecked(true);
                }else{
                    item.setChecked(false);
                }
                getSupportFragmentManager().beginTransaction()
                        .show(ganhuoFragment)
                        .hide(juejinFragment)
                        .hide(v2emFragment)
                        .hide(weixinFragment)
                        .hide(zhihuFragment)
                        .hide(collectFragment)
                        .commit();

                break;
            case R.id.nav_v2ex:
                CharSequence v2ex = item.getTitle();
                tvTool.setText(v2ex);
                item_search.setVisible(false);
                checked = item.isChecked();
                if (checked) {
                    item.setChecked(true);
                }else{
                    item.setChecked(false);
                }
                getSupportFragmentManager().beginTransaction()
                        .show(v2emFragment)
                        .hide(juejinFragment)
                        .hide(weixinFragment)
                        .hide(ganhuoFragment)
                        .hide(zhihuFragment)
                        .hide(collectFragment)
                        .commit();
                break;
            case R.id.nav_juejin:
                CharSequence juejin = item.getTitle();
                tvTool.setText(juejin);
                item_search.setVisible(false);
                checked = item.isChecked();
                if (checked) {
                    item.setChecked(true);
                }else{
                    item.setChecked(false);
                }
                getSupportFragmentManager().beginTransaction()
                        .show(juejinFragment)
                        .hide(v2emFragment)
                        .hide(weixinFragment)
                        .hide(ganhuoFragment)
                        .hide(zhihuFragment)
                        .hide(collectFragment)
                        .commit();
                break;
            case R.id.nav_like:
                CharSequence like = item.getTitle();
                tvTool.setText(like);
                item_search.setVisible(false);
                checked = item.isChecked();
                if (checked) {
                    item.setChecked(true);
                }else{
                    item.setChecked(false);

                }
                getSupportFragmentManager().beginTransaction()
                        .show(collectFragment)
                        .hide(v2emFragment)
                        .hide(weixinFragment)
                        .hide(ganhuoFragment)
                        .hide(zhihuFragment)
                        .hide(juejinFragment)
                        .commit();



                break;
            case R.id.nav_setting:
                CharSequence setting = item.getTitle();
                tvTool.setText(setting);
                break;
            case R.id.nav_aboue:
                CharSequence aboue = item.getTitle();
                tvTool.setText(aboue);
                break;

            default:
                break;
        }

        //设置点击侧滑任一条目隐藏侧滑
        dt.closeDrawer(GravityCompat.START);


        return true;//todo
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option, menu);
        //获取搜索的图片
        item_search = menu.findItem(R.id.search);
        item_search.setVisible(false);//设置不显示
        msvMain.setMenuItem(item_search);//设置关联

        return super.onCreateOptionsMenu(menu);
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
