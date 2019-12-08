package com.lenovo.example.zhihu_project.model.api;

import com.lenovo.example.zhihu_project.model.bean.WXHttpResponse;
import com.lenovo.example.zhihu_project.model.bean.WXItemBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2019/9/8.
 */

public interface WeixinApis {

    //    String HOST = "http://apis.baidu.com/txapi/weixin/";
    String HOST = "http://api.tianapi.com/";

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Flowable<WXHttpResponse<List<WXItemBean>>> getWXHot(@Query("key") String key, @Query("num") int num, @Query("page") int page);

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Flowable<WXHttpResponse<List<WXItemBean>>> getWXHotSearch(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);


}
