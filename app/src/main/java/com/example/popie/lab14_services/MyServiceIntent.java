package com.example.popie.lab14_services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by popie on 12/21/2017.
 */

public class MyServiceIntent extends IntentService {
    String msg;
    int progress;
    int limit;
    String sLimit;
    private String Tag = "MTAG";

    public MyServiceIntent() {
        super("my_thread");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        for (float i = 1; i <= limit; i++) {
            try {
                Thread.sleep(1000);
                Log.d(Tag, "Service : " + i);
                progress = Math.round((i / limit * 100));
                msg = "" + progress;
                EventBus.getDefault().post(new ProgressEvent(msg));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_SHORT).show();

        sLimit = intent.getStringExtra("limit");
        limit = Integer.parseInt(sLimit);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {

        super.onStart(intent, startId);

    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(), "Service Stopped", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    public final class ProgressEvent {

        String msg;

        public ProgressEvent(String msg) {
            this.msg = msg;
        }
    }


}
