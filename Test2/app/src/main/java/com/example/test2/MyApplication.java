package com.example.test2;

import android.app.Application;
import android.content.Intent;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(MyApplication.this, MainActivity.class);
        startActivity(intent);
    }
}
