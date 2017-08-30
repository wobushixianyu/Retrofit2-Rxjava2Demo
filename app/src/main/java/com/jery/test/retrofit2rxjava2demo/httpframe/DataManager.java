package com.jery.test.retrofit2rxjava2demo.httpframe;

import android.support.annotation.NonNull;

import com.jery.test.retrofit2rxjava2demo.httpframe.model.Response;

import java.util.Map;

import rx.Observable;

public final class DataManager {
    private ProjectService mProjectService;
    private ProjectService mProjectService2;

    public DataManager() {
        mProjectService = ProjectServiceFactory.makeBourbonService();
        mProjectService2 = ProjectServiceFactory.makeBourbonService3();
    }

    public DataManager(boolean isNoCustomGson) {
        if (null == mProjectService2) {
            mProjectService2 = ProjectServiceFactory.makeBourbonService3();
        }
        if (isNoCustomGson) {
            mProjectService = ProjectServiceFactory.makeBourbonService2();
        } else {
            mProjectService = ProjectServiceFactory.makeBourbonService();
        }
    }

    public Observable<Response> sendSmsCode(@NonNull Map<String, Object> params) {
        return mProjectService.sendSmsCode(params);
    }
}
