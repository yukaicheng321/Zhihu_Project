package com.lenovo.example.zhihu_project.apps;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.lenovo.example.zhihu_project.greendao.DaoMaster;
import com.lenovo.example.zhihu_project.greendao.DaoSession;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;


public class MyApp extends Application {

    private static String DB_NAME = "1901A";


    public static MyApp myApp;
    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        initUmeng();
        initDao();




    }

    private void initDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "my.db", null);
        SQLiteDatabase readableDatabase = devOpenHelper.getReadableDatabase();
        DaoMaster daoMaster = new DaoMaster(readableDatabase);
        daoSession = daoMaster.newSession();


    }

    private void initUmeng() {
        UMConfigure.init(this,"5d76719a0cafb2f33d0000d5"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
        PlatformConfig.setPinterest("1439206");
        PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
        PlatformConfig.setVKontakte("5764965","5My6SNliAaLxEm3Lyd9J");
        PlatformConfig.setDropbox("oz8v5apet3arcdy","h7p2pjbzkkxt02a");
    }
}
