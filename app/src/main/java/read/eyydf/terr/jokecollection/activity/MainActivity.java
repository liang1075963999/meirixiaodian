package read.eyydf.terr.jokecollection.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.fragment.MainFirstHomeFragment;
import read.eyydf.terr.jokecollection.fragment.MainOwnFragment;
import read.eyydf.terr.jokecollection.fragment.MainTypeFragment;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;
import read.eyydf.terr.jokecollection.tools.DeviceUtils;
import read.eyydf.terr.jokecollection.views.NoScrollViewPager;
import read.eyydf.terr.jokecollection.views.zitiTextView;

/**
 * 主activity
 */
@SuppressLint("NewApi")
@ContentView(R.layout.mainlayout)
public class MainActivity extends BaseActivity {
    @ViewInject(R.id.home_image)
    private ImageView home_image;
    @ViewInject(R.id.cet_main_tv)
    private zitiTextView barName;
    @ViewInject(R.id.type_image)
    private ImageView type_image;
    @ViewInject(R.id.own_image)
    private ImageView own_image;
   /* @ViewInject(R.id.cet_main)
    private LinearLayout cet_main;*/
    @ViewInject(R.id.viewpager)
    private NoScrollViewPager viewPager;
    private long firstTime;
    private List<Fragment> list;
    private Myadapter myadapter;
    private MainOwnFragment ownFragment;
    private ImageView geRen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @SuppressLint("Recycle")
    /**
     * 初始化
     */
    public void init() {
        x.view().inject(this);
        DeviceUtils.init(this);
        WebView view = new WebView(this);
        ActivityUtils.ua = view.getSettings().getUserAgentString();
        view.onPause();
        view.destroy();
        view = null;
        geRen=findViewById(R.id.geren);
        geRen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,GeRenActivity.class);
                startActivity(intent);
            }
        });
        Log.d("banebnhao", ActivityUtils.getVersionCode(this)+"版本号");
//		DownJson downJson = new DownJson(1, 2, -1, -1, 0, null ,null, -1, -1);
//		downJson.FreedomLoadTask(new FreedomCallback() {
//			@Override
//			public void jsonLoaded(String [] json, String tag) {
//				Log.e("testLog", tag + json[0]);
//				// 获取剪贴板管理器：
//				ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//				// 创建普通字符型ClipData
//				ClipData mClipData = ClipData.newPlainText("Label", json[0]);
//				// 将ClipData内容放到系统剪贴板里。
//				cm.setPrimaryClip(mClipData);
//			}
//		});
//		downJson.execute(ActivityUtils.url);

        SharedPreferences isShow = getSharedPreferences("isShow", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit_isShow = isShow.edit();
        edit_isShow.putBoolean("isShow", true);
        edit_isShow.commit();

        SharedPreferences tips = getSharedPreferences("TIPS", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit_tips = tips.edit();
        edit_tips.putBoolean("tips", true);
        edit_tips.commit();
        if (getApplication().getPackageName().equals("read.eyydf.terr.jokecollection"))
            barName.setText("爆笑日报");
        if (getApplication().getPackageName().equals("read.terr.jokecollection.encyclopedia"))
            barName.setText("爆笑百科");
        if (getApplication().getPackageName().equals("read.eyydf.jokecollection.episode"))
            barName.setText("每日笑点");
        ActivityUtils.initTranslucentStatus(this);
      /*  LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) cet_main.getLayoutParams();
        lp.topMargin = DeviceUtils.getStatusBarHeight(this);
        cet_main.setLayoutParams(lp);*/
        FragmentManager fm = getSupportFragmentManager();
        // 开启Fragment事务
        fm.beginTransaction();
        myadapter = new Myadapter(fm);
        viewPager.setAdapter(myadapter);
        viewPager.setNoScroll(true);
        // 设置当前显示页面相邻的a个页面进行缓存
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                home_image.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.home01));
                type_image.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.type01));
                own_image.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.own01));
                switch (arg0) {
                    case 0:
                        home_image.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.home02));
                        break;
                    case 1:
                        type_image.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.type02));
                        break;
                    case 2:
                        own_image.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.own02));
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    public static String sendGetRequest(String urlString) throws Exception {
        // 打开链接
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "text/plain");
        conn.setRequestProperty("Charset", "utf-8");
        conn.setConnectTimeout(5000);
        // 如果请求响应码是200，则表示成功
        if (conn.getResponseCode() == 200) {
            // 获得服务器响应的数据
            BufferedReader in = new BufferedReader(new InputStreamReader(conn
                    .getInputStream(), "utf-8"));
            // 数据
            String retData = null;
            String responseData = "";
            while ((retData = in.readLine()) != null) {
                responseData += retData;
            }
            in.close();
            return responseData;
        }
        return "sendGetRequest error!";

    }

    class Myadapter extends FragmentStatePagerAdapter {
        // 只适配一次。
        public Myadapter(FragmentManager fm) {
            super(fm);
            list = new ArrayList<>();
            list.add(new MainFirstHomeFragment());
            list.add(new MainTypeFragment());
            ownFragment = new MainOwnFragment();
            list.add(ownFragment);
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }

    /**
     * 点击事件
     */
    @Event(value = {R.id.home, R.id.type, R.id.own}, type = View.OnClickListener.class)
    private void click(View v) {
        switch (v.getId()) {
            case R.id.home:
                viewPager.setCurrentItem(0);
                break;
            case R.id.type:
                viewPager.setCurrentItem(1);
                break;
            case R.id.own:
                viewPager.setCurrentItem(2);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 退出
     */
    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            ActivityUtils.showToast(this, "再按一次退出程序");
            firstTime = secondTime;
        } else {
            finish();
            overridePendingTransition(R.anim.exit_enter, R.anim.exit_out);
            MyApplication.exit(this);
        }
    }
}
