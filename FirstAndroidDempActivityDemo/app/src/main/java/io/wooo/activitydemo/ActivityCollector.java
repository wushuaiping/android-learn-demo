package io.wooo.activitydemo;

import android.app.Activity;
import android.os.Process;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();
        int pid = Process.myPid();
        Process.killProcess(pid);
        Log.e("ActivityCollector", "kill pid: " + pid);
    }
}
