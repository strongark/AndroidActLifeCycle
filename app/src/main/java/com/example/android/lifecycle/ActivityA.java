/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.lifecycle.util.DownloadImage;
import com.example.android.lifecycle.util.StatusTracker;
import com.example.android.lifecycle.util.Utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Example Activity to demonstrate the lifecycle callback methods.
 */
public class ActivityA extends Activity {

    private String mActivityName;
    private TextView mStatusView;
    private TextView mStatusAllView;
    private StatusTracker mStatusTracker = StatusTracker.getInstance();
    private final String photo_url_str = "http://geosun.sjsu.edu/index/image/large_sjsu_tile.jpg";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        mActivityName = getString(R.string.activity_a);
        mStatusView = (TextView)findViewById(R.id.thread_counter);
        //mStatusAllView = (TextView)findViewById(R.id.status_view_all_a);
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_create));
        mStatusTracker.increaseThreadCounter();
        Utils.printThreadCounter(mStatusView);

        // show The Image in a ImageView
        new DownloadImage((ImageView) findViewById(R.id.img_sjsu_logo))
                .execute(photo_url_str);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_start));
        mStatusTracker.increaseThreadCounter();
        Utils.printThreadCounter(mStatusView);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_restart));
        mStatusTracker.increaseThreadCounter();
        Utils.printThreadCounter(mStatusView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_resume));
        mStatusTracker.increaseThreadCounter();
        Utils.printThreadCounter(mStatusView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_pause));
        mStatusTracker.increaseThreadCounter();
        Utils.printThreadCounter(mStatusView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_stop));
        mStatusTracker.increaseThreadCounter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_destroy));
        mStatusTracker.increaseThreadCounter();
        mStatusTracker.clear();
    }

    public void startDialog(View v) {
        Intent intent = new Intent(ActivityA.this, DialogActivity.class);
        startActivity(intent);
    }

    public void startActivityB(View v) {
        Intent intent = new Intent(ActivityA.this, ActivityB.class);
        startActivity(intent);
    }

    public void finishActivityA(View v) {
        ActivityA.this.finish();
    }

}
