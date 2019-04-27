package com.github.retrofitlib;

import android.os.Bundle;
import android.widget.TextView;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.json.JSONObject;

public class MainActivity extends RxAppCompatActivity {
    private LifecycleTransformer lifecycleTransformer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv_userinfo = findViewById(R.id.tv_userinfo);

        lifecycleTransformer = bindToLifecycle();
        String phone = "18611378920";
        String authcode = "1234";
        MyPresenter.login(lifecycleTransformer, phone, authcode, "normal", null, new MyPresenter.LoginCallback() {
            @Override
            public void callback(JSONObject joResult) {
                tv_userinfo.setText(joResult.toString());
            }
        });
    }
}
