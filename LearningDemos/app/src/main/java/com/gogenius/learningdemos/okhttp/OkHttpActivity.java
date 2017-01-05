package com.gogenius.learningdemos.okhttp;

import android.app.Activity;
import android.os.Bundle;

import com.gogenius.learningdemos.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by shijiwei on 2016/9/17.
 */
public class OkHttpActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        initalizeOkHttp();
    }

    private void initalizeOkHttp() {


        // 第一步
        OkHttpClient mOkHttpClient = new OkHttpClient();


        //第二步
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("username", "德玛西亚");

        Request mRequest = new Request.Builder()
                .url("https://github.com/hongyangAndroid")
                .post(builder.build())
                .build();

        //第三步
        Call mCall = mOkHttpClient.newCall(mRequest);

        //第四步
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });

    }
}
