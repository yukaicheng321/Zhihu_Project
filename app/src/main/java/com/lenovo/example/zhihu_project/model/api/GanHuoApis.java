package com.lenovo.example.zhihu_project.model.api;

import com.lenovo.example.zhihu_project.model.bean.GankHttpResponse;
import com.lenovo.example.zhihu_project.model.bean.GankItemBean;
import com.lenovo.example.zhihu_project.model.bean.GankSearchItemBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lenovo on 2019/9/9.
 */

public interface GanHuoApis {
    String HOST = "http://gank.io/api/";

    /**
     * 技术文章列表
     */
    @GET("data/{tech}/{num}/{page}")
    Flowable<GankHttpResponse<List<GankItemBean>>> getTechList(@Path("tech") String tech, @Path("num") int num, @Path("page") int page);

    /**
     * 妹纸列表
     */
    @GET("data/福利/{num}/{page}")
    Flowable<GankHttpResponse<List<GankItemBean>>> getGirlList(@Path("num") int num, @Path("page") int page);

    /**
     * 随机妹纸图
     */
    @GET("random/data/福利/{num}")
    Flowable<GankHttpResponse<List<GankItemBean>>> getRandomGirl(@Path("num") int num);

    /**
     * 搜索
     */
    @GET("search/query/{query}/category/{type}/count/{count}/page/{page}")
    Flowable<GankHttpResponse<List<GankSearchItemBean>>> getSearchList(@Path("query") String query, @Path("type") String type, @Path("count") int num, @Path("page") int page);

}
