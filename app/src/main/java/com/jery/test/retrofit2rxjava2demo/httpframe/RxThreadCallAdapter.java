package com.jery.test.retrofit2rxjava2demo.httpframe;

import android.support.annotation.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Scheduler;

class RxThreadCallAdapter extends CallAdapter.Factory {

    private RxJavaCallAdapterFactory rxFactory = RxJavaCallAdapterFactory.create();
    private Scheduler subscribeScheduler;
    private Scheduler observerScheduler;

    RxThreadCallAdapter(Scheduler subscribeScheduler, Scheduler observerScheduler) {
        this.subscribeScheduler = subscribeScheduler;
        this.observerScheduler = observerScheduler;
    }

    @SuppressWarnings("unchecked")
    @Override
    public CallAdapter<?, ?> get(@NonNull Type returnType, @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
        CallAdapter<Observable<?>, Observable<?>> callAdapter = (CallAdapter<Observable<?>, Observable<?>>) rxFactory.get(returnType, annotations, retrofit);
        return callAdapter != null ? new ThreadCallAdapter(callAdapter) : null;
    }

    private final class ThreadCallAdapter implements CallAdapter<Observable<?>, Observable<?>> {
        CallAdapter<Observable<?>, Observable<?>> delegateAdapter;

        ThreadCallAdapter(CallAdapter<Observable<?>, Observable<?>> delegateAdapter) {
            this.delegateAdapter = delegateAdapter;
        }

        @Override
        public Type responseType() {
            return delegateAdapter.responseType();
        }

        @Override
        public Observable<?> adapt(@NonNull Call<Observable<?>> call) {
            return delegateAdapter.adapt(call).subscribeOn(subscribeScheduler).observeOn(observerScheduler);
        }
    }
}
