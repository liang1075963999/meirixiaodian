package read.eyydf.terr.jokecollection.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.activity.TypeActivity;
import read.eyydf.terr.jokecollection.activity.WebViewActivity;
import read.eyydf.terr.jokecollection.model.BannerData;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;
import read.eyydf.terr.jokecollection.tools.DownJson;

import static read.eyydf.terr.jokecollection.activity.MainActivity.sendGetRequest;

/**
 * Created by fenghu on 2017/5/15.
 */
@SuppressLint("ValidFragment")
@ContentView(R.layout.viewpagerfragmentlayouttype)
public class MainTypeFragment extends Fragment {
    private Banner banner;
    private BannerData bannerData;
    private ArrayList<String> getBannerList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        banner = (Banner) view.findViewById(R.id.tuijian_banner_viewpager);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String response = null;
                    if (getActivity().getApplication().getPackageName().equals("read.eyydf.jokecollection.episode"))
                        response = sendGetRequest("http://app.51babyapp.com/AD/HUDONG/xiaohua3_02.json");
                    else if (getActivity().getApplication().getPackageName().equals("read.terr.jokecollection.encyclopedia"))
                        response = sendGetRequest("http://app.51babyapp.com/AD/HUDONG/xiaohua2_02.json");
                    else if (getActivity().getApplication().getPackageName().equals("read.eyydf.terr.jokecollection"))
                        response = sendGetRequest("http://app.51babyapp.com/AD/HUDONG/xiaohua1_02.json");
                    JSONObject jsonObject = new JSONObject(response);
                    bannerData = new BannerData();
                    bannerData.setOpen(jsonObject.getBoolean("isOpen"));
                    JSONArray jsonArray = jsonObject.getJSONArray("url");
                    Random random = new Random();
                    bannerData.setUrl(jsonArray.getString(random.nextInt(jsonArray.length())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        initBanne();
    }

    private void initBanne() {
        DownJson downJson1 = new DownJson(10, 2, -1, -1, -1, null, null, ActivityUtils.uerid, -1, -1);
        downJson1.FreedomLoadTask(new DownJson.FreedomCallback() {
            @Override
            public void jsonLoaded(String[] json, String tag) {
                if (tag.equals("ready")) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(json[0]);
                        getBannerList = new ArrayList<>();
                        JSONArray bannerlist = jsonObject.getJSONArray("bannerlist");
                        for (int i = 0; i < bannerlist.length(); i++) {
                            JSONObject jsonObject1 = bannerlist.getJSONObject(i);
                            JSONArray jsonArray = jsonObject1.getJSONArray("bannerImgs");
                            for (int j = 0; j < jsonArray.length(); j++) {
                                getBannerList.add(ActivityUtils.url_request + jsonArray.getJSONObject(j).getString("url"));
                                Log.d("MainTypgment", jsonArray.getJSONObject(j).getString("url"));
                            }
                        }
                        if (bannerData.isOpen() && getBannerList.size() != 0)
                            initBanner();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        downJson1.execute(ActivityUtils.url);
    }

    private void initBanner() {
        banner.setVisibility(View.VISIBLE);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < getBannerList.size(); i++) {
            list.add(getBannerList.get(i));
        }
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        banner.setImages(list);
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < getBannerList.size(); i++) {
            lists.add("");
        }
        banner.setBannerTitles(lists);
        banner.setDelayTime(2000);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(getActivity()).load(path).into(imageView);
            }
        });
        banner.setBannerAnimation(Transformer.Default);
        banner.setViewPagerIsScroll(false);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                final Intent intent = new Intent(getActivity(), WebViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("geturl", bannerData.getUrl());
                intent.putExtra("url", bundle);
                startActivity(intent);
            }
        });
        banner.start();
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

    @Event(value = {R.id.type_01, R.id.type_02, R.id.type_03, R.id.type_04, R.id.type_05, R.id.type_06}, type = View.OnClickListener.class)
    private void click(View v) {
        switch (v.getId()) {
            case R.id.type_01:
                click_tiaozhuan("爆笑男女", 11);
                break;
            case R.id.type_02:
                click_tiaozhuan("校园趣事", 12);
                break;
            case R.id.type_03:
                click_tiaozhuan("冷笑话", 13);
                break;
            case R.id.type_04:
                click_tiaozhuan("幽默夫妻", 14);
                break;
            case R.id.type_05:
                click_tiaozhuan("糗事一筐", 15);
                break;
            case R.id.type_06:
                click_tiaozhuan("雷人儿童", 16);
                break;
        }
    }
}
