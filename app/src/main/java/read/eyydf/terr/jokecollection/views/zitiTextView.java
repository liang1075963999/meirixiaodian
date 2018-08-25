package read.eyydf.terr.jokecollection.views;

import android.content.Context;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author ccheng
 * @Date 3/18/14
 */
public class zitiTextView extends TextView {
	private TextPaint paint;

	public zitiTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//paint = getPaint();
		//paint.setTypeface(FontCustom.setFont(context));
	}

	public zitiTextView(Context context) {
		super(context);
	//	paint = getPaint();
		//paint.setTypeface(FontCustom.setFont(context));
	}
}
