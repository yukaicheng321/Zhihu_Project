package com.lenovo.example.zhihu_project.model;

import android.util.Log;


import com.lenovo.example.zhihu_project.constants.Constant;
import com.lenovo.example.zhihu_project.model.api.GanHuoApis;
import com.lenovo.example.zhihu_project.model.api.VtexApis;
import com.lenovo.example.zhihu_project.model.api.WeixinApis;
import com.lenovo.example.zhihu_project.model.api.ZhihuApis;
import com.lenovo.example.zhihu_project.utils.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    private static volatile HttpManager httpManager;
    private static VtexApis vtexApis;

    public static HttpManager getInstance(){
        if(httpManager == null){
            synchronized (HttpManager.class){
                if(httpManager == null) httpManager = new HttpManager();
            }
        }
        return httpManager;
    }

    private static Cache cache;

    private static ZhihuApis myApi;

    private static WeixinApis weixinApis;
    //知乎接口
    private static ZhihuApis zhihuApi;

    private static GanHuoApis ganHuoApis;

    //创建Retrofit对象
    private static Retrofit getRetrofit(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkhttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    /**
     * 创建带缓存的HttpClient对象
     * @return
     */
    private static OkHttpClient getOkhttpClient() {
        //本地缓存文件
        File file = new File(Constant.PATH_CACHE);
        //设置缓存文件的大小100M
        cache = new Cache(file, 1024 * 1024 * 100);
        return new OkHttpClient.Builder()
                .cache(cache)  //缓存
                .addInterceptor(new LoggingInterceptor())   //日志拦截器
                .addNetworkInterceptor(new Myintercepter())   //网络拦截器
                .addInterceptor(new HeaderInterceptor())
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .cookieJar(cookieJar)
                .connectTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    static class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request build = chain.request().newBuilder()
                    .header("Connection","keep-alive")
                    .build();
            return chain.proceed(build);
        }
    }

    //日志拦截器
    static class LoggingInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            long t1 = System.nanoTime();
            Log.i("interceptor", String.format("Sending request %s on %s%n%s",request.url(),chain.connection(),request.headers()));

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.i("Received:", String.format("Received response for %s in %.1fms%n%s",response.request().url(),(t2-t1)/1e6d,response.headers()));
            return response;
        }
    }


    //获取相关的网络接口 baseURL基础地址  tCla 用来做数据加载的接口类
    private static synchronized <T> T getServerApis(String baseUrl, Class<T> tCla){
        return getRetrofit(baseUrl).create(tCla);
    }


    /**
     * 获取api接口
     * @return
     */
    public static ZhihuApis getMyApi(){
        synchronized (HttpManager.class){
            if(myApi == null){
                synchronized (HttpManager.class){
                    myApi = getServerApis(Constant.Base_apk_url,ZhihuApis.class);
                }
            }
        }
        return myApi;
    }

    /**
     * 获取知乎日报api接口
     * @return
     */
    public static ZhihuApis getZhihuApi(){
        synchronized (HttpManager.class){
            if(zhihuApi == null){
                synchronized (HttpManager.class){
                    zhihuApi = getServerApis(ZhihuApis.HOST,ZhihuApis.class);
                }
            }
        }
        return zhihuApi;
    }

    /**
     * 获取知乎日报api接口
     * @return
     */
    public static WeixinApis getWeixinApi(){
        synchronized (HttpManager.class){
            if(weixinApis == null){
                synchronized (HttpManager.class){
                    weixinApis = getServerApis(weixinApis.HOST,WeixinApis.class);
                }
            }
        }
        return weixinApis;
    }
    /**
     * 获取干货的接口
     * @return
     */
    public static GanHuoApis getGanHuoApis(){
        synchronized (HttpManager.class){
            if(ganHuoApis == null){
                synchronized (HttpManager.class){
                    ganHuoApis = getServerApis(ganHuoApis.HOST,GanHuoApis.class);
                }
            }
        }
        return ganHuoApis;
    }



    public static VtexApis getV2Node(){
        synchronized (HttpManager.class){
            if(vtexApis == null){
                synchronized (HttpManager.class){
                    vtexApis = getServerApis(VtexApis.HOST,VtexApis.class);
                }
            }
        }
        return vtexApis;
    }
    //拦截器的实现类
    private static class Myintercepter implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if(!SystemUtils.checkNetWork()){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            //通过判断网络连接是否存在获取本地或者服务器的数据
            if(!SystemUtils.checkNetWork()){
                int maxAge = 0;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public ,max-age="+maxAge).build();
            }else{
                int maxStale = 60*60*24*28; //设置缓存数据的保存时间
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public, onlyif-cached, max-stale="+maxStale).build();
            }

        }
    }

    /**
     * Cookie设置
     */
    private static CookieJar cookieJar = new CookieJar() {

        private final Map<String, List<Cookie>> cookieMap = new HashMap<String,List<Cookie>>();

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            String host = url.host();
            List<Cookie> cookieList = cookieMap.get(host);
            if(cookieList != null){
                cookieMap.remove(host);
            }
            cookieMap.put(host,cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookieList = cookieMap.get(url.host());
            return cookieList != null ? cookieList : new ArrayList<Cookie>();
        }
    };

}
