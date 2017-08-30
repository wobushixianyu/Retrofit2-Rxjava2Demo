package com.jery.test.retrofit2rxjava2demo.httpframe;

import com.jery.test.retrofit2rxjava2demo.httpframe.model.Response;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface ProjectService {

    @FormUrlEncoded
    @POST("api/v2/sms/login/send")
    Observable<Response> sendSmsCode(@FieldMap Map<String, Object> params);
}
