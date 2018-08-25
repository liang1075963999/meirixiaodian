package read.eyydf.terr.jokecollection.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

public class DeviceUtils {
    public static int screenLarge = 0;
    public static int screenSmall = 0;
    public static int statebar = 0;
    public static String packageName;
    public static String version;
    public static int screenH = 0;
    public static int screenW = 0;
    public static int DeviceOrientation;
    private static Activity activity;

    public static int packageCode(Context context) {
        PackageManager manager = context.getPackageManager();
        int code = 0;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }

    @SuppressLint("NewApi")
    public static void init(Activity a) {
        activity = a;
        packageName = a.getPackageName();
        PackageManager pm = a.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(a.getPackageName(), 0);
            version = pi.versionName;
        } catch (NameNotFoundException e) {
        }
        Rect rectangle = new Rect();
        a.getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);

        statebar = rectangle.top;
        statebar = 0;
        DisplayMetrics metric = new DisplayMetrics();
        a.getWindowManager().getDefaultDisplay().getRealMetrics(metric);
        if (getDeviceOrientation(a) == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            screenLarge = metric.widthPixels;
            screenSmall = metric.heightPixels - statebar;
            screenH = screenSmall;
            screenW = screenLarge;
        } else {
            screenSmall = metric.widthPixels;
            screenLarge = metric.heightPixels - statebar;
            screenH = screenLarge;
            screenW = screenSmall;
        }
    }

    // 获取手机状态栏高度
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    public static int getDeviceH() {
        return screenH;
    }

    public static int getDeviceW() {
        return screenW;
    }

    public static String getIMEI() {
        TelephonyManager tm = (TelephonyManager) activity.getSystemService(Activity.TELEPHONY_SERVICE);
        if (tm != null) {
            return tm.getDeviceId();
        }
        return "";
    }

    public static String getAndroidID() {
        return Secure.getString(activity.getContentResolver(), Secure.ANDROID_ID);
    }

    @SuppressWarnings("unused")
    public static int getDeviceHOld(Activity a) {
        DisplayMetrics metric = new DisplayMetrics();
        a.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;
        int height = metric.heightPixels;

        Rect rectangle = new Rect();
        a.getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);

        return height - rectangle.top;
    }

    @SuppressWarnings("unused")
    public static int getDeviceWOld(Activity a) {
        DisplayMetrics metric = new DisplayMetrics();
        a.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;
        int height = metric.heightPixels;
        return width;
    }

    public static int getDeviceOrientation(Activity a) {
        int orient = a.getRequestedOrientation();
        if (orient == ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                || orient == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                || orient == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
                || orient == ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE) {
            DeviceOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
            return ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        } else {
            DeviceOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
            return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }
    }

    public static int getDeviceOrientation() {
        return DeviceOrientation;
    }

    public static boolean getDeviceInstalledApk(Activity a, String packageName) {
        PackageManager packageManager = a.getPackageManager();
        List<PackageInfo> list = packageManager.getInstalledPackages(0);
        for (PackageInfo p : list) {
            if (packageName.equals(p.applicationInfo.packageName)) {
                return true;
            }
        }
        return false;
    }

    public static void openBrowser(final Activity a, final String str) {
        a.runOnUiThread(new Runnable() {
            public void run() {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(str));
                a.startActivity(intent);
            }
        });

    }

    public static String getRandomString(int length) { // length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static void installApp(final Activity a, final String str) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");

        a.startActivity(intent);

    }

    public static void toast(final Activity a, final String str) {
        a.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(a.getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static int dip2px(Context contxet, float dpValue) {
        final float scale = contxet.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 保存方法
     */
    public static void saveBitmap(String path, Bitmap bitmap) {
        File f = new File(path);
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
