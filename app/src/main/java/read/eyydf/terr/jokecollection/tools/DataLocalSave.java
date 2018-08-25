package read.eyydf.terr.jokecollection.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 本地存放工具类
 * @author fenghu
 */
public class DataLocalSave {
	/**
	 * 存放int
	 */
	public static void saveInt(final Context a, int uerid, String str) {
		SharedPreferences share = a.getSharedPreferences(str, Context.MODE_PRIVATE);
		SharedPreferences.Editor edit = share.edit();
		edit.putInt(str, uerid);
		edit.commit();
	}

	/**
	 * 获取int
	 */
	public static int getInt(final Context a, String str) {
		SharedPreferences share = a.getSharedPreferences(str, Context.MODE_PRIVATE);
		return share.getInt(str, -1);
	}
	
	/**
	 * 存放String
	 */
	public static void saveString(final Context a, String uerid, String str) {
		SharedPreferences share = a.getSharedPreferences(str, Context.MODE_PRIVATE);
		SharedPreferences.Editor edit = share.edit();
		edit.putString(str, uerid);
		edit.commit();
	}

	/**
	 * 获取String
	 */
	public static String getString(final Context a, String str) {
		SharedPreferences share = a.getSharedPreferences(str, Context.MODE_PRIVATE);
		return share.getString(str,"null");
	}
}
