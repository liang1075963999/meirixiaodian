package read.eyydf.terr.jokecollection.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by liang on 2018/8/10.
 */

public class ShareUtil {
    private static ShareUtil shareUtil;
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private ShareUtil(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("jilu", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public static ShareUtil getInstance(Context context) {
        if (shareUtil == null)
            shareUtil = new ShareUtil(context);
        return shareUtil;
    }

    public void addData(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getData(String key) {
        return sharedPreferences.getString(key, "zanwu");
    }
}
