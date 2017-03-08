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

package com.example.android.lifecycle.util;

import android.os.Handler;
import android.widget.TextView;

import java.util.List;

public class Utils {

    private static StatusTracker mStatusTracker = StatusTracker.getInstance();

    /**
     * Helper method to print out the lifecycle state of each Activity.  Note this has
     * been wrapped in a Handler to delay the output due to overlaps in lifecycle state
     * changes as one Activity launches another.
     *
     * @param viewMethods TextView to list out the lifecycle methods called
     * @param viewStatus  TextView to list out the status of all Activity classes
     * @link http://developer.android.com/guide/topics/fundamentals/activities.html#CoordinatingActivities
     */
    public static void printStatus(final TextView viewMethods, final TextView viewStatus) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Get the stack of Activity lifecycle methods called and print to TextView
                StringBuilder sbMethods = new StringBuilder();
                List<String> listMethods = mStatusTracker.getMethodList();
                for (String method : listMethods) {
                    sbMethods.insert(0, method + "\r\n");
                }
                if (viewMethods != null) {
                    viewMethods.setText(sbMethods.toString());
                }

                // Get the status of all Activity classes and print to TextView
                StringBuilder sbStatus = new StringBuilder();
                for (String key : mStatusTracker.keySet()) {
                    sbStatus.insert(0, key + ": " + mStatusTracker.getStatus(key) + "\n");
                }
                if (viewStatus != null) {
                    viewStatus.setText(sbStatus.toString());
                }
            }
        }, 750);
    }

    public static void printThreadCounter(final TextView viewThreadCounter) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                // format the counter to 000X
                StringBuilder sbStatus = new StringBuilder();

                sbStatus.append(mStatusTracker.getThreadCounter());
                while(sbStatus.length()<4)
                    sbStatus.insert(0,"0");
                if(viewThreadCounter != null) {
                    viewThreadCounter.setText(sbStatus.toString());
                }
            }
        }, 750);
    }
}




