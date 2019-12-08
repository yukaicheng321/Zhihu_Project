package com.lenovo.example.zhihu_project.constants;




import com.lenovo.example.zhihu_project.apps.MyApp;

import java.io.File;

public class Constant {

    public static final String Base_Url = "https://cdwan.cn/api/";

    public static final String Base_apk_url = "http://yun918.cn/study/public/";

    public static final String Base_Wan_url = "https://www.wanandroid.com/";



    //网络缓存的地址
    public static final String PATH_DATA = MyApp.myApp.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/ShiXun";


    public static final int CLICK_TIME = 2000;

    public static final String KEY_API = "52b7ec3471ac3bec6846577e79f20e4c";
    public static int uid;
    public static final String IT_VTEX_NODE_NAME = "vtex_node_name";
    public static final String IT_VTEX_TYPE = "vtex_type";
    public static final String IT_VTEX_TOPIC_ID = "vtex_id";
    public static final String TECH_ANDROID="Android";
    public static final String TECH_IOS="iOS";
    public static final String TECH_FIRST="前端";
    public static final String TECH_FULI="福利";

}
