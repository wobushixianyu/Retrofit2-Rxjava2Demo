package com.jery.test.retrofit2rxjava2demo.httpframe;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

public class AuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();

        //统一添加的header
        String builder = "";

        Timber.d("======>Authorization :" + builder);

        Request newRequest = oldRequest.newBuilder()
                .addHeader("Authorization",builder)
                .method(oldRequest.method(),oldRequest.body())
                .build();

        return chain.proceed(newRequest);
    }
}
