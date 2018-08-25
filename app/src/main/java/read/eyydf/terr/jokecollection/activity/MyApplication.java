package read.eyydf.terr.jokecollection.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;

import org.xutils.x;

import java.util.LinkedList;
import java.util.List;

public class MyApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        x.Ext.init(this);// Xutils初始化
        //FeedbackAPI.init(MyApplication.this, "24968010", "58a9e34745345a5a2ce3d1fc549b0912");

    }

    public static Context getMyApplicationContext() {
        return context;
    }

    private static List<Activity> mList = new LinkedList<Activity>();

    public static void addActivity(Activity activity) {
        mList.add(activity);
    }

    public static void exit(Activity a) {

        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MobclickAgent.onKillProcess(a);
            System.exit(0);
        }
    }
}
