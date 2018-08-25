package read.eyydf.terr.jokecollection.tools;

import android.content.Context;
import android.graphics.Typeface;

public class FontCustom {
	static String fongUrl = "ziti.OTF";
	static Typeface tf;

	/***
	 * 设置字体
	 *
	 * @return
	 */
	public static Typeface setFont(Context context) {
		if (tf == null) {
			tf = Typeface.createFromAsset(context.getAssets(), fongUrl);
		}
		return tf;
	}
}
