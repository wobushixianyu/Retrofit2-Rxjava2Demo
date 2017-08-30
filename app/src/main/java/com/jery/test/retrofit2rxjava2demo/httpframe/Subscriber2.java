package com.jery.test.retrofit2rxjava2demo.httpframe;

import com.google.gson.JsonSyntaxException;


import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;
import timber.log.Timber;


public abstract class Subscriber2<T> extends Subscriber<T> {

    private boolean isSign = false;

    protected Subscriber2() {
    }

    protected Subscriber2(boolean sign) {
        this.isSign = sign;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        Timber.e(e);
        String errMeg = e.getMessage();
        //  {"ResultCode":1005,"Message":"服务器内部错误:网络错误:错误代码null,sSmsQryScL,CRM_TEMPLATE_31A1_TAEC126CRM1，服务调用出错：TPENOENT(6):0:0:TPED_MINVAL(0):QMNONE(0):0","Data":""}
        if (e instanceof SocketTimeoutException) {
            errMeg = "网络响应超时，请稍后再试！";// 网络超时
        } else if (e instanceof ConnectException) {
            errMeg = "网络链接失败，请检查网络连接！";// 网络中段
        } else if (e instanceof JsonSyntaxException) {
            errMeg = "与服务器通信异常，请联系相应管理员！";// Gson 类型转换错误
        } else if (e instanceof retrofit2.HttpException) {
            errMeg = "与服务器通信异常，请联系相应管理员！";// 没有该接口-404
        }
    }
}
