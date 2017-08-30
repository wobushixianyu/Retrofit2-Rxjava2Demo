package com.jery.test.retrofit2rxjava2demo.activity;

import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jery.test.retrofit2rxjava2demo.R;
import com.jery.test.retrofit2rxjava2demo.httpframe.DataManager;
import com.jery.test.retrofit2rxjava2demo.httpframe.Subscriber2;
import com.jery.test.retrofit2rxjava2demo.httpframe.model.Response;

import java.util.Map;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //HttpRequest();
    }

    private void HttpRequest() {
        DataManager mDataManager = new DataManager();
        Map<String, Object> params = new ArrayMap<>();
        params.put("phoneNumber", "18280108248");
        params.put("password", "108248");
        mDataManager.sendSmsCode(params).subscribe(new Subscriber2<Response>() {
            @Override
            public void onCompleted() {
                super.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onNext(Response response) {
                Timber.i("======>test: "+response.toString());
            }
        });
    }
}
