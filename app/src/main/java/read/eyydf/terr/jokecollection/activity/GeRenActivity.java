package read.eyydf.terr.jokecollection.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.fragment.LiShiFragment;
import read.eyydf.terr.jokecollection.fragment.ShouCangFragment;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;
import read.eyydf.terr.jokecollection.tools.DataLocalSave;
import read.eyydf.terr.jokecollection.tools.DownJson;
import read.eyydf.terr.jokecollection.tools.DownJson1;
import read.eyydf.terr.jokecollection.tools.FastBlur;
import read.eyydf.terr.jokecollection.views.TabsView1;

/**
 * Created by liang on 2018/8/22.
 */

public class GeRenActivity extends BaseActivity {
    private ImageView sheZhi;
    private TextView dengLu;
    private TextView wenzi;
    private TextView qianming;
    private ViewPager homeViewpager;
    private TabsView1 tabslayout;
    private Myadapter myadapter;
    private List<Fragment> list;
    private BroadcastReceiver br;
    private String path;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    private ImageView tou;
    private ImageView houTui;

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void init() {
        ActivityUtils.initTranslucentStatus(this);
        sheZhi = findViewById(R.id.shezhi);
        dengLu = findViewById(R.id.denglu);
        homeViewpager = findViewById(R.id.homeViewpager);
        tabslayout = findViewById(R.id.tabs);
        linearLayout = findViewById(R.id.linear);
        linearLayout1 = findViewById(R.id.linear1);
        houTui = findViewById(R.id.houtui);
        tou = findViewById(R.id.tou);
        wenzi = findViewById(R.id.wenzi);
        qianming = findViewById(R.id.qianming);
        houTui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ActivityUtils.uerid = DataLocalSave.getInt(this, "userid");
        isLogin();
        // 广播
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("MainOwnFragment");
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean login = intent.getBooleanExtra("login", false);
                Log.d("fdsfsdfsdfsaa", "login:" + login);
                if (login) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            isLogin();
                        }
                    });
                } else {
                    tou.setImageResource(R.mipmap.tou);
                    linearLayout.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                    wenzi.setText("未登录");
                    qianming.setText("");
                   /* DownJson1 downJson = new DownJson1(20, 2, -1, -1, -1, DataLocalSave.getString(GeRenActivity.this, "zhanghao"), DataLocalSave.getString(GeRenActivity.this, "mima"), -1, -1, -1, 2);
                    downJson.FreedomLoadTask(new DownJson.FreedomCallback() {
                        @Override
                        public void jsonLoaded(String[] json, String tag) {
                            try {
                                Log.d("hahaha", json[0]);
                                if (tag.equals("ready")) {
                                    JSONObject jsonObject = new JSONObject(json[0]);
                                    if (jsonObject.getInt("result") == 1) {
                                        wenzi.setText(jsonObject.getJSONObject("user").getString("nickname"));
                                        qianming.setText(jsonObject.getJSONObject("user").getString("signature"));
                                    }
                                    // showToast("登录失败,账户或密码错误!");
                                } else {
                                    // showToast("登录失败!");
                                }
                            } catch (Exception e) {
                                Log.d("LoginActivity", e.getMessage().toString());
                                // showToast("登录失败!");
                            }
                        }
                    });
                    downJson.execute(ActivityUtils.baikeribaourl);*/
                    /*own_hide_one.setVisibility(View.VISIBLE);
                    own_hide_two.setVisibility(View.GONE);
                    own_user_image.setVisibility(View.GONE);
                    own_user_image_baikuang.setVisibility(View.GONE);
                    own_user_image_bg.setBackgroundResource(R.drawable.own_bg_xuhua);*/
                }
            }
        };
        localBroadcastManager.registerReceiver(br, intentFilter);

        sheZhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GeRenActivity.this, SheZhiActivity.class);
                startActivity(intent);
            }
        });
        dengLu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GeRenActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        tabslayout.setTabs("历史", "收藏");
        tabslayout.setOnTabsItemClickListener(new TabsView1.OnTabsItemClickListener() {

            @Override
            public void onClick(View view, int position) {
                homeViewpager.setCurrentItem(position, true);
            }
        });
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction();
        myadapter = new Myadapter(fm);
        homeViewpager.setAdapter(myadapter);
        // 设置当前显示页面相邻的a个页面进行缓存
        homeViewpager.setOffscreenPageLimit(2);
        homeViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                switch (arg0) {
                    case 0:
                        tabslayout.setCurrentTab(0, true);
                        break;
                    case 1:
                        tabslayout.setCurrentTab(1, true);
                        break;
                   /* case 2:
                        tabslayout.setCurrentTab(2, true);
                        break;*/
                /*case 3:
                    tabslayout.setCurrentTab(3, true);
					break;*/
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

    private void isLogin() {
        if (ActivityUtils.uerid != -1) {
            path = getFilesDir() + "/" + ActivityUtils.uerid + "_own_bg.png";
           // wenzi.setText("已登录");
            FragmentManager fm = getSupportFragmentManager();
            myadapter = new Myadapter(fm);
            homeViewpager.setAdapter(myadapter);
            if (new File(path).exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                tou.setImageBitmap(bitmap);
                Bitmap blurBitmap = FastBlur.toBlur(bitmap, 2);
                // own_user_image_bg.setBackground(new BitmapDrawable(getResources(), blurBitmap));
            } else {
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.own_bg_xuhua);
                BitmapDrawable bd = (BitmapDrawable) drawable;
                Bitmap bitmap = bd.getBitmap();
                Bitmap blurBitmap = FastBlur.toBlur(bitmap, 2);
                //own_user_image_bg.setBackground(new BitmapDrawable(getResources(), blurBitmap));
            }
            /*own_hide_one.setVisibility(View.GONE);
            own_hide_two.setVisibility(View.VISIBLE);
            own_user_image.setVisibility(View.VISIBLE);
            own_user_image_baikuang.setVisibility(View.VISIBLE);*/
            linearLayout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
            if(!DataLocalSave.getString(GeRenActivity.this, "zhanghao").equals("null")&&!DataLocalSave.getString(GeRenActivity.this, "zhanghao").equals("null")){
                DownJson1 downJson = new DownJson1(20, 2, -1, -1, -1, DataLocalSave.getString(GeRenActivity.this, "zhanghao"), DataLocalSave.getString(GeRenActivity.this, "mima"), -1, -1, -1, 2);
                downJson.FreedomLoadTask(new DownJson.FreedomCallback() {
                    @Override
                    public void jsonLoaded(String[] json, String tag) {
                        try {
                            Log.d("hahaha", json[0]);
                            if (tag.equals("ready")) {
                                JSONObject jsonObject = new JSONObject(json[0]);
                                if (jsonObject.getInt("result") == 1) {
                                    wenzi.setText(jsonObject.getJSONObject("user").getString("nickname"));
                                    qianming.setText(jsonObject.getJSONObject("user").getString("signature"));
                                }
                                // showToast("登录失败,账户或密码错误!");
                            } else {
                                // showToast("登录失败!");
                            }
                        } catch (Exception e) {
                            Log.d("LoginActivity", e.getMessage().toString());
                            // showToast("登录失败!");
                        }
                    }
                });
                downJson.execute(ActivityUtils.baikeribaourl);
            }
        } else {
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.own_bg_xuhua);
            BitmapDrawable bd = (BitmapDrawable) drawable;
            Bitmap bitmap = bd.getBitmap();
            Bitmap blurBitmap = FastBlur.toBlur(bitmap, 2);
            Log.d("GeRenActivity", "到这里了");
            linearLayout1.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
           /* wenzi.setText("未登录");
            qianming.setText("");*/
            //   own_user_image_bg.setBackground(new BitmapDrawable(getResources(), blurBitmap));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geren);
        init();
    }

    class Myadapter extends FragmentStatePagerAdapter {
        // 只适配一次。
        public Myadapter(FragmentManager fm) {
            super(fm);
            list = new ArrayList<>();
            list.add(new LiShiFragment());
            list.add(new ShouCangFragment());
            //list.add(new FirstHomezuixinFragment(3));
            //list.add(new FirstHomepaihangFragment());
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
}
