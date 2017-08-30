package com.jery.test.retrofit2rxjava2demo.utils;

import android.support.v4.util.ArrayMap;

import com.jery.test.retrofit2rxjava2demo.BuildConfig;

import timber.log.Timber;

/**
 * Created by jery on 2017/7/18.
 */

public class ProjectUtils {

    public static ArrayMap<String, String> getCommonParameter() {
        ArrayMap<String, String> params = new ArrayMap<>();
        params.put("origin", "1");
        params.put("v", String.valueOf(BuildConfig.VERSION_CODE));
        Timber.d("====params：" + params.toString());
        return params;
    }
}
