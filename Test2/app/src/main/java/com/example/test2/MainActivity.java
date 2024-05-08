package com.example.test2;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.test2.ui.login.LoginActivity;

import java.util.List;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivitya_main);
        findViewById(R.id.mainBtn).setOnClickListener(v -> {
            Log.i("12", "123");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.AppTask> appTasks =
                    am.getAppTasks();
            for (ActivityManager.AppTask appTask : appTasks) {
                Log.i("apptask", appTask.getTaskInfo().toString());
            }
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {
                Log.i("apptask", runningAppProcess.processName);
            }
            Intent intent1 = new Intent();
//            intent1.setClassName("com.example.helloworldapp1", "com.example.helloworldapp1.MainActivity");
            intent1.setComponent(new ComponentName("com.example.helloworldapp1", "com.example.helloworldapp1.MainActivity"));
            List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(intent1, 0);
                Log.i("apptask", "" + resolveInfos.size());
                Log.i("apptask", "" + resolveInfos.get(0).toString());
        });
    }
}
