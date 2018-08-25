package read.eyydf.terr.jokecollection.fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.activity.LoginActivity;
import read.eyydf.terr.jokecollection.activity.OwnGuanyuShowActivity;
import read.eyydf.terr.jokecollection.activity.OwnHezuoShowActivity;
import read.eyydf.terr.jokecollection.activity.OwnXieyiShowActivity;
import read.eyydf.terr.jokecollection.activity.RegisterActivity;
import read.eyydf.terr.jokecollection.activity.RepalceBgActivity;
import read.eyydf.terr.jokecollection.activity.TypeActivity;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;
import read.eyydf.terr.jokecollection.tools.DataLocalSave;
import read.eyydf.terr.jokecollection.tools.FastBlur;
import read.eyydf.terr.jokecollection.views.CircleImageView;
import read.eyydf.terr.jokecollection.views.UserDefineScrollView;

/**
 * 我
 */
@SuppressLint("NewApi")
@ContentView(R.layout.viewpagerfragmentlayoutown)
public class MainOwnFragment extends Fragment {
	@ViewInject(R.id.own_hide_one)
	private LinearLayout own_hide_one;
	@ViewInject(R.id.own_hide_two)
	private LinearLayout own_hide_two;
	@ViewInject(R.id.own_user_image)
	public CircleImageView own_user_image;
	@ViewInject(R.id.own_user_image_baikuang)
	public ImageView own_user_image_baikuang;
	@ViewInject(R.id.own_user_image_bg)
	public UserDefineScrollView own_user_image_bg;
	private BroadcastReceiver br;
	private String path;

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (getActivity() != null) {
			LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(br);
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		return x.view().inject(this, inflater, container);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		init();
	}

	/**
	 * 初始化
	 */
	public void init() {
		ActivityUtils.uerid = DataLocalSave.getInt(getActivity(), "userid");
		isLogin();
		// 广播
		LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("MainOwnFragment");
		br = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				boolean login = intent.getBooleanExtra("login", false);
				if (login) {
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							isLogin();
						}
					});
				} else {
					own_hide_one.setVisibility(View.VISIBLE);
					own_hide_two.setVisibility(View.GONE);
					own_user_image.setVisibility(View.GONE);
					own_user_image_baikuang.setVisibility(View.GONE);
					own_user_image_bg.setBackgroundResource(R.drawable.own_bg_xuhua);
				}
			}
		};
		localBroadcastManager.registerReceiver(br, intentFilter);
	}

	/**
	 * 登录状态
	 */
	private void isLogin() {
		if (getActivity() == null) {
			return;
		}
		if (ActivityUtils.uerid != -1) {
			path = getActivity().getFilesDir() + "/" + ActivityUtils.uerid + "_own_bg.png";
			if (new File(path).exists()) {
				Bitmap bitmap = BitmapFactory.decodeFile(path);
				own_user_image.setImageBitmap(bitmap);
				Bitmap blurBitmap = FastBlur.toBlur(bitmap, 2);
				own_user_image_bg.setBackground(new BitmapDrawable(getResources(), blurBitmap));
			} else {
				Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.own_bg_xuhua);
				BitmapDrawable bd = (BitmapDrawable) drawable;
				Bitmap bitmap = bd.getBitmap();
				Bitmap blurBitmap = FastBlur.toBlur(bitmap, 2);
				own_user_image_bg.setBackground(new BitmapDrawable(getResources(), blurBitmap));
			}
			own_hide_one.setVisibility(View.GONE);
			own_hide_two.setVisibility(View.VISIBLE);
			own_user_image.setVisibility(View.VISIBLE);
			own_user_image_baikuang.setVisibility(View.VISIBLE);
		} else {
			Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.own_bg_xuhua);
			BitmapDrawable bd = (BitmapDrawable) drawable;
			Bitmap bitmap = bd.getBitmap();
			Bitmap blurBitmap = FastBlur.toBlur(bitmap, 2);
			own_user_image_bg.setBackground(new BitmapDrawable(getResources(), blurBitmap));
		}
	}

	/**
	 * 点击事件
	 */
	@Event(value = { R.id.own_guanyu, R.id.own_xieyi, R.id.own_hezuo, R.id.own_login, R.id.own_regiter,
			R.id.own_read_histroy, R.id.own_shoucang, R.id.own_replace_bg }, type = View.OnClickListener.class)
	private void click(View v) {
		switch (v.getId()) {
		case R.id.own_login:
			login();
			break;
		case R.id.own_regiter:
			regiter();
			break;
		case R.id.own_read_histroy:
			read_histroy();
			break;
		case R.id.own_shoucang:
			shoucang();
			break;
		case R.id.own_replace_bg:
			replace_bg();
			break;
		case R.id.own_xieyi:
			xieyi();
			break;
		case R.id.own_hezuo:
			hezuo();
			break;
		case R.id.own_guanyu:
			guanyu();
			break;
		}
	}

	/**
	 * 登录
	 */
	private void login() {
		if (getActivity() != null) {
			Intent intent = new Intent(getActivity(), LoginActivity.class);
			startActivity(intent);
		}
	}

	/**
	 * 注册
	 */
	private void regiter() {
		if (getActivity() != null) {
			Intent intent = new Intent(getActivity(), RegisterActivity.class);
			startActivity(intent);
		}
	}

	/**
	 * 收藏
	 */
	private void shoucang() {
		click_tiaozhuan("收藏", 22);
	}

	/**
	 * 阅读记录
	 */
	private void read_histroy() {
		click_tiaozhuan("阅读记录", 23);
	}

	/**
	 * 点击跳转
	 */
	private void click_tiaozhuan(String title, int position) {
		if (getActivity() != null) {
			Intent intent = new Intent(getActivity(), TypeActivity.class);
			intent.putExtra("title", title);
			intent.putExtra("position", position);
			startActivity(intent);
		}
	}

	/**
	 * 更换背景图
	 */
	private void replace_bg() {
		Intent intent = new Intent(getActivity(), RepalceBgActivity.class);
		startActivity(intent);
	}

	/**
	 * 协议
	 */
	private void xieyi() {
		click_tiaozhuan_xieyi(1);
	}

	/**
	 * 商务合作
	 */
	private void hezuo() {
		click_tiaozhuan_xieyi(2);
	}

	/**
	 * 关于app
	 */
	private void guanyu() {
		click_tiaozhuan_xieyi(3);
	}

	/**
	 * 点击跳转协议
	 */
	private void click_tiaozhuan_xieyi(int type) {
		if (getActivity() != null) {
			Class<?> classType = null;
			if (type == 1) {
				classType = OwnXieyiShowActivity.class;
			} else if (type == 2) {
				classType = OwnHezuoShowActivity.class;
			} else if (type == 3) {
				classType = OwnGuanyuShowActivity.class;
			} else {
				return;
			}
			Intent intent = new Intent(getActivity(), classType);
			startActivity(intent);
		}
	}
}
