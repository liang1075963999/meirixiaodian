package read.eyydf.terr.jokecollection.tools;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;

public class NetworkConstants {
	/**
	 * Unknown network class
	 */
	public static final int NETWORK_CLASS_UNKNOWN = 0;

	/**
	 * wifi net work
	 */
	public static final int NETWORK_WIFI = 1;

	/**
	 * "2G" networks
	 */
	public static final int NETWORK_CLASS_2_G = 2;

	/**
	 * "3G" networks
	 */
	public static final int NETWORK_CLASS_3_G = 3;

	/**
	 * "4G" networks
	 */
	public static final int NETWORK_CLASS_4_G = 4;

	public static int getNetWorkClass(Activity context) {
		TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext()
				.getSystemService(Context.TELEPHONY_SERVICE);

		switch (telephonyManager.getNetworkType()) {
		case TelephonyManager.NETWORK_TYPE_GPRS:
		case TelephonyManager.NETWORK_TYPE_EDGE:
		case TelephonyManager.NETWORK_TYPE_CDMA:
		case TelephonyManager.NETWORK_TYPE_1xRTT:
		case TelephonyManager.NETWORK_TYPE_IDEN:
			return NetworkConstants.NETWORK_CLASS_2_G;

		case TelephonyManager.NETWORK_TYPE_UMTS:
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
		case TelephonyManager.NETWORK_TYPE_EVDO_A:
		case TelephonyManager.NETWORK_TYPE_HSDPA:
		case TelephonyManager.NETWORK_TYPE_HSUPA:
		case TelephonyManager.NETWORK_TYPE_HSPA:
		case TelephonyManager.NETWORK_TYPE_EVDO_B:
		case TelephonyManager.NETWORK_TYPE_EHRPD:
		case TelephonyManager.NETWORK_TYPE_HSPAP:
			return NetworkConstants.NETWORK_CLASS_3_G;

		case TelephonyManager.NETWORK_TYPE_LTE:
			return NetworkConstants.NETWORK_CLASS_4_G;

		default:
			return NetworkConstants.NETWORK_CLASS_UNKNOWN;
		}
	}

	public static int getNetWorkStatus(Activity context) {
		int netWorkType = NetworkConstants.NETWORK_CLASS_UNKNOWN;

		ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		android.net.NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {
			int type = networkInfo.getType();

			if (type == ConnectivityManager.TYPE_WIFI) {
				netWorkType = NetworkConstants.NETWORK_WIFI;
			} else if (type == ConnectivityManager.TYPE_MOBILE) {
				netWorkType = getNetWorkClass(context);
			}
		}

		return netWorkType;
	}

}
