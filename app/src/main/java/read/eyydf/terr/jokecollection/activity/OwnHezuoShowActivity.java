package read.eyydf.terr.jokecollection.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;

/**
 * Created by fenghu on 2017/5/18.
 */
@ContentView(R.layout.own_hezuo_show_activity)
public class OwnHezuoShowActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	@Event(value = { R.id.image_back, R.id.web_layout1, R.id.web_layout2 }, type = View.OnClickListener.class)
	private void click(View v) {
		ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.web_layout1:
			cm.setPrimaryClip(ClipData.newPlainText("Label", "505152484"));
			showToast("已复制QQ");
			break;
		case R.id.web_layout2:
			cm.setPrimaryClip(ClipData.newPlainText("Label", "shike@apploft.com.cn"));
			showToast("已复制邮箱");
			break;
		}
	}

	public void init() {
		x.view().inject(this);
		ActivityUtils.initTranslucentStatus(this);
		ImageView back=findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
