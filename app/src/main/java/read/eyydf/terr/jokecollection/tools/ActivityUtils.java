package read.eyydf.terr.jokecollection.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by fenghu on 2017/5/15.
 */

@SuppressLint("InflateParams")
public class ActivityUtils {
    public static boolean isLog;
    public static String ua;
    public static final String url_request = "http://app.clubx.cn:9080/Appware";
    public static final String url = "http://app.clubx.cn:9080/Appware/apptom/SelectArticle";
    public static final String baikeribaourl = "http://app.clubx.cn:9080/RoflJoke2/apptom/ToArticle";
    public static final String baikeribaotupianurl = "http://m.clubx.cn:9080/app";
    public static int uerid = -1;

    static public String getAppVersion(Activity a) {
        String versionName = "";
        try {
            versionName = a.getPackageManager().getPackageInfo(
                    a.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    static public String getPackageName(Activity a) {
        return a.getPackageName();
    }

    public static int getVersionCode(Activity context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(getPackageName(context), 0);
            //获取包的详细信息
            //获取版本号和版本名称
            System.out.println("版本号：" + info.versionCode);
            System.out.println("版本名称：" + info.versionName);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * [沉浸状态栏]
     */
    @SuppressLint("NewApi")
    public static void initTranslucentStatus(Activity a) {

        // 4.4 全透明状态栏（有的机子是过渡形式的透明）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            a.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // a.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
        // 5.0 全透明实现
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Window window = a.getWindow();
            // window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            // | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            // window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            // | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            // | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            // window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // window.setStatusBarColor(ContextCompat.getColor(a,(R.color.blue)));
            a.getWindow().setStatusBarColor(Color.TRANSPARENT);
            // window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public static void startActivity(Activity a, Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(a, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        a.startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public static void startActivityForResult(Activity a, Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(a, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        a.startActivityForResult(intent, requestCode);
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    public static void showToast(Activity a, String msg) {
        Toast.makeText(a, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * [日志输出]
     *
     * @param msg
     */
    public static void $Log(String msg) {
        if (isLog)
            Log.i("FMSD", msg);
    }


    // 是否有网络，包括2g,3g.wifi
    public static boolean isNetActive(Activity a) {
        boolean flag = false;
        ConnectivityManager manager = (ConnectivityManager) a.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();
        }
        return flag;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static Bitmap getImageFromAssetsFile(Activity a, String fileName) {
        Bitmap image = null;
        AssetManager am = a.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

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

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
