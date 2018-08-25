package read.eyydf.terr.jokecollection.fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.banner.Banner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.activity.ContentActivity;
import read.eyydf.terr.jokecollection.adpater.ZuixinListviewAdapter1;
import read.eyydf.terr.jokecollection.model.BannerData;
import read.eyydf.terr.jokecollection.model.GetListData;
import read.eyydf.terr.jokecollection.pullableview.PullToRefreshLayout;
import read.eyydf.terr.jokecollection.pullableview.PullableListView;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;
import read.eyydf.terr.jokecollection.tools.DownJson.FreedomCallback;
import read.eyydf.terr.jokecollection.tools.DownJson1;

/**
 * Created by fenghu on 2017/5/15.
 */
@SuppressLint({"ValidFragment", "InflateParams"})
@ContentView(R.layout.viewpagerfirsthometuijianfragment)
public class FirstHometuijianFragment extends Fragment {
    private Banner banner;
    @ViewInject(R.id.pullToRefreshLayout)
    private PullToRefreshLayout pullToRefreshLayout;
    @ViewInject(R.id.tuijian_listview)
    private PullableListView tuijian_listview;
    private ZuixinListviewAdapter1 zuixinListviewAdapter;
    /*    private ImageView tuijian_tuijian_image01;
        private ImageView tuijian_tuijian_image02;
        private ImageView tuijian_qutu_image01;
        private ImageView tuijian_qutu_image02;*/
    private List<GetListData> getListDatas;
    private List<GetListData> getListDatasColumn;
    private int index;
    private boolean isStop;
    private TextView dianzan_counts;
    private ImageView dianzan_image;
    private BroadcastReceiver br;
    private BannerData bannerData;
    private ArrayList<String> getBannerList;
    private RelativeLayout rela;

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
        rela = view.findViewById(R.id.rela);
        init();
    }

    private void init() {
        if (getActivity() != null) {
            // 广播
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("FirstHometuijianFragment");
            br = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    final boolean isDianzan = intent.getBooleanExtra("isDianzan", false);
                    final int count = intent.getIntExtra("count", 0);
                    Log.d("uuuuuu", isDianzan + "," + count);
                    if (isDianzan) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (dianzan_image != null) {
                                    //dianzan_image.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bluedianzan));
                                }
                                if (dianzan_counts != null && count != 0) {
                                    dianzan_counts.setText(count + "");
                                }
                            }
                        });
                    }
                }
            };
            localBroadcastManager.registerReceiver(br, intentFilter);
            //final View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.viewpagerfirsthometuijianfragmentheader, null);
            //banner = (Banner) headerView.findViewById(R.id.tuijian_banner_viewpager);
           /* tuijian_tuijian_image01 = (ImageView) headerView.findViewById(tuijian_tuijian_image01);
            tuijian_tuijian_image02 = (ImageView) headerView.findViewById(tuijian_tuijian_image02);
            tuijian_qutu_image01 = (ImageView) headerView.findViewById(R.id.tuijian_qutu_image01);
            tuijian_qutu_image02 = (ImageView) headerView.findViewById(tuijian_qutu_image02);
            tuijian_tuijian_image01.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    click_xiaohua_cloumn(0);
                }
            });
            tuijian_tuijian_image02.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    click_xiaohua_cloumn(1);
                }
            });
            tuijian_qutu_image01.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    click_xiaohua_cloumn(2);
                }
            });
            tuijian_qutu_image02.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    click_xiaohua_cloumn(3);
                }
            });*/
            tuijian_listview.addHeaderView(new LinearLayout(getContext()));
           /* new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String response = null;
                        if (getActivity().getApplication().getPackageName().equals("read.eyydf.jokecollection.episode"))
                            response = sendGetRequest("http://app.51babyapp.com/AD/HUDONG/xiaohua3_01.json");
                        else if (getActivity().getApplication().getPackageName().equals("read.terr.jokecollection.encyclopedia"))
                            response = sendGetRequest("http://app.51babyapp.com/AD/HUDONG/xiaohua2_01.json");
                        else if (getActivity().getApplication().getPackageName().equals("read.eyydf.terr.jokecollection"))
                            response = sendGetRequest("http://app.51babyapp.com/AD/HUDONG/xiaohua1_01.json");
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
            }).start();*/
//			initData();
//			MyPagerAdapter mAdapter = new MyPagerAdapter();
//			noScrollViewPager.setAdapter(mAdapter);//第二步：设置viewpager适配器
//			noScrollViewPager.addOnPageChangeListener(new OnPageChangeListener() {	
//				@Override
//				public void onPageSelected(int position) {
//				}
//				@Override
//				public void onPageScrolled(int arg0, float arg1, int arg2) {}
//				@Override
//				public void onPageScrollStateChanged(int arg0) {}
//			});
//			autoPlayView();


            pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                }

                @Override
                public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                    load_more();
                }
            });

            //DownJson downJson = new DownJson(1, 1, -1, -1, 0, null, null, -1, -1, -1);
            DownJson1 downJson = new DownJson1(1, 1, -1, -1, 0, null, null, -1, -1, -1, 2);
            downJson.FreedomLoadTask(new FreedomCallback() {
                @Override
                public void jsonLoaded(String[] json, String tag) {
                    if (tag.equals("ready")) {
                        try {
                            Log.d("FirstHometuijianFragmen", json[0]);
                            JSONObject jsonObject = new JSONObject(json[0]);
                            try {
                                /*getBannerList = new ArrayList<>();
                                JSONArray bannerlist = jsonObject.getJSONArray("bannerlist");
                                Log.d("FirstHometuijiane", bannerlist.toString());
                                for (int i = 0; i < bannerlist.length(); i++) {
                                    JSONObject jsonObject1 = bannerlist.getJSONObject(i);
                                    JSONArray jsonArray = jsonObject1.getJSONArray("bannerImgs");
                                    for (int j = 0; j < jsonArray.length(); j++) {
                                        getBannerList.add(ActivityUtils.url_request + jsonArray.getJSONObject(j).getString("url"));
                                    }
                                }
                                if (bannerData.isOpen() && getBannerList.size() != 0)
                                    initBanner();*/
                               /* JSONArray jsonArrayColumn = jsonObject.getJSONArray("columnlist");
                                Glide.with(getActivity()).load(ActivityUtils.url_request + jsonArrayColumn.getJSONObject(0).getJSONArray("contentPictures").getJSONObject(0).getString("url"))
                                        .into(tuijian_tuijian_image01);
                                Glide.with(getActivity()).load(ActivityUtils.url_request + jsonArrayColumn.getJSONObject(1).getJSONArray("contentPictures").getJSONObject(0).getString("url"))
                                        .into(tuijian_tuijian_image02);
                                Glide.with(getActivity()).load(ActivityUtils.url_request + jsonArrayColumn.getJSONObject(2).getJSONArray("contentPictures").getJSONObject(0).getString("url"))
                                        .into(tuijian_qutu_image01);
                                Glide.with(getActivity()).load(ActivityUtils.url_request + jsonArrayColumn.getJSONObject(3).getJSONArray("contentPictures").getJSONObject(0).getString("url"))
                                        .into(tuijian_qutu_image02);
                                getListDatasColumn = new ArrayList<>();
                                for (int i = 0; i < jsonArrayColumn.length(); i++) {
                                    GetListData paihangListData = new GetListData();
                                    paihangListData.setArticle_id(jsonArrayColumn.getJSONObject(i).getInt("article_id"));
                                    paihangListData.setArticle_name(jsonArrayColumn.getJSONObject(i).getString("article_name"));
                                    paihangListData.setContent(jsonArrayColumn.getJSONObject(i).getString("content"));
                                    paihangListData.setImageNum(jsonArrayColumn.getJSONObject(i).getInt("imageNum"));
                                    paihangListData.setCountcollect(jsonArrayColumn.getJSONObject(i).getInt("countcollect"));
                                    paihangListData.setCountlike(jsonArrayColumn.getJSONObject(i).getInt("countlike"));
                                    paihangListData.setIs_collect(jsonArrayColumn.getJSONObject(i).getInt("is_collect"));
                                    paihangListData.setIs_like(jsonArrayColumn.getJSONObject(i).getInt("is_like"));
                                    try {
                                        JSONArray contentPicturesArray = jsonArrayColumn.getJSONObject(i).getJSONArray("contentPictures");
                                        if (contentPicturesArray.length() > 0) {
                                            ArrayList<String> contentPictures = new ArrayList<>();
                                            for (int j = 0; j < contentPicturesArray.length(); j++) {
                                                contentPictures.add(contentPicturesArray.getJSONObject(j).getString("url"));
                                            }
                                            paihangListData.setContentPictures(contentPictures);
                                        }
                                    } catch (Exception e) {
                                    }
                                    getListDatasColumn.add(paihangListData);*/
                                //}

                            } catch (Exception e) {
                            }
                            JSONArray articlelist = jsonObject.getJSONArray("articlelist");
                            JSONObject bannerone = jsonObject.getJSONObject("bannerone");
                            getListDatas = new ArrayList<>();
                            for (int i = 0; i < articlelist.length(); i++) {
                                GetListData paihangListData = new GetListData();
                                if (i == 2 && bannerone != null) {
                                    Log.d("woshitupian", bannerone.getString("url"));
                                    paihangListData.setTuPianURL(bannerone.getString("url"));
                                    paihangListData.setGuangGaoURL(bannerone.getString("advertise_url"));
                                }
                                paihangListData.setArticle_id(articlelist.getJSONObject(i).getInt("article_id"));
                                paihangListData.setArticle_name(articlelist.getJSONObject(i).getString("article_name"));
                                paihangListData.setContent(articlelist.getJSONObject(i).getString("content"));
                                Log.d("qwewrtt", articlelist.getJSONObject(i).getString("content"));
                                paihangListData.setImageNum(articlelist.getJSONObject(i).getInt("imageNum"));
                                paihangListData.setCountcollect(articlelist.getJSONObject(i).getInt("countcollect"));
                                paihangListData.setCountlike(articlelist.getJSONObject(i).getInt("countlike"));
                                paihangListData.setIs_collect(articlelist.getJSONObject(i).getInt("is_collect"));
                                paihangListData.setIs_like(articlelist.getJSONObject(i).getInt("is_like"));
                                paihangListData.setDate(articlelist.getJSONObject(i).getString("create_date"));
                                try {
                                    JSONArray contentPicturesArray = articlelist.getJSONObject(i).getJSONArray("contentPictures");
                                    if (contentPicturesArray.length() > 0) {
                                        ArrayList<String> contentPictures = new ArrayList<>();
                                        for (int j = 0; j < contentPicturesArray.length(); j++) {
                                            contentPictures.add(contentPicturesArray.getJSONObject(j).getString("url"));
                                        }
                                        paihangListData.setContentPictures(contentPictures);
                                    }
                                } catch (Exception e) {
                                    Log.d("yichang1", e.getMessage().toString());
                                }
                                getListDatas.add(paihangListData);
                            }
                            //DownJson downJson1 = new DownJson(1, 1, -1, -1, 1, null, null, -1, -1, -1);
                            DownJson1 downJson1 = new DownJson1(1, 1, -1, -1, 1, null, null, -1, -1, -1, 2);
                            downJson1.FreedomLoadTask(new FreedomCallback() {
                                @Override
                                public void jsonLoaded(String[] json, String tag) {
                                    if (tag.equals("ready")) {
                                        index = 2;
                                        try {
                                            JSONObject jsonObject = new JSONObject(json[0]);
                                            JSONObject bannerone = jsonObject.getJSONObject("bannerone");
                                            JSONArray articlelist = jsonObject.getJSONArray("articlelist");
                                            for (int i = 0; i < articlelist.length(); i++) {
                                                GetListData paihangListData = new GetListData();
                                                if (i == 2 && bannerone != null) {
                                                    Log.d("woshitupian", bannerone.getString("url"));
                                                    paihangListData.setTuPianURL(bannerone.getString("url"));
                                                    paihangListData.setGuangGaoURL(bannerone.getString("advertise_url"));
                                                }
                                                paihangListData
                                                        .setArticle_id(articlelist.getJSONObject(i).getInt("article_id"));
                                                paihangListData.setArticle_name(
                                                        articlelist.getJSONObject(i).getString("article_name"));
                                                paihangListData
                                                        .setContent(articlelist.getJSONObject(i).getString("content"));
                                                paihangListData
                                                        .setImageNum(articlelist.getJSONObject(i).getInt("imageNum"));
                                                paihangListData.setCountcollect(articlelist.getJSONObject(i).getInt("countcollect"));
                                                paihangListData.setCountlike(articlelist.getJSONObject(i).getInt("countlike"));
                                                paihangListData.setIs_collect(articlelist.getJSONObject(i).getInt("is_collect"));
                                                paihangListData.setIs_like(articlelist.getJSONObject(i).getInt("is_like"));
                                                try {
                                                    JSONArray contentPicturesArray = articlelist.getJSONObject(i).getJSONArray("contentPictures");
                                                    if (contentPicturesArray.length() > 0) {
                                                        ArrayList<String> contentPictures = new ArrayList<>();
                                                        for (int j = 0; j < contentPicturesArray.length(); j++) {
                                                            contentPictures.add(contentPicturesArray.getJSONObject(j).getString("url"));
                                                        }
                                                        paihangListData.setContentPictures(contentPictures);
                                                    }
                                                } catch (Exception e) {
                                                    Log.d("yichang2", e.getMessage().toString());
                                                }
                                                getListDatas.add(paihangListData);
                                            }
                                            if (getActivity() != null) {
                                                if (getListDatas == null) {
                                                    return;
                                                }
                                                // tuijian_listview.removeHeaderView(headerView);
                                                listViewSet();
                                            }
                                        } catch (Exception e) {
                                            Log.d("yichang", e.getMessage().toString());
                                        }
                                    } else {
                                        listViewSet();
                                    }
                                }
                            });
                            //downJson1.execute(ActivityUtils.url);
                            downJson1.execute(ActivityUtils.baikeribaourl);
                        } catch (Exception e) {
                        }
                    }
                }
            });
            //downJson.execute(ActivityUtils.url);
            downJson.execute(ActivityUtils.baikeribaourl);
        }
    }

    /*private void initBanner() {
        banner.setVisibility(View.VISIBLE);
        final List<String> list = new ArrayList<>();
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
        banner.setViewPagerIsScroll(false);
        banner.setBannerAnimation(Transformer.Default);
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
    }*/

    /**
     * 设置listView
     */
    private void listViewSet() {
        zuixinListviewAdapter = new ZuixinListviewAdapter1(getListDatas,
                getActivity());
        tuijian_listview.setAdapter(zuixinListviewAdapter);
        rela.setVisibility(View.GONE);
        pullToRefreshLayout.setVisibility(View.VISIBLE);
        tuijian_listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                dianzan_counts = (TextView) arg1.findViewById(R.id.dianzan_counts);
                dianzan_image = (ImageView) arg1.findViewById(R.id.dianzan_image);
                click_xiaohua(arg2 - 1);
            }
        });
    }


    /**
     * 加载更多方法
     */
    private void load_more() {
        //DownJson downJson1 = new DownJson(1, 1, -1, -1, index, null, null, -1, -1, -1);
        DownJson1 downJson1 = new DownJson1(1, 1, -1, -1, index, null, null, -1, -1, -1, 2);
        downJson1.FreedomLoadTask(new FreedomCallback() {
            @Override
            public void jsonLoaded(String[] json, String tag) {
                if (tag.equals("ready")) {
                    index++;
                    final List<GetListData> paihangListDataTemp = new ArrayList<>();
                    paihangListDataTemp.addAll(getListDatas);
                    int count = getListDatas.size();
                    try {
                        JSONObject jsonObject = new JSONObject(json[0]);
                        JSONObject bannerone = jsonObject.getJSONObject("bannerone");
                        JSONArray articlelist = jsonObject.getJSONArray("articlelist");
                        if (articlelist.length() == 0) {
                            pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                            Toast.makeText(getActivity(), "已加载所有数据!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        for (int i = 0; i < articlelist.length(); i++) {
                            GetListData paihangListData = new GetListData();
                            if (i == 2 && bannerone != null) {
                                Log.d("woshitupian", bannerone.getString("url"));
                                paihangListData.setTuPianURL(bannerone.getString("url"));
                                paihangListData.setGuangGaoURL(bannerone.getString("advertise_url"));
                            }
                            paihangListData.setArticle_id(articlelist.getJSONObject(i).getInt("article_id"));
                            paihangListData.setArticle_name(articlelist.getJSONObject(i).getString("article_name"));
                            paihangListData.setContent(articlelist.getJSONObject(i).getString("content"));
                            paihangListData.setImageNum(articlelist.getJSONObject(i).getInt("imageNum"));
                            paihangListData.setCountcollect(articlelist.getJSONObject(i).getInt("countcollect"));
                            paihangListData.setCountlike(articlelist.getJSONObject(i).getInt("countlike"));
                            paihangListData.setIs_collect(articlelist.getJSONObject(i).getInt("is_collect"));
                            paihangListData.setIs_like(articlelist.getJSONObject(i).getInt("is_like"));
                            try {
                                JSONArray contentPicturesArray = articlelist.getJSONObject(i).getJSONArray("contentPictures");
                                if (contentPicturesArray.length() > 0) {
                                    ArrayList<String> contentPictures = new ArrayList<>();
                                    for (int j = 0; j < contentPicturesArray.length(); j++) {
                                        contentPictures.add(contentPicturesArray.getJSONObject(j).getString("url"));
                                    }
                                    paihangListData.setContentPictures(contentPictures);
                                }
                            } catch (Exception e) {
                                Log.d("opop", e.getMessage().toString());
                            }
                            paihangListDataTemp.add(paihangListData);
                        }
                        getListDatas.clear();
                        getListDatas.addAll(paihangListDataTemp);
                        zuixinListviewAdapter.notifyDataSetChanged();
                        tuijian_listview.setSelection(count - 2);

                    } catch (Exception e) {
                        Log.d("opop1", e.getMessage().toString());
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                        Toast.makeText(getActivity(), "已加载所有数据!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                } else {
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
                }
            }
        });
        downJson1.execute(ActivityUtils.baikeribaourl);

    }

    /**
     * listview点击笑话
     */
    private void click_xiaohua(int position) {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), ContentActivity.class);
            ArrayList<Integer> article_id_list = new ArrayList<>();
            ArrayList<String> article_name_list = new ArrayList<>();
            ArrayList<String> content_list = new ArrayList<>();
            ArrayList<Integer> countcollect = new ArrayList<>();
            ArrayList<Integer> countlike = new ArrayList<>();
            ArrayList<Integer> is_collect = new ArrayList<>();
            ArrayList<Integer> is_like = new ArrayList<>();
            ArrayList<String> date = new ArrayList<>();
            for (int i = 0; i < getListDatas.size(); i++) {
                article_id_list.add(getListDatas.get(i).getArticle_id());
                article_name_list.add(getListDatas.get(i).getArticle_name());
                content_list.add(getListDatas.get(i).getContent());
                countcollect.add(getListDatas.get(i).getCountcollect());
                countlike.add(getListDatas.get(i).getCountlike());
                is_collect.add(getListDatas.get(i).getIs_collect());
                is_like.add(getListDatas.get(i).getIs_like());
                date.add(getListDatas.get(i).getDate());
            }
            intent.putIntegerArrayListExtra("article_id_list", article_id_list);
            intent.putStringArrayListExtra("article_name_list", article_name_list);
            intent.putStringArrayListExtra("content_list", content_list);
            intent.putStringArrayListExtra("date", content_list);
//			intent.putIntegerArrayListExtra("countcollect", countcollect);
//			intent.putIntegerArrayListExtra("countlike", countlike);
//			intent.putIntegerArrayListExtra("is_collect", is_collect);
//			intent.putIntegerArrayListExtra("is_like", is_like);
            intent.putExtra("position", position);
            intent.putExtra("tuijian", true);
            startActivity(intent);
        }
    }

    /**
     * 栏目点击笑话
     */
    private void click_xiaohua_cloumn(int position) {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), ContentActivity.class);
            ArrayList<Integer> article_id_list = new ArrayList<>();
            ArrayList<String> article_name_list = new ArrayList<>();

            for (int i = 0; i < getListDatasColumn.size(); i++) {
                article_id_list.add(getListDatasColumn.get(i).getArticle_id());
                article_name_list.add(getListDatasColumn.get(i).getArticle_name());
            }
            intent.putIntegerArrayListExtra("article_id_list", article_id_list);
            intent.putStringArrayListExtra("article_name_list", article_name_list);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    }

    //轮播图填充
    private void initData() {


    }

    /**
     * 每隔3秒自动播放图片
     */
   /* private void autoPlayView() {
        //自动播放图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop) {
                    if (getActivity() == null) {
                        isStop = true;
                        return;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            noScrollViewPager.setCurrentItem(noScrollViewPager.getCurrentItem() + 1);
                        }
                    });
                    SystemClock.sleep(3000);
                }
            }
        }).start();
    }*/

    //适配器
    class MyPagerAdapter extends PagerAdapter {
        // 1.返回条目的总数
        @Override
        public int getCount() {
            return getListDatas.size();
        }

        // 2.返回要显示的条目，并创建条目
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //container:容器，其实也就是ViewPager
            //position:当前要显示的条目的位置
            int newPosition = position % getListDatas.size();
            // ImageView imageView = getListDatas.get(newPosition);
            ImageView imageView = new ImageView(getActivity());
            //a.将View对象添加到container容器中
            container.addView(imageView);
            //b.把View对象返回给框架，适配器
            return imageView;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }
}
