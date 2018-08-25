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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.activity.BaoXiaoActivity;
import read.eyydf.terr.jokecollection.activity.ContentActivity;
import read.eyydf.terr.jokecollection.activity.GaoXiaoActivity;
import read.eyydf.terr.jokecollection.activity.WebViewActivity;
import read.eyydf.terr.jokecollection.adpater.MyAdapter;
import read.eyydf.terr.jokecollection.adpater.ZuixinListviewAdapter;
import read.eyydf.terr.jokecollection.model.BannerData;
import read.eyydf.terr.jokecollection.model.GetListData;
import read.eyydf.terr.jokecollection.model.TuiJianBannerEntity;
import read.eyydf.terr.jokecollection.model.TuiJianEntity;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;
import read.eyydf.terr.jokecollection.tools.DownJson;
import read.eyydf.terr.jokecollection.tools.DownJson.FreedomCallback;
import read.eyydf.terr.jokecollection.tools.DownJson1;
import read.eyydf.terr.jokecollection.tools.RecyclerInterface;
import read.eyydf.terr.jokecollection.views.MyTransition;

import static read.eyydf.terr.jokecollection.activity.MainActivity.sendGetRequest;

/**
 * Created by fenghu on 2017/5/15.
 */
@SuppressLint("ValidFragment")
@ContentView(R.layout.viewpagerfirsthomezuixinfragment)
public class FirstHomezuixinFragment extends Fragment implements View.OnClickListener {
    /*@ViewInject(R.id.pullToRefreshLayout)
    private PullToRefreshLayout pullToRefreshLayout;
    @ViewInject(R.id.zuixin_listview)
    private PullableListView listView;*/
    RecyclerView recyclerView;
    RecyclerView recyclerView1;
    private List<GetListData> gaoXiaDatas;
    private List<GetListData> baoXiaDatas;
    private ZuixinListviewAdapter zuixinListviewAdapter;
    private int index;
    private int type;
    private TextView dianzan_counts;
    private ImageView dianzan_image;
    private BroadcastReceiver br;
    private MyAdapter myAdapter;
    private MyAdapter myAdapter1;
    private BannerData bannerData;
    private List<TuiJianBannerEntity.Bannerlistshiti> getBannerList;
    private ImageView banner;
    private ArrayList<TuiJianEntity.Choicenesslistshiti> list;
    private ImageView imageView;
    private ImageView imageView1;
    private ImageView imageView2;
    private TextView title;
    private TuiJianBannerEntity.Bannerlistshiti b;
    private TuiJianBannerEntity.Bannerlistshiti.BannerImgsshiti b1;
    private ArrayList<Integer> ids;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getActivity() != null) {
            LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(br);
        }
    }

    @Override
    public void onClick(View v) {
        // Intent intent = new Intent(getContext(), ContentActivity.class);
        switch (v.getId()) {
            case R.id.image:
                //intent.putExtra("hao", 0);
                //click_xiaohua1(0);
                // intent.putExtra("zhi",list.get(0));
                break;
            case R.id.image1:
                // intent.putExtra("hao", 1);
                // click_xiaohua1(1);
                // intent.putExtra("zhi",list.get(1));
                break;
            case R.id.image2:
                // intent.putExtra("hao", 2);
                //  click_xiaohua1(2);
                //intent.putExtra("zhi",list.get(2));
                break;
        }
        // intent.putParcelableArrayListExtra("zhi", list);
        // startActivity(intent);
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
        init(view);
    }

    public FirstHomezuixinFragment() {
        super();
    }

    public FirstHomezuixinFragment(int type) {
        super();
        this.type = type;
    }

    private void init(View view) {
        // 广播
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("FirstHomezuixinFragment");
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final boolean isDianzan = intent.getBooleanExtra("isDianzan", false);
                final int count = intent.getIntExtra("count", 0);
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
        gaoXiaDatas = new ArrayList<>();
        baoXiaDatas = new ArrayList<>();
        myAdapter = new MyAdapter(gaoXiaDatas, getContext());
        myAdapter1 = new MyAdapter(baoXiaDatas, getContext());
        recyclerView = view.findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView1 = view.findViewById(R.id.recycler1);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        banner = view.findViewById(R.id.banner);
        imageView = view.findViewById(R.id.image);
        imageView1 = view.findViewById(R.id.image1);
        imageView2 = view.findViewById(R.id.image2);
        imageView.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        title = view.findViewById(R.id.title);
        /*pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
			public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
			}

			@Override
			public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
				load_more();
			}
		});*/

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

                    boolean isOpen = jsonObject.getBoolean("isOpen");
                    JSONArray versionArray = jsonObject.getJSONArray("versionList");
                    for (int i = 0; i < versionArray.length(); i++) {
                        if (versionArray.getInt(i) == ActivityUtils.getVersionCode(getActivity())) {
                            isOpen = false;
                        }
                    }
                    bannerData.setOpen(isOpen);

                    JSONArray jsonArray = jsonObject.getJSONArray("url");
                    Random random = new Random();
                    bannerData.setUrl(jsonArray.getString(random.nextInt(jsonArray.length())));
                    initBanne();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        DownJson1 downJson11 = new DownJson1(2, 2, -1, -1, 0, null, null, -1, -1, -1, 2);
        downJson11.FreedomLoadTask(new FreedomCallback() {
            @Override
            public void jsonLoaded(String[] json, String tag) {
                if (tag.equals("ready")) {
                    Log.d("huoqu", json[0]);
                    try {
                        Gson gson = new Gson();
                        TuiJianEntity tuiJianEntity = gson.fromJson(json[0], TuiJianEntity.class);
                        list = tuiJianEntity.getChoicenesslist();
                        title.setText(list.get(0).getArticle_name());
                        ids = new ArrayList<>();
                        Log.d("aqqqqqq", "list.size():" + list.size());
                        if (list.size() >= 3) {
                            for (int i = 0; i < 3; i++) {
                                ids.add(list.get(i).getArticle_id());
                            }
                        }
                        RequestOptions requestOptions = RequestOptions.centerCropTransform().optionalTransform(new MyTransition(getContext()));
                        Log.d("fffaaaaa", "list.get(0).getContentPictures().get(0):" + list.get(0).getContentPictures().get(0).getUrl());
                        Glide.with(getContext()).applyDefaultRequestOptions(requestOptions).load(list.get(0).getContentPictures().get(0).getUrl()).into(imageView);
                        Glide.with(getContext()).applyDefaultRequestOptions(requestOptions).load(list.get(1).getContentPictures().get(0).getUrl()).into(imageView1);
                        Glide.with(getContext()).applyDefaultRequestOptions(requestOptions).load(list.get(2).getContentPictures().get(0).getUrl()).into(imageView2);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                click_xiaohua2(0);
                            }
                        });
                        imageView1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                click_xiaohua2(1);
                            }
                        });
                        imageView2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                click_xiaohua2(2);
                            }
                        });
                    } catch (Exception e) {
                    }
                }
            }
        });
        downJson11.execute(ActivityUtils.baikeribaourl);

        //DownJson downJson = new DownJson(2, 3, -1, -1, 0, null, null, -1, -1, -1);
        DownJson1 downJson = new DownJson1(36, 2, -1, -1, 0, null, null, -1, -1, -1, 2);
        downJson.FreedomLoadTask(new FreedomCallback() {
            @Override
            public void jsonLoaded(String[] json, String tag) {
                if (tag.equals("ready")) {
                    Log.d("huoqu", json[0]);
                    try {
                        JSONObject jsonObject = new JSONObject(json[0]);
                        JSONArray articlelist = jsonObject.getJSONArray("picturelist");
                        Log.d("FirstHomezuixinFragment", "articlelist.length():" + articlelist.length());
                        if (articlelist.length() >= 5) {
                            for (int i = 0; i < 5; i++) {
                                GetListData paihangListData = new GetListData();
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
                                            if (j == 0)
                                                paihangListData.setArticle_id(contentPicturesArray.getJSONObject(0).getInt("picture_id"));
                                            contentPictures.add(contentPicturesArray.getJSONObject(j).getString("url"));
                                        }
                                        paihangListData.setContentPictures(contentPictures);
                                    }
                                } catch (Exception e) {
                                }
                                gaoXiaDatas.add(paihangListData);
                            }
                        }
                        gaoXiaoSet();
                    } catch (Exception e) {
                    }
                }
            }
        });
        downJson.execute(ActivityUtils.baikeribaourl);
        //DownJson downJson1 = new DownJson(3, 3, -1, -1, 1, null, null, -1, -1, -1);
        DownJson1 downJson1 = new DownJson1(35, 2, -1, -1, 0, null, null, -1, -1, -1, 2);
        downJson1.FreedomLoadTask(new FreedomCallback() {
            @Override
            public void jsonLoaded(String[] json, String tag) {
                if (tag.equals("ready")) {
                    Log.d("huoqu1", json[0]);
                    index = 2;
                    try {
                        JSONObject jsonObject = new JSONObject(json[0]);
                        JSONArray articlelist = jsonObject.getJSONArray("columnlist");
                        if (articlelist.length() >= 5) {
                            for (int i = 0; i < 5; i++) {
                                GetListData paihangListData = new GetListData();
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
                                }
                                baoXiaDatas.add(paihangListData);
                            }
                        }
                        baoXiaSet();
                    } catch (Exception e) {
                    }
                }
            }
        });
        downJson1.execute(ActivityUtils.baikeribaourl);
    }

    private void initBanne() {
        //DownJson downJson1 = new DownJson(10, 2, -1, -1, -1, null, null, ActivityUtils.uerid, -1, -1);
        DownJson1 downJson1 = new DownJson1(2, 2, -1, -1, -1, null, null, ActivityUtils.uerid, -1, -1, 2);
        downJson1.FreedomLoadTask(new DownJson.FreedomCallback() {
            @Override
            public void jsonLoaded(String[] json, String tag) {
                if (tag.equals("ready")) {
                    JSONObject jsonObject = null;
                    try {
                        Gson gson = new Gson();
                        TuiJianBannerEntity tuiJianBannerEntity = gson.fromJson(json[0], TuiJianBannerEntity.class);
                        getBannerList = tuiJianBannerEntity.getBannerlist();

                       /* jsonObject = new JSONObject(json[0]);
                        getBannerList = new ArrayList<>();
                        JSONArray bannerlist = jsonObject.getJSONArray("bannerlist");
                        for (int i = 0; i < bannerlist.length(); i++) {
                            JSONObject jsonObject1 = bannerlist.getJSONObject(i);
                            JSONArray jsonArray = jsonObject1.getJSONArray("bannerImgs");
                            for (int j = 0; j < jsonArray.length(); j++) {
                                getBannerList.add(ActivityUtils.url_request + jsonArray.getJSONObject(j).getString("url"));
                                Log.d("MainTypgment", jsonArray.getJSONObject(j).getString("url"));
                            }
                        }*/
                        if (bannerData.isOpen() && getBannerList.size() != 0)
                            initBanner();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        downJson1.execute(ActivityUtils.baikeribaourl);
    }

    private void initBanner() {
        banner.setVisibility(View.VISIBLE);
        RequestOptions requestOptions = RequestOptions.centerCropTransform().optionalTransform(new MyTransition(getContext()));
        Random random = new Random();
        Random random1 = new Random();
        try {
            b = getBannerList.get(random.nextInt(getBannerList.size()));
            b1 = b.getBannerImgs().get(random1.nextInt(b.getBannerImgs().size()));
            Glide.with(getContext()).applyDefaultRequestOptions(requestOptions).load(b1.getUrl()).into(banner);
        } catch (Exception e) {

        }
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("geturl", b1.getAdvertise_url());
                Log.d("FirstHomezuixinFragment", b1.getAdvertise_url());
                intent.putExtra("url", bundle);
                startActivity(intent);
            }
        });
      /*  banner.setVisibility(View.VISIBLE);
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
        banner.start();*/
    }

    /**
     * 设置listView
     */
    private void gaoXiaoSet() {
        recyclerView.setAdapter(myAdapter);
        myAdapter.setJieKou(new RecyclerInterface() {
            @Override
            public void danJi(View view, int position) {
                if (position == 5) {
                    Intent intent = new Intent(getActivity(), GaoXiaoActivity.class);
                    startActivity(intent);
                } else
                    click_xiaohua(position);
            }
        });
      /*  listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                dianzan_counts = (TextView) arg1.findViewById(R.id.dianzan_counts);
                dianzan_image = (ImageView) arg1.findViewById(R.id.dianzan_image);
                click_xiaohua(arg2);
            }
        });*/
    }

    private void baoXiaSet() {
        recyclerView1.setAdapter(myAdapter1);
        myAdapter1.setJieKou(new RecyclerInterface() {
            @Override
            public void danJi(View view, int position) {
                // Toast.makeText(getContext(), "你点击了" + position, Toast.LENGTH_SHORT).show();
                if (position == 5) {
                    Intent intent = new Intent(getActivity(), BaoXiaoActivity.class);
                    startActivity(intent);
                } else
                    click_xiaohua1(position);
            }
        });
       /* listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                dianzan_counts = (TextView) arg1.findViewById(R.id.dianzan_counts);
                dianzan_image = (ImageView) arg1.findViewById(R.id.dianzan_image);
                click_xiaohua(arg2);
            }
        });*/
    }
    /**
     * 加载更多方法
     */
    /*private void load_more() {
        DownJson downJson1 = new DownJson(type, 3, -1, -1, index, null, null, -1, -1, -1);
		downJson1.FreedomLoadTask(new FreedomCallback() {
			@Override
			public void jsonLoaded(String[] json, String tag) {
				if (tag.equals("ready")) {
					index++;
					final List<GetListData> paihangListDataTemp = new ArrayList<>();
					paihangListDataTemp.addAll(zuixinListDatas);
					int count = zuixinListDatas.size();
					try {
						JSONObject jsonObject = new JSONObject(json[0]);
						JSONArray articlelist = jsonObject.getJSONArray("articlelist");
						if (articlelist.length() == 0) {
							pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
							Toast.makeText(getActivity(), "已加载所有数据!", Toast.LENGTH_SHORT).show();
							return;
						}
						for (int i = 0; i < articlelist.length(); i++) {
							GetListData paihangListData = new GetListData();
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
							} catch (Exception e) {}
							paihangListDataTemp.add(paihangListData);
						}
						zuixinListDatas.clear();
						zuixinListDatas.addAll(paihangListDataTemp);
						zuixinListviewAdapter.notifyDataSetChanged();
						listView.setSelection(count - 2);

					} catch (Exception e) {
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
		downJson1.execute(ActivityUtils.url);

	}*/

    /**
     * 点击笑话
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
            for (int i = 0; i < gaoXiaDatas.size(); i++) {
                article_id_list.add(gaoXiaDatas.get(i).getArticle_id());
                article_name_list.add(gaoXiaDatas.get(i).getArticle_name());
                content_list.add(gaoXiaDatas.get(i).getContent());
                countcollect.add(gaoXiaDatas.get(i).getCountcollect());
                countlike.add(gaoXiaDatas.get(i).getCountlike());
                is_collect.add(gaoXiaDatas.get(i).getIs_collect());
                is_like.add(gaoXiaDatas.get(i).getIs_like());
            }

            intent.putIntegerArrayListExtra("article_id_list", article_id_list);
            intent.putExtra("wenzhangid", article_id_list.get(position));
            intent.putStringArrayListExtra("article_name_list", article_name_list);
            intent.putStringArrayListExtra("content_list", content_list);
//			intent.putIntegerArrayListExtra("countcollect", countcollect);
//			intent.putIntegerArrayListExtra("countlike", countlike);
//			intent.putIntegerArrayListExtra("is_collect", is_collect);
//			intent.putIntegerArrayListExtra("is_like", is_like);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    }

    private void click_xiaohua1(int position) {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), ContentActivity.class);
            ArrayList<Integer> article_id_list = new ArrayList<>();
            ArrayList<String> article_name_list = new ArrayList<>();
            ArrayList<String> content_list = new ArrayList<>();
            ArrayList<Integer> countcollect = new ArrayList<>();
            ArrayList<Integer> countlike = new ArrayList<>();
            ArrayList<Integer> is_collect = new ArrayList<>();
            ArrayList<Integer> is_like = new ArrayList<>();
            for (int i = 0; i < baoXiaDatas.size(); i++) {
                article_id_list.add(baoXiaDatas.get(i).getArticle_id());
                article_name_list.add(baoXiaDatas.get(i).getArticle_name());
                content_list.add(baoXiaDatas.get(i).getContent());
                countcollect.add(baoXiaDatas.get(i).getCountcollect());
                countlike.add(baoXiaDatas.get(i).getCountlike());
                is_collect.add(baoXiaDatas.get(i).getIs_collect());
                is_like.add(baoXiaDatas.get(i).getIs_like());
            }
            intent.putIntegerArrayListExtra("article_id_list", article_id_list);
            intent.putStringArrayListExtra("article_name_list", article_name_list);
            intent.putStringArrayListExtra("content_list", content_list);
//			intent.putIntegerArrayListExtra("countcollect", countcollect);
//			intent.putIntegerArrayListExtra("countlike", countlike);
//			intent.putIntegerArrayListExtra("is_collect", is_collect);
//			intent.putIntegerArrayListExtra("is_like", is_like);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    }

    private void click_xiaohua2(int position) {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), ContentActivity.class);
          /*  ArrayList<Integer> article_id_list = new ArrayList<>();
            ArrayList<String> article_name_list = new ArrayList<>();
            ArrayList<String> content_list = new ArrayList<>();
            ArrayList<Integer> countcollect = new ArrayList<>();
            ArrayList<Integer> countlike = new ArrayList<>();
            ArrayList<Integer> is_collect = new ArrayList<>();
            ArrayList<Integer> is_like = new ArrayList<>();
            for (int i = 0; i < baoXiaDatas.size(); i++) {
                article_id_list.add(baoXiaDatas.get(i).getArticle_id());
                article_name_list.add(baoXiaDatas.get(i).getArticle_name());
                content_list.add(baoXiaDatas.get(i).getContent());
                countcollect.add(baoXiaDatas.get(i).getCountcollect());
                countlike.add(baoXiaDatas.get(i).getCountlike());
                is_collect.add(baoXiaDatas.get(i).getIs_collect());
                is_like.add(baoXiaDatas.get(i).getIs_like());
            }*/
            if (ids.size() > 0) {
                Log.d("fsaaaaq", "你haoa");
                intent.putIntegerArrayListExtra("article_id_list", ids);
                intent.putExtra("position", position);
                intent.putExtra("hao", position);
                startActivity(intent);
            }
            //intent.putStringArrayListExtra("article_name_list", article_name_list);
            // intent.putStringArrayListExtra("content_list", content_list);
//			intent.putIntegerArrayListExtra("countcollect", countcollect);
//			intent.putIntegerArrayListExtra("countlike", countlike);
//			intent.putIntegerArrayListExtra("is_collect", is_collect);
//			intent.putIntegerArrayListExtra("is_like", is_like);
        }
    }
}
