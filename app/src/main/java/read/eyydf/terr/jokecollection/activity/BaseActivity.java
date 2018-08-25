package read.eyydf.terr.jokecollection.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by fenghu on 2017/5/8.
 */

public abstract class BaseActivity extends AppCompatActivity {
	/**
	 * 是否沉浸状态栏
	 **/
	@SuppressWarnings("unused")
	private boolean isSetStatusBar = false;
	/**
	 * 是否允许全屏
	 **/
	@SuppressWarnings("unused")
	private boolean mAllowFullScreen = false;
	/**
	 * 是否禁止旋转屏幕
	 **/
	@SuppressWarnings("unused")
	private boolean isAllowScreenRoate = false;
	/**
	 * 当前Activity渲染的视图View
	 **/
	@SuppressWarnings("unused")
	private View mContextView = null;
	/**
	 * 是否输出日志信息
	 **/
	private boolean isDebug;
	public boolean isNeed = true;
	private String APP_NAME;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// isDebug = true;
		APP_NAME = "FMSD";
		// $Log(APP_NAME + "-->onCreate()");
		// try {
		// DeviceUtils.init(this);
		// Bundle bundle = getIntent().getExtras();
		// initParms(bundle);
		// mContextView = LayoutInflater.from(this)
		// .inflate(bindLayout(), null);
		// if (mAllowFullScreen) {
		// this.getWindow().setFlags(
		// WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// }
		// if (isSetStatusBar) {
		// initTranslucentStatus();
		// }
		// if (!isAllowScreenRoate) {
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// } else {
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
		// }
		// initView(mContextView);
		// doBusiness(this);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * [沉浸状态栏]
	 */
	@SuppressLint("NewApi")
	public void initTranslucentStatus() {

		// 4.4 全透明状态栏（有的机子是过渡形式的透明）
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
		// 5.0 全透明实现
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.getDecorView()
					.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(Color.TRANSPARENT);// calculateStatusColor(Color.WHITE,
														// (int) alphaValue)
		}
	}
	 /**
	 * [初始化]
	 */
	 public abstract void init();
	
	// /**
	// * [业务操作]
	// *
	// * @param mContext
	// */
	// public abstract void doBusiness(Context mContext);
	//
	// /** View点击 **/
	// public abstract void widgetClick(View v);
	//
	// @Override
	// public void onClick(View v) {
	//// if (fastClick())
	// widgetClick(v);
	// }

	/**
	 * [页面跳转]
	 *
	 * @param clz
	 */
	public void startActivitys(Class<?> clz) {
		startActivity(clz, null);
	}

	/**
	 * [携带数据的页面跳转]
	 *
	 * @param clz
	 * @param bundle
	 */
	public void startActivity(Class<?> clz, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, clz);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/**
	 * [含有Bundle通过Class打开编辑界面]
	 *
	 * @param cls
	 * @param bundle
	 * @param requestCode
	 */
	public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivityForResult(intent, requestCode);
	}

	/**
	 * [简化Toast]
	 *
	 * @param msg
	 */
	protected void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	/**
	 * 强制弹出
	 */
	@SuppressWarnings("unused")
	private void newMessage() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		SharedPreferences tips = getSharedPreferences("isShow", Context.MODE_PRIVATE);
		SharedPreferences.Editor edit_tips = tips.edit();
		edit_tips.putBoolean("isShow", false);
		edit_tips.commit();
		builder.setTitle("温馨提示");
		builder.setMessage("您有未读反馈消息");
		// builder.setIcon(R.drawable.youjian);
		builder.setCancelable(false);
		builder.setPositiveButton("查看", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				// 打开用户反馈界面
				// FeedbackAPI.openFeedbackActivity();
			}
		});
		builder.setNegativeButton("忽略", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				dialogInterface.cancel();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
		// 点击dialog屏幕外不会消失
		dialog.setCanceledOnTouchOutside(false);
		// 点击back键dialog不会消失
		dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					return true;
				} else {
					return false;
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		$Log(APP_NAME + "--->onDestroy()");
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
		$Log(APP_NAME + "--->onPause()");
	}

	/**
	 * [是否允许全屏]
	 *
	 * @param allowFullScreen
	 */
	public void setAllowFullScreen(boolean allowFullScreen) {
		this.mAllowFullScreen = allowFullScreen;
	}

	/**
	 * [是否设置沉浸状态栏]
	 *
	 * @param isSetStatusBar
	 */
	public void setSteepStatusBar(boolean isSetStatusBar) {
		this.isSetStatusBar = isSetStatusBar;
	}

	/**
	 * [是否允许屏幕旋转]
	 *
	 * @param isAllowScreenRoate
	 */
	public void setScreenRoate(boolean isAllowScreenRoate) {
		this.isAllowScreenRoate = isAllowScreenRoate;
	}

	/**
	 * [日志输出]
	 *
	 * @param msg
	 */
	protected void $Log(String msg) {
		if (isDebug) {
			Log.e(APP_NAME, msg);
		}
	}

	/**
	 * [日志开关]
	 */
	protected void isDeBug(boolean isDebug) {
		this.isDebug = isDebug;
	}

	/**
	 * [防止快速点击]
	 *
	 * @return
	 */
	public boolean fastClick() {
		long lastClick = 0;
		if (System.currentTimeMillis() - lastClick <= 2000) {
			return false;
		}
		lastClick = System.currentTimeMillis();
		return true;
	}

}
