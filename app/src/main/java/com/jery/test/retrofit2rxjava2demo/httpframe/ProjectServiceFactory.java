package com.jery.test.retrofit2rxjava2demo.httpframe;

import com.google.gson.GsonBuilder;
import com.jery.test.retrofit2rxjava2demo.BuildConfig;
import com.jery.test.retrofit2rxjava2demo.httpframe.converter.GsonConverterFactory;
import com.jery.test.retrofit2rxjava2demo.utils.ProjectUtils;
import com.orhanobut.hawk.Hawk;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ProjectServiceFactory {
    public static ProjectService makeBourbonService() {
        OkHttpClient okHttpClient = makeOkHttpClient(makeLoggingInterceptor());
        return makeDzgService(okHttpClient);
    }

    public static ProjectService makeBourbonService2() {
        OkHttpClient okHttpClient = makeOkHttpClient(makeLoggingInterceptor());
        return makeDzgService2(okHttpClient);
    }

    public static ProjectService makeBourbonService3() {
        OkHttpClient okHttpClient = makeOkHttpClient2(makeLoggingInterceptor());
        return makeDzgService2(okHttpClient);
    }

    private static ProjectService makeDzgService(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.HTTP_BASE)
                .client(okHttpClient)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()))
                .addCallAdapterFactory(new RxThreadCallAdapter(Schedulers.io(), AndroidSchedulers.mainThread()))
                .build();
        return retrofit.create(ProjectService.class);
    }

    private static ProjectService makeDzgService2(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.HTTP_BASE)
                .client(okHttpClient)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()))
                .addCallAdapterFactory(new RxThreadCallAdapter(Schedulers.io(), AndroidSchedulers.mainThread()))
                .build();
        return retrofit.create(ProjectService.class);
    }

    private static OkHttpClient makeOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        BasicParamsInterceptor interceptor = new BasicParamsInterceptor.Builder()
                .addParamsMap(ProjectUtils.getCommonParameter()) //添加公共参数
                .build();
        boolean isRelease = Hawk.get("IS_RELEASE", false);
        if (isRelease) {
            return new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(interceptor)
                    .addInterceptor(new AuthInterceptor())
                    .connectTimeout(20, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS)
                    .build();
        } else {
            return new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(interceptor)
                    .addInterceptor(new AuthInterceptor())
                    .proxy(Proxy.NO_PROXY)
                    .connectTimeout(20, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS)
                    .build();
        }
    }

    private static OkHttpClient makeOkHttpClient2(HttpLoggingInterceptor httpLoggingInterceptor) {
        boolean isRelease = Hawk.get("IS_RELEASE", false);
        if (isRelease) {
            return new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(new AuthInterceptor())
                    .connectTimeout(20, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS)
                    .build();
        } else {
            return new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(new AuthInterceptor())
                    .proxy(Proxy.NO_PROXY)
                    .connectTimeout(20, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS)
                    .build();
        }
    }

    private static HttpLoggingInterceptor makeLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }
}
