package com.lenovo.example.zhihu_project.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.DisplayMetrics;

import com.lenovo.example.zhihu_project.apps.MyApp;


public class SystemUtils {

    /**
     * 检查是否有网络
     * @return
     */
    public static boolean checkNetWork(){
        ConnectivityManager manager = (ConnectivityManager) MyApp.myApp.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null;
    }

    /**
     * 当前是否是wifi链接
     * @return
     */
    public static boolean isWifiConnected(){
        ConnectivityManager manager = (ConnectivityManager) MyApp.myApp.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return info != null;
    }

    /**
     * 检查手机（4,3,2）G是否链接
     */
    public static boolean isMobileNetworkConnected(){
        ConnectivityManager manager = (ConnectivityManager) MyApp.myApp.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return info != null;
    }

    public static long getSystemTime(){
        return System.currentTimeMillis();
    }

    /**
     * 获取屏幕的dpi
     * @param at
     * @return
     */
    public static int getDpi(Activity at){
        DisplayMetrics dm = new DisplayMetrics();
        at.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.densityDpi;
    }


    /**
     * 获取包名
     * @param context
     * @return
     */
    public static String getPgName(Context context){
        return context.getPackageName();
    }

    /**
     * 获取版本号
     * @param context
     * @return
     */
    public static Long getVersionCode(Context context, String pg){
        PackageInfo pgInfo = null;
        try {
            pgInfo = context.getPackageManager().getPackageInfo(pg,0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return Long.valueOf(pgInfo.versionCode);
        }else{

//            return pgInfo.getLongVersionCode();
            return  null;

        }
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dp2px(float dpValue) {
        final float scale = MyApp.myApp.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2dp(float pxValue) {
        final float scale = MyApp.myApp.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }




}
