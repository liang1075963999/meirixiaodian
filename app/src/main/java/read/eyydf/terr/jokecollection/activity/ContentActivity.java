package read.eyydf.terr.jokecollection.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.model.ItemData;
import read.eyydf.terr.jokecollection.model.TuiJianEntity;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;
import read.eyydf.terr.jokecollection.tools.DownJson;
import read.eyydf.terr.jokecollection.tools.DownJson1;
import read.eyydf.terr.jokecollection.views.MyRecyclerView;
import read.eyydf.terr.jokecollection.views.MyScrollview;
import read.eyydf.terr.jokecollection.views.zitiTextView;

/**
 * Created by fenghu on 2017/5/18.
 */
@ContentView(R.layout.contentactivitylayout)
public class ContentActivity extends BaseActivity {
    /*@ViewInject(R.id.cet_main_tv)
    private zitiTextView barName;
    @ViewInject(R.id.content_article_name)
    private TextView content_article_name;
    @ViewInject(R.id.content_content)
    private LinearLayout content_content;
    @ViewInject(R.id.content_dianzan_text)
    private TextView content_dianzan_text;
    @ViewInject(R.id.content_shoucang_text)
    private TextView content_shoucang_text;
    @ViewInject(R.id.next_title)
    private TextView next_title;
    @ViewInject(R.id.content_dianzan)
    private ImageView content_dianzan;
    @ViewInject(R.id.content_shoucang)
    private ImageView content_shoucang;
    @ViewInject(R.id.content_layout)
    private LinearLayout content_layout;
    @ViewInject(R.id.scrollView)
    private ScrollView scrollView;
    @ViewInject(R.id.web_layout)
    private int article_id = -1;
    private int position = -1;
    private int isShoucang = 1;
    private ArrayList<Integer> article_id_list;
    private ArrayList<String> article_name_list;
    private int dianzanCount;
    private int shoucangCount;
    private boolean tuiJian;*/
    @ViewInject(R.id.cet_main_tv)
    private zitiTextView barName;
    @ViewInject(R.id.recycler)
    private MyRecyclerView recyclerView;
    private int dianzanCount;
    private int article_id = -1;
    private int position1 = -1;
    private int isShoucang = 1;
    private ArrayList<Integer> article_id_list;
    private ArrayList<String> article_name_list;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private boolean tuiJian;
    private int shoucangCount;
    private int fangXiang;
    private List<ItemData> itemDatas;
    private int haoma = -1;
    private boolean is;
    private ArrayList<TuiJianEntity.Choicenesslistshiti> zhi;
    private int wenzhangID;
    private int iio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getApplication().getPackageName().equals("read.eyydf.terr.jokecollection"))
            ClassicsHeader.REFRESH_HEADER_LASTTIME = "爆笑日报";
        if (getApplication().getPackageName().equals("read.terr.jokecollection.encyclopedia"))
            ClassicsHeader.REFRESH_HEADER_LASTTIME = "爆笑百科";
        if (getApplication().getPackageName().equals("read.eyydf.jokecollection.episode"))
            ClassicsHeader.REFRESH_HEADER_LASTTIME = "每日笑点";
        ClassicsHeader.REFRESH_HEADER_PULLDOWN = "下拉加载上一条";
        ClassicsHeader.REFRESH_HEADER_RELEASE = "释放取消";
        ClassicsHeader.REFRESH_HEADER_REFRESHING = "正在加载...";
        ClassicsHeader.REFRESH_HEADER_FINISH = "加载完成";
        ClassicsFooter.REFRESH_FOOTER_PULLUP = "上拉加载下一条";
        ClassicsFooter.REFRESH_FOOTER_RELEASE = "释放取消";
        ClassicsFooter.REFRESH_FOOTER_REFRESHING = "正在加载...";
        x.view().inject(this);
        ActivityUtils.initTranslucentStatus(this);
        itemDatas = new ArrayList<>();
        if (getApplication().getPackageName().equals("read.eyydf.terr.jokecollection"))
            barName.setText("爆笑日报");
        if (getApplication().getPackageName().equals("read.terr.jokecollection.encyclopedia"))
            barName.setText("爆笑百科");
        if (getApplication().getPackageName().equals("read.eyydf.jokecollection.episode"))
            barName.setText("每日笑点");
        article_id_list = new ArrayList<>();
        article_name_list = new ArrayList<>();
        article_id_list = getIntent().getIntegerArrayListExtra("article_id_list");
   /*     for (int i = 0; i < article_id_list.size(); i++) {
            Log.d("ContentActivity", "article_id_list.get(i):" + article_id_list.get(i));
        }*/
/*        08-02 12:00:34.636 6222-6222/read.eyydf.jokecollection.episode D/ContentActivity: article_id_list.get(i):1
        08-02 12:00:34.636 6222-6222/read.eyydf.jokecollection.episode D/ContentActivity: article_id_list.get(i):2
        08-02 12:00:34.636 6222-6222/read.eyydf.jokecollection.episode D/ContentActivity: article_id_list.get(i):3
        08-02 12:00:34.636 6222-6222/read.eyydf.jokecollection.episode D/ContentActivity: article_id_list.get(i):4
        08-02 12:00:34.636 6222-6222/read.eyydf.jokecollection.episode D/ContentActivity: article_id_list.get(i):5
        08-02 12:00:34.636 6222-6222/read.eyydf.jokecollection.episode D/ContentActivity: article_id_list.get(i):6
        08-02 12:00:34.636 6222-6222/read.eyydf.jokecollection.episode D/ContentActivity: article_id_list.get(i):7
        08-02 12:00:34.636 6222-6222/read.eyydf.jokecollection.episode D/ContentActivity: article_id_list.get(i):8
        08-02 12:00:34.636 6222-6222/read.eyydf.jokecollection.episode D/ContentActivity: article_id_list.get(i):9
        08-02 12:00:34.636 6222-6222/read.eyydf.jokecollection.episode D/ContentActivity: article_id_list.get(i):10*/
        article_name_list = getIntent().getStringArrayListExtra("article_name_list");
        position1 = getIntent().getIntExtra("position", -1);
        tuiJian = getIntent().getBooleanExtra("tuijian", false);
        zhi = getIntent().getParcelableArrayListExtra("zhi");
        haoma = getIntent().getIntExtra("hao", -1);
        wenzhangID = getIntent().getIntExtra("wenzhangid", -1);
        init();
        initListener();
    }

    public void init() {
        mLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        mAdapter = new MyAdapter();
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    private void initListener() {
       /* mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onInitComplete() {
                Log.e("hehe", "onInitComplete");
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                Log.e("hehe", "释放位置:" + position + " 下一页:" + isNext);
                int index = 0;
                if (isNext) {
                    index = 0;
                } else {
                    index = 1;
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onPageSelected(int position, boolean isBottom) {
                Log.e("hehe", "选中位置:" + position + "  是否是滑动到底部:" + isBottom);
            }


        });*/
    }

    private void content1(final MyAdapter.ViewHolder holder, int hao) {
        article_id = article_id_list.get(hao);
     /*   Log.d("pppppo", "article_id:" + article_id);
        holder.content_article_name.setText(article_name_list.get(position1).trim());
        if (position1 < article_name_list.size() - 1) {
            holder.next_title.setText(article_name_list.get(position1 + 1));
        }
        Log.d("qqwwq", "article_id:" + article_id);*/
        //DownJson downJson = new DownJson(23, 2, -1, article_id, -1, null, null, ActivityUtils.uerid, -1, -1);
        DownJson1 downJson;
        downJson = new DownJson1(23, 2, -1, article_id, -1, null, null, ActivityUtils.uerid, -1, -1, 2);
        downJson.FreedomLoadTask(new DownJson.FreedomCallback() {
            //ItemData itemData=new ItemData();
            @Override
            public void jsonLoaded(String[] json, String tag) {
                if (tag.equals("ready")) {
                    try {
                        JSONObject jsonObject = new JSONObject(json[0]);
                        Log.d("ContentActivity11", "jsonObject:" + jsonObject);
                        JSONObject article = jsonObject.getJSONObject("article");
                        holder.content_article_name.setText(article.getString("article_name").trim());
                        //content_content.setText(article.getString("content"));
                        holder.riQi.setText(article.getString("create_date"));
                        dianzanCount = article.getInt("countlike");
                        Log.d("ContentActivity", "dianzanCount:" + dianzanCount);
                        holder.content_dianzan_text.setText(dianzanCount + "");
                        // itemData.setDianzanCount(dianzanCount);

                        shoucangCount = article.getInt("countcollect");
                        Log.d("jiance", article_id + ",收藏数为" + shoucangCount + ",点赞数位" + dianzanCount);
                        Log.d("ContentActivity", "shoucangCount:" + shoucangCount);
                        holder.content_shoucang_text.setText(shoucangCount + "");
                        //itemData.setShoucangCount(shoucangCount);
                        holder.liuLanLiang.setText(dianzanCount + shoucangCount + 234 + "人浏览");
                        Log.d("dsdfdsf", article.getInt("is_like") + "," + article.getInt("is_collect"));
                /*    itemData.setIs_like(shoucangCount);
                    itemData.setShoucangCount(shoucangCount);*/
                        if (article.getInt("is_like") > 0) {
                            holder.content_dianzan.setImageDrawable(
                                    ContextCompat.getDrawable(ContentActivity.this, R.drawable.bluedianzan));
                        } else {
                            holder.content_dianzan.setImageDrawable(
                                    ContextCompat.getDrawable(ContentActivity.this, R.drawable.huisedianzan));
                        }
                        if (article.getInt("is_collect") == 1) {
                            isShoucang = 2;
                            holder.content_shoucang
                                    .setImageDrawable(ContextCompat.getDrawable(ContentActivity.this, R.drawable.bluestar));
                        } else {
                            isShoucang = 1;
                            holder.content_shoucang.setImageDrawable(
                                    ContextCompat.getDrawable(ContentActivity.this, R.drawable.huisestar));
                        }
                        final ArrayList<String> arrayList = new ArrayList<>();
                        JSONArray contentPictures = article.getJSONArray("contentPictures");
                        for (int i = 0; i < contentPictures.length(); i++) {
                            JSONObject imageItem = contentPictures.getJSONObject(i);
                            arrayList.add(imageItem.getString("url"));
                        }
                        String[] articles = article.getString("content").split("]");
                        Log.d("fdsfsdfff", article.getString("content"));
                        for (int i = 0; i < articles.length; i++) {
                            zitiTextView zitiTextView = initZiTiView(holder);
                            if (i < articles.length - 1) {
                                if (i == 0 && articles[0].startsWith("[img")) {
                                    final ImageView imageView = initImage(holder);
                                    if (arrayList.size() != 0) {
                                        is = true;
                                        final int ii = i;
                                        Glide.with(ContentActivity.this).load(arrayList.get(0))
                                                .into(imageView);
                                        imageView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(ContentActivity.this, BigImageActivity.class);
                                                intent.putExtra("images", arrayList.get(ii));
                                                startActivity(intent);
                                                overridePendingTransition(0, 0);
                                            }
                                        });
                                        holder.content_content.addView(imageView);
                                    }
                                } else {
                                    if (articles[i].length() > 5)
                                        zitiTextView.setText(articles[i].substring(0, articles[i].length() - 5).trim());
                                    else {
                                        final ImageView imageView = initImage(holder);
                                        if (arrayList.size() != 0 && i < articles.length - 1) {
                                            final int ii = i;
                                            Log.d("tupian", arrayList.get(i));
                                            Glide.with(ContentActivity.this).load(arrayList.get(i))
                                                    .into(imageView);
                                            imageView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(ContentActivity.this, BigImageActivity.class);
                                                    intent.putExtra("images", arrayList.get(ii));
                                                    startActivity(intent);
                                                    overridePendingTransition(0, 0);
                                                }
                                            });
                                            holder.content_content.addView(imageView);
                                        }
                                        continue;
                                    }
                                }
                            } else {
                                if (articles[i].trim().contains("[img")) {
                                    ImageView imageView = initImage(holder);
                                    Glide.with(ContentActivity.this).load(arrayList.get(arrayList.size() - 1))
                                            .into(imageView);
                                    imageView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(ContentActivity.this, BigImageActivity.class);
                                            intent.putExtra("images", arrayList.get(arrayList.size() - 1));
                                            startActivity(intent);
                                            overridePendingTransition(0, 0);
                                        }
                                    });
                                    holder.content_content.addView(imageView);
                                    //zitiTextView.setText(articles[i].trim().substring(0, articles[i].length() - 5));
                                } else {
                                    zitiTextView.setText(articles[i].trim());
                                }
                            }
                            holder.content_content.addView(zitiTextView);
                            final ImageView imageView = initImage(holder);
                            //imageView.setScaleType(ScaleType.MATRIX);
                            try {
                                if (!is) {
                                    if (arrayList.size() != 0 && i < articles.length - 1) {
                                        final int ii = i;
                                        Glide.with(ContentActivity.this).load(arrayList.get(i))
                                                .into(imageView);
                                        imageView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(ContentActivity.this, BigImageActivity.class);
                                                intent.putExtra("images", arrayList.get(ii));
                                                startActivity(intent);
                                                overridePendingTransition(0, 0);
                                            }
                                        });
                                        holder.content_content.addView(imageView);
                                    }
                                } else {
                                    if (arrayList.size() != 0 && i < articles.length - 2) {
                                        final int ii = i + 1;
                                        Log.d("tupian", ActivityUtils.url_request + arrayList.get(i + 1));
                                        Glide.with(ContentActivity.this).load(arrayList.get(i + 1))
                                                .into(imageView);
                                        imageView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(ContentActivity.this, BigImageActivity.class);
                                                intent.putExtra("images", arrayList.get(ii));
                                                startActivity(intent);
                                                overridePendingTransition(0, 0);
                                            }
                                        });
                                        holder.content_content.addView(imageView);
                                    }
                                }
                            } catch (Exception e) {

                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }
        });
        downJson.execute(ActivityUtils.baikeribaourl);
    }

    private void content(final MyAdapter.ViewHolder holder) {
        /*position++;
        mAdapter.notifyDataSetChanged();*/
        Log.d("ppp", "执行到此了" + position1);
        article_id = article_id_list.get(position1);
        Log.d("pppppo", "article_id:" + article_id);
        holder.content_article_name.setText(article_name_list.get(position1).trim());
        if (position1 < article_name_list.size() - 1) {
            holder.next_title.setText(article_name_list.get(position1 + 1));
        }
        Log.d("qqwwq", "article_id:" + article_id);
        //DownJson downJson = new DownJson(23, 2, -1, article_id, -1, null, null, ActivityUtils.uerid, -1, -1);
        DownJson1 downJson;
        if (wenzhangID == -1) {
            downJson = new DownJson1(23, 2, -1, article_id, -1, null, null, ActivityUtils.uerid, -1, -1, 2);
            downJson.FreedomLoadTask(new DownJson.FreedomCallback() {
                //ItemData itemData=new ItemData();
                @Override
                public void jsonLoaded(String[] json, String tag) {
                    if (tag.equals("ready")) {
                        try {
                            JSONObject jsonObject = new JSONObject(json[0]);
                            Log.d("ContentActivity11", "jsonObject:" + jsonObject);
                            JSONObject article = jsonObject.getJSONObject("article");

                            //content_content.setText(article.getString("content"));
                            holder.riQi.setText(article.getString("create_date"));
                            dianzanCount = article.getInt("countlike");
                            Log.d("ContentActivity", "dianzanCount:" + dianzanCount);
                            holder.content_dianzan_text.setText(dianzanCount + "");
                            // itemData.setDianzanCount(dianzanCount);

                            shoucangCount = article.getInt("countcollect");
                            Log.d("jiance", article_id + ",收藏数为" + shoucangCount + ",点赞数位" + dianzanCount);
                            Log.d("ContentActivity", "shoucangCount:" + shoucangCount);
                            holder.content_shoucang_text.setText(shoucangCount + "");
                            //itemData.setShoucangCount(shoucangCount);
                            holder.liuLanLiang.setText(dianzanCount + shoucangCount + 234 + "人浏览");
                            Log.d("dsdfdsf", article.getInt("is_like") + "," + article.getInt("is_collect"));
                /*    itemData.setIs_like(shoucangCount);
                    itemData.setShoucangCount(shoucangCount);*/
                            if (article.getInt("is_like") > 0) {
                                holder.content_dianzan.setImageDrawable(
                                        ContextCompat.getDrawable(ContentActivity.this, R.drawable.bluedianzan));
                            } else {
                                holder.content_dianzan.setImageDrawable(
                                        ContextCompat.getDrawable(ContentActivity.this, R.drawable.huisedianzan));
                            }
                            if (article.getInt("is_collect") == 1) {
                                isShoucang = 2;
                                holder.content_shoucang
                                        .setImageDrawable(ContextCompat.getDrawable(ContentActivity.this, R.drawable.bluestar));
                            } else {
                                isShoucang = 1;
                                holder.content_shoucang.setImageDrawable(
                                        ContextCompat.getDrawable(ContentActivity.this, R.drawable.huisestar));
                            }
                            final ArrayList<String> arrayList = new ArrayList<>();
                            JSONArray contentPictures = article.getJSONArray("contentPictures");
                            for (int i = 0; i < contentPictures.length(); i++) {
                                JSONObject imageItem = contentPictures.getJSONObject(i);
                                arrayList.add(imageItem.getString("url"));
                            }
                            String[] articles = article.getString("content").split("]");
                            Log.d("fdsfsdfff", article.getString("content"));
                            for (int i = 0; i < articles.length; i++) {
                                zitiTextView zitiTextView = initZiTiView(holder);
                                if (i < articles.length - 1) {
                                    if (i == 0 && articles[0].startsWith("[img")) {
                                        final ImageView imageView = initImage(holder);
                                        if (arrayList.size() != 0) {
                                            is = true;
                                            final int ii = i;
                                            Glide.with(ContentActivity.this).load(arrayList.get(0))
                                                    .into(imageView);
                                            imageView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(ContentActivity.this, BigImageActivity.class);
                                                    intent.putExtra("images", arrayList.get(ii));
                                                    startActivity(intent);
                                                    overridePendingTransition(0, 0);
                                                }
                                            });
                                            holder.content_content.addView(imageView);
                                        }
                                    } else {
                                        if (articles[i].length() > 5)
                                            zitiTextView.setText(articles[i].substring(0, articles[i].length() - 5).trim());
                                        else {
                                            final ImageView imageView = initImage(holder);
                                            if (arrayList.size() != 0 && i < articles.length - 1) {
                                                final int ii = i;
                                                Log.d("tupian", arrayList.get(i));
                                                Glide.with(ContentActivity.this).load(arrayList.get(i))
                                                        .into(imageView);
                                                imageView.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Intent intent = new Intent(ContentActivity.this, BigImageActivity.class);
                                                        intent.putExtra("images", arrayList.get(ii));
                                                        startActivity(intent);
                                                        overridePendingTransition(0, 0);
                                                    }
                                                });
                                                holder.content_content.addView(imageView);
                                            }
                                            continue;
                                        }
                                    }
                                } else {
                                    if (articles[i].trim().contains("[img")) {
                                        ImageView imageView = initImage(holder);
                                        Glide.with(ContentActivity.this).load(arrayList.get(arrayList.size() - 1))
                                                .into(imageView);
                                        imageView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(ContentActivity.this, BigImageActivity.class);
                                                intent.putExtra("images", arrayList.get(arrayList.size() - 1));
                                                startActivity(intent);
                                                overridePendingTransition(0, 0);
                                            }
                                        });
                                        holder.content_content.addView(imageView);
                                        //zitiTextView.setText(articles[i].trim().substring(0, articles[i].length() - 5));
                                    } else {
                                        zitiTextView.setText(articles[i].trim());
                                    }
                                }
                                holder.content_content.addView(zitiTextView);
                                final ImageView imageView = initImage(holder);
                                //imageView.setScaleType(ScaleType.MATRIX);
                                try {
                                    if (!is) {
                                        if (arrayList.size() != 0 && i < articles.length - 1) {
                                            final int ii = i;
                                            Glide.with(ContentActivity.this).load(arrayList.get(i))
                                                    .into(imageView);
                                            imageView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(ContentActivity.this, BigImageActivity.class);
                                                    intent.putExtra("images", arrayList.get(ii));
                                                    startActivity(intent);
                                                    overridePendingTransition(0, 0);
                                                }
                                            });
                                            holder.content_content.addView(imageView);
                                        }
                                    } else {
                                        if (arrayList.size() != 0 && i < articles.length - 2) {
                                            final int ii = i + 1;
                                            Log.d("tupian", ActivityUtils.url_request + arrayList.get(i + 1));
                                            Glide.with(ContentActivity.this).load(arrayList.get(i + 1))
                                                    .into(imageView);
                                            imageView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(ContentActivity.this, BigImageActivity.class);
                                                    intent.putExtra("images", arrayList.get(ii));
                                                    startActivity(intent);
                                                    overridePendingTransition(0, 0);
                                                }
                                            });
                                            holder.content_content.addView(imageView);
                                        }
                                    }
                                } catch (Exception e) {

                                }
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            });
            downJson.execute(ActivityUtils.baikeribaourl);

        } else {
            downJson = new DownJson1(34, 2, -1, article_id, -1, null, null, ActivityUtils.uerid, -1, -1, 2);
            downJson.FreedomLoadTask(new DownJson.FreedomCallback() {
                //ItemData itemData=new ItemData();
                @Override
                public void jsonLoaded(String[] json, String tag) {
                    if (tag.equals("ready")) {
                        try {
                            JSONObject jsonObject = new JSONObject(json[0]);
                            Log.d("ContentActivity11", "jsonObject:" + jsonObject);
                            JSONObject picture = jsonObject.getJSONObject("picture");
                            holder.content_article_name.setText(picture.getString("article_name").trim());
                            //content_content.setText(article.getString("content"));
                            holder.riQi.setText(picture.getString("create_date"));
                            dianzanCount = picture.getInt("countlike");
                            Log.d("ContentActivity", "dianzanCount:" + dianzanCount);
                            holder.content_dianzan_text.setText(dianzanCount + "");
                            // itemData.setDianzanCount(dianzanCount);

                            shoucangCount = picture.getInt("countcollect");
                            Log.d("jiance", article_id + ",收藏数为" + shoucangCount + ",点赞数位" + dianzanCount);
                            Log.d("ContentActivity", "shoucangCount:" + shoucangCount);
                            holder.content_shoucang_text.setText(shoucangCount + "");
                            holder.liuLanLiang.setVisibility(View.INVISIBLE);
                            holder.content_dianzan.setVisibility(View.INVISIBLE);
                            holder.content_dianzan_text.setVisibility(View.INVISIBLE);
                            holder.content_shoucang_text.setVisibility(View.INVISIBLE);
                            holder.content_shoucang.setVisibility(View.INVISIBLE);
                            //itemData.setShoucangCount(shoucangCount);
                           // holder.liuLanLiang.setText(dianzanCount + shoucangCount + 234 + "人浏览");
                          /*  Log.d("dsdfdsf", article.getInt("is_like") + "," + article.getInt("is_collect"));*/
                /*    itemData.setIs_like(shoucangCount);
                    itemData.setShoucangCount(shoucangCount);*/
                           /* if (picture.getInt("is_like") > 0) {
                                holder.content_dianzan.setImageDrawable(
                                        ContextCompat.getDrawable(ContentActivity.this, R.drawable.bluedianzan));
                            } else {
                                holder.content_dianzan.setImageDrawable(
                                        ContextCompat.getDrawable(ContentActivity.this, R.drawable.huisedianzan));
                            }
                            if (picture.getInt("is_collect") == 1) {
                                isShoucang = 2;
                                holder.content_shoucang
                                        .setImageDrawable(ContextCompat.getDrawable(ContentActivity.this, R.drawable.bluestar));
                            } else {
                                isShoucang = 1;
                                holder.content_shoucang.setImageDrawable(
                                        ContextCompat.getDrawable(ContentActivity.this, R.drawable.huisestar));
                            }*/
                            final ArrayList<String> arrayList = new ArrayList<>();
                            JSONArray contentPictures = picture.getJSONArray("contentPictures");
                            for (int i = 0; i < contentPictures.length(); i++) {
                                JSONObject imageItem = contentPictures.getJSONObject(i);
                                arrayList.add(imageItem.getString("url"));
                            }
                            for (int i = 0; i < arrayList.size(); i++) {
                                iio = i;
                                ImageView imageView = initImage(holder);
                                Glide.with(ContentActivity.this).load(arrayList.get(i))
                                        .into(imageView);
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(ContentActivity.this, BigImageActivity.class);
                                        intent.putExtra("images", arrayList.get(iio));
                                        startActivity(intent);
                                        overridePendingTransition(0, 0);
                                    }
                                });
                                holder.content_content.addView(imageView);
                            }
                          /*  String[] articles = picture.getString("content").split("]");
                            Log.d("fdsfsdfff", picture.getString("content"));
                            for (int i = 0; i < articles.length; i++) {
                                zitiTextView zitiTextView = initZiTiView(holder);
                                if (i < articles.length - 1) {
                                    if (i == 0 && articles[0].startsWith("[img")) {
                                        final ImageView imageView = initImage(holder);
                                        if (arrayList.size() != 0) {
                                            is = true;
                                            final int ii = i;
                                            Glide.with(ContentActivity.this).load(arrayList.get(0))
                                                    .into(imageView);
                                            imageView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(ContentActivity.this, BigImageActivity.class);
                                                    intent.putExtra("images", arrayList.get(ii));
                                                    startActivity(intent);
                                                    overridePendingTransition(0, 0);
                                                }
                                            });
                                            holder.content_content.addView(imageView);
                                        }
                                    } else {
                                        if (articles[i].length() > 5)
                                            zitiTextView.setText(articles[i].substring(0, articles[i].length() - 5).trim());
                                        else {
                                            final ImageView imageView = initImage(holder);
                                            if (arrayList.size() != 0 && i < articles.length - 1) {
                                                final int ii = i;
                                                Log.d("tupian", arrayList.get(i));
                                                Glide.with(ContentActivity.this).load(arrayList.get(i))
                                                        .into(imageView);
                                                imageView.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Intent intent = new Intent(ContentActivity.this, BigImageActivity.class);
                                                        intent.putExtra("images", arrayList.get(ii));
                                                        startActivity(intent);
                                                        overridePendingTransition(0, 0);
                                                    }
                                                });
                                                holder.content_content.addView(imageView);
                                            }
                                            continue;
                                        }
                                    }
                                } else {
                                    if (articles[i].trim().contains("[img")) {
                                        ImageView imageView = initImage(holder);
                                        Glide.with(ContentActivity.this).load(arrayList.get(arrayList.size() - 1))
                                                .into(imageView);
                                        imageView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(ContentActivity.this, BigImageActivity.class);
                                                intent.putExtra("images", arrayList.get(arrayList.size() - 1));
                                                startActivity(intent);
                                                overridePendingTransition(0, 0);
                                            }
                                        });
                                        holder.content_content.addView(imageView);
                                        //zitiTextView.setText(articles[i].trim().substring(0, articles[i].length() - 5));
                                    } else {
                                        zitiTextView.setText(articles[i].trim());
                                    }
                                }
                                holder.content_content.addView(zitiTextView);
                                final ImageView imageView = initImage(holder);
                                //imageView.setScaleType(ScaleType.MATRIX);
                             *//*   try {
                                    if (!is) {
                                        if (arrayList.size() != 0 && i < articles.length - 1) {
                                            final int ii = i;
                                            Glide.with(ContentActivity.this).load(arrayList.get(i))
                                                    .into(imageView);
                                            imageView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(ContentActivity.this, BigImageActivity.class);
                                                    intent.putExtra("images", arrayList.get(ii));
                                                    startActivity(intent);
                                                    overridePendingTransition(0, 0);
                                                }
                                            });
                                            holder.content_content.addView(imageView);
                                        }
                                    } else {
                                        if (arrayList.size() != 0 && i < articles.length - 2) {
                                            final int ii = i + 1;
                                            Log.d("tupian", ActivityUtils.url_request + arrayList.get(i + 1));
                                            Glide.with(ContentActivity.this).load(arrayList.get(i + 1))
                                                    .into(imageView);
                                            imageView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(ContentActivity.this, BigImageActivity.class);
                                                    intent.putExtra("images", arrayList.get(ii));
                                                    startActivity(intent);
                                                    overridePendingTransition(0, 0);
                                                }
                                            });
                                            holder.content_content.addView(imageView);
                                        }
                                    }
                                } catch (Exception e) {

                                }*//*
                            }*/
                        } catch (Exception e) {
                        }
                    }
                }
            });
            downJson.execute(ActivityUtils.baikeribaourl);
        }
    }

    @Event(value = {R.id.content_back, R.id.content_dianzan, R.id.content_shoucang,
            R.id.next_content}, type = View.OnClickListener.class)
    private void click(View v) {
        switch (v.getId()) {
            case R.id.content_back:
                click_content_back();
                break;
        }
    }

    public int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private ImageView initImage(final MyAdapter.ViewHolder holder) {
        ImageView imageView = new ImageView(ContentActivity.this);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) holder.content_content
                .getLayoutParams();
        marginLayoutParams.setMargins(dp2px(20), dp2px(20), dp2px(20), dp2px(0));
        // imageView.setLayoutParams(marginLayoutParams);
        imageView.setPadding(0, dp2px(10), 0, dp2px(20));
        return imageView;
    }

    private zitiTextView initZiTiView(MyAdapter.ViewHolder holder) {
        zitiTextView zitiTextView = new zitiTextView(ContentActivity.this);
  /*      ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) holder.content_content
                .getLayoutParams();*/
        // marginLayoutParams.setMargins(dp2px(20), dp2px(15), dp2px(20), dp2px(0));
        // zitiTextView.setLayoutParams(marginLayoutParams);
        // zitiTextView.setPadding(dp2px(10), dp2px(20), dp2px(10), dp2px(20));
        zitiTextView.setTextColor(getResources().getColor(R.color.gray_dark));
        zitiTextView.setTextSize(17);
        zitiTextView.setLineSpacing(13f, 2f);
        zitiTextView.setBackgroundColor(Color.parseColor("#ffffff"));
        return zitiTextView;
    }

    private void click_content_back() {
        finish();
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        MyAdapter() {
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item, parent, false);
            return new MyAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {
            holder.content_dianzan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click_content_dianzan(holder);
                }
            });
            holder.content_shoucang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click_content_shoucang(holder);
                }
            });
            holder.next_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click_next_content(holder);
                }
            });
            holder.smart.setEnableAutoLoadmore(false);
            holder.smart.setRefreshHeader(new ClassicsHeader(ContentActivity.this));
            holder.smart.setRefreshFooter(new ClassicsFooter(ContentActivity.this));
            holder.smart.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    //mAdapter.notifyItemRangeRemoved(0,mAdapter.getItemCount());
              /*      if(position!=0){*/
                    if (haoma != -1) {
                        haoma--;
                        if (haoma > -1) {
                            click_next_content1(holder, haoma);
                            //content1(holder, haoma);
                        } else {
                            haoma = 0;
                            Toast.makeText(ContentActivity.this, "已加载全部数据", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        position1--;
                        if (position1 > -1 && position1 < article_name_list.size()) {
                            mAdapter = new MyAdapter();
                            recyclerView.setAdapter(mAdapter);
                        } else if (position1 == -1) {
                            position1 = 0;
                            Toast.makeText(ContentActivity.this, "已经是第一条了", Toast.LENGTH_SHORT).show();
                        }

                        Log.d("MyAdaptershangla", "position1:" + position1);
                    }
                    //content(holder);
                    refreshlayout.finishRefresh(500);
                  /*  }else {
                        ClassicsHeader.REFRESH_HEADER_PULLDOWN="已经是第一条了";
                        Toast.makeText(ContentActivity.this, "已经是第一条了", Toast.LENGTH_SHORT).show();
                        refreshlayout.finishRefresh(500);
                    }*/
                }
            });
            holder.smart.setOnLoadmoreListener(new OnLoadmoreListener() {
                @Override
                public void onLoadmore(RefreshLayout refreshlayout) {
                    if (haoma != -1) {
                        haoma++;
                        if (haoma < 3) {
                            click_next_content1(holder, haoma);
                            //content1(holder, haoma);
                        } else {
                            haoma = 2;
                            Toast.makeText(ContentActivity.this, "已加载全部数据", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        position1++;
                        if (position1 > -1 && position1 < article_name_list.size()) {
                            mAdapter = new MyAdapter();
                            recyclerView.setAdapter(mAdapter);
                        } else if (position1 == article_name_list.size()) {
                            position1 = article_name_list.size() - 1;
                            Toast.makeText(ContentActivity.this, "已经是最后一条了", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("MyAdapterxiala", "position1:" + position1);
                    }
                    refreshlayout.finishLoadmore(500);
                }
            });
          /*  if (position1 == -1)
                Toast.makeText(ContentActivity.this, "已经是第一条了", Toast.LENGTH_SHORT).show();
            else if (position1 == 10)
                Toast.makeText(ContentActivity.this, "已经是最后一条了", Toast.LENGTH_SHORT).show();
            else if (position1 < 10)
                content(holder);*/
          /*  if (biaoShi == 0) {
                biaoShi++;
            }*/
            if (haoma > -1 && haoma < 3) {
                content1(holder, haoma);
            } else if (position1 > -1 && position1 < article_name_list.size()) {
                content(holder);
            }/* else if (position1 == -1) {
                Toast.makeText(ContentActivity.this, "已经是第一条了", Toast.LENGTH_SHORT).show();
            }*/
            /*if (position1 != 10) {
                Log.d("ppp", "执行到此了hahahaha" + position1);
                content(holder);
            } */
        /*    else if (position1 == 10)
                Toast.makeText(ContentActivity.this, "已经是最后一条了", Toast.LENGTH_SHORT).show();*/
           /* holder.scrollView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Log.d("MyAdapter", "v.getScrollY():" + v.getScrollY());
                    Log.d("MyAdapter", "fangXiang:" + fangXiang);
//                    if (v.getScrollY() > fangXiang) {//向下
                    // 判断 scrollView 当前滚动位置在顶部
                    if (holder.scrollView.getScrollY() == 0) {
                        Log.d("MyAdapter", "到顶部了");
                        recyclerView.setIsIntercept(true);
                    }

                    // 判断scrollview 滑动到底部
                    // scrollY 的值和子view的高度一样，这人物滑动到了底部
                    else if (holder.scrollView.getChildAt(0).getHeight() - holder.scrollView.getHeight()
                            == holder.scrollView.getScrollY()) {
                        Log.d("MyAdapter", "到底部了");
                        recyclerView.setIsIntercept(false);
                    } else {
                        recyclerView.setIsIntercept(true);
                    }
//                    }
                    *//*if (v.getScrollY() <= fangXiang) {//向上
                        // 判断 scrollView 当前滚动位置在顶部
                        if (holder.scrollView.getScrollY() == 0) {
                            Log.d("MyAdapter", "到顶部了");
                            recyclerView.setIsIntercept(false);
                        }

                        // 判断scrollview 滑动到底部
                        // scrollY 的值和子view的高度一样，这人物滑动到了底部
                        else if (holder.scrollView.getChildAt(0).getHeight() - holder.scrollView.getHeight()
                                == holder.scrollView.getScrollY()) {
                            Log.d("MyAdapter", "到底部了");
                            recyclerView.setIsIntercept(false);
                        } else {
                            recyclerView.setIsIntercept(true);
                        }
                        if (fangXiang == v.getScrollY()) {
                            recyclerView.setIsIntercept(false);
                        }
                    }*//*
                    fangXiang = v.getScrollY();
                    return false;
                }
            });*/
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            zitiTextView content_article_name;
            LinearLayout content_content;
            ImageView content_dianzan;
            ImageView content_shoucang;
            TextView next_content;
            TextView content_dianzan_text;
            TextView next_title;
            TextView content_shoucang_text;
            TextView riQi;
            TextView liuLanLiang;
            LinearLayout content_layout;
            MyScrollview scrollView;
            SmartRefreshLayout smart;

            ViewHolder(View itemView) {
                super(itemView);
                content_article_name = itemView.findViewById(R.id.content_article_name);
                content_content = itemView.findViewById(R.id.content_content);
                content_dianzan = itemView.findViewById(R.id.content_dianzan);
                content_shoucang = itemView.findViewById(R.id.content_shoucang);
                riQi = itemView.findViewById(R.id.riqi);
                liuLanLiang = itemView.findViewById(R.id.liulan);
                next_content = itemView.findViewById(R.id.next_content);
                content_dianzan_text = itemView.findViewById(R.id.content_dianzan_text);
                next_title = itemView.findViewById(R.id.next_title);
                content_shoucang_text = itemView.findViewById(R.id.content_shoucang_text);
                content_layout = itemView.findViewById(R.id.content_layout);
                scrollView = itemView.findViewById(R.id.scrollView);
                smart = itemView.findViewById(R.id.smart);
            }
        }
    }

    private void click_content_dianzan(final MyAdapter.ViewHolder holder) {
        // DownJson downJson = new DownJson(31, 2, -1, article_id, -1, null, null, ActivityUtils.uerid, -1, -1);
        DownJson1 downJson = new DownJson1(31, 2, -1, article_id, -1, null, null, ActivityUtils.uerid, -1, -1, 2);
        downJson.FreedomLoadTask(new DownJson.FreedomCallback() {
            @Override
            public void jsonLoaded(String[] json, String tag) {
                try {
                    if (tag.equals("ready")) {
                        if (new JSONObject(json[0]).getInt("result") == 1) {
                            showToast("点赞成功");
                            holder.content_dianzan.setImageDrawable(
                                    ContextCompat.getDrawable(ContentActivity.this, R.drawable.bluedianzan));
                            holder.content_dianzan_text.setText((dianzanCount + 1) + "");
                            String s = "FirstHomezuixinFragment";
                            if (tuiJian) {
                                s = "FirstHometuijianFragment";
                            }
                            Intent intent = new Intent(s);
                            intent.putExtra("isDianzan", true);
                            intent.putExtra("count", (dianzanCount + 1));
                            LocalBroadcastManager.getInstance(ContentActivity.this).sendBroadcast(intent);

                        } else {
                            showToast("已点过赞!");
                        }
                    }
                } catch (Exception e) {
                    showToast("点赞失败!");
                }
            }
        });
        downJson.execute(ActivityUtils.baikeribaourl);
    }

    private void click_content_shoucang(final MyAdapter.ViewHolder holder) {
        if (ActivityUtils.uerid == -1) {
            showToast("未登录,无法使用收藏功能!");
        } else {
            // DownJson downJson = new DownJson(32, 2, -1, article_id, -1, null, null, ActivityUtils.uerid, isShoucang, -1);
            DownJson1 downJson = new DownJson1(32, 2, -1, article_id, -1, null, null, ActivityUtils.uerid, isShoucang, -1, 2);
            downJson.FreedomLoadTask(new DownJson.FreedomCallback() {
                @Override
                public void jsonLoaded(String[] json, String tag) {
                    try {
                        if (tag.equals("ready")) {
                            if (new JSONObject(json[0]).getInt("result") == 1) {
                                if (isShoucang == 1) {
                                    showToast("收藏成功!");
                                    isShoucang = 2;
                                    shoucangCount++;
                                    holder.content_shoucang_text.setText(shoucangCount + "");
                                    holder.content_shoucang.setImageDrawable(
                                            ContextCompat.getDrawable(ContentActivity.this, R.drawable.bluestar));
                                } else {
                                    showToast("取消收藏成功!");
                                    isShoucang = 1;
                                    shoucangCount--;
                                    holder.content_shoucang_text.setText(shoucangCount + "");
                                    holder.content_shoucang.setImageDrawable(
                                            ContextCompat.getDrawable(ContentActivity.this, R.drawable.huisestar));
                                }
                            } else {
                                if (isShoucang == 1) {
                                    showToast("收藏失败!");
                                } else {
                                    showToast("取消收藏失败!");
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (isShoucang == 1) {
                            showToast("收藏失败!");
                        } else {
                            showToast("取消收藏失败!");
                        }
                    }
                }
            });
            downJson.execute(ActivityUtils.baikeribaourl);
        }
    }

    private void click_next_content1(final MyAdapter.ViewHolder holder, final int h) {
        if (haoma != -1) {
            if (h > -1 && h < 3) {
                Log.d("cffdao", "h:" + h);
                holder.content_content.removeAllViews();
                final ScaleAnimation sa1 = new ScaleAnimation(1.0f, 0f, 1.0f, 0f, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                sa1.setDuration(200);
                final ScaleAnimation sa2 = new ScaleAnimation(0f, 1.0f, 0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                sa2.setDuration(200);
                holder.content_layout.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                holder.content_layout.startAnimation(sa1);
                sa1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        content1(holder, h);
                        holder.scrollView.scrollTo(0, 0);
                    }
                });
                sa2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        holder.content_layout.setLayerType(View.LAYER_TYPE_NONE, null);
                    }
                });
                return;
            } else {
                Toast.makeText(ContentActivity.this, "已加载全部数据", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private void click_next_content(final MyAdapter.ViewHolder holder) {
        if (haoma > -1) {
            if (haoma < 2) {
                holder.content_content.removeAllViews();
                final ScaleAnimation sa1 = new ScaleAnimation(1.0f, 0f, 1.0f, 0f, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                sa1.setDuration(200);
                final ScaleAnimation sa2 = new ScaleAnimation(0f, 1.0f, 0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                sa2.setDuration(200);
                holder.content_layout.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                holder.content_layout.startAnimation(sa1);
                sa1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        haoma++;
                        content1(holder, haoma);
                        holder.scrollView.scrollTo(0, 0);
                    }
                });
                sa2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        holder.content_layout.setLayerType(View.LAYER_TYPE_NONE, null);
                    }
                });
                return;
            } else {
                Toast.makeText(ContentActivity.this, "已加载全部数据", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (position1 < article_name_list.size() - 1) {
            holder.content_content.removeAllViews();
            if (position1 < article_id_list.size() - 1) {
                final ScaleAnimation sa1 = new ScaleAnimation(1.0f, 0f, 1.0f, 0f, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                sa1.setDuration(200);
                final ScaleAnimation sa2 = new ScaleAnimation(0f, 1.0f, 0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                sa2.setDuration(200);
                holder.content_layout.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                holder.content_layout.startAnimation(sa1);
                sa1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        position1++;
                        content(holder);
                        holder.scrollView.scrollTo(0, 0);
                    }
                });
                sa2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        holder.content_layout.setLayerType(View.LAYER_TYPE_NONE, null);
                    }
                });
            }
        } else {
            showToast("已经是最后一条了");

        }
    }
  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Event(value = {R.id.content_back, R.id.content_dianzan, R.id.content_shoucang,
            R.id.next_content}, type = View.OnClickListener.class)
    private void click(View v) {
        switch (v.getId()) {
            case R.id.content_back:
                click_content_back();
                break;
            case R.id.content_dianzan:
                click_content_dianzan();
                break;
            case R.id.content_shoucang:
                click_content_shoucang();
                break;
            case R.id.next_content:
                click_next_content();
                break;
        }
    }

    public void init() {
        x.view().inject(this);
        ActivityUtils.initTranslucentStatus(this);
        if (getApplication().getPackageName().equals("read.eyydf.jokecollection.episode"))
            barName.setText(R.string.duanzibarname);
        else if (getApplication().getPackageName().equals("read.terr.jokecollection.encyclopedia"))
            barName.setText(R.string.baikebarname);
        else if (getApplication().getPackageName().equals("read.eyydf.terr.jokecollection"))
            barName.setText(R.string.ribaobarname);
        article_id_list = new ArrayList<>();
        article_name_list = new ArrayList<>();
        article_id_list = getIntent().getIntegerArrayListExtra("article_id_list");
        article_name_list = getIntent().getStringArrayListExtra("article_name_list");
        position = getIntent().getIntExtra("position", -1);
        tuiJian = getIntent().getBooleanExtra("tuijian", false);
        content();
    }

    *//**
     * 文章内容
     *//*
    private void content() {
        article_id = article_id_list.get(position);
        content_article_name.setText(article_name_list.get(position).trim());
        if (position < article_name_list.size() - 1) {
            next_title.setText(article_name_list.get(position + 1));
        }
        DownJson downJson = new DownJson(23, 2, -1, article_id, -1, null, null, ActivityUtils.uerid, -1, -1);
        downJson.FreedomLoadTask(new FreedomCallback() {
            @Override
            public void jsonLoaded(String[] json, String tag) {
                try {
                    JSONObject jsonObject = new JSONObject(json[0]);
                    JSONObject article = jsonObject.getJSONObject("article");
                    //content_content.setText(article.getString("content"));

                    dianzanCount = article.getInt("countlike");
                    content_dianzan_text.setText(dianzanCount + "");

                    shoucangCount = article.getInt("countcollect");
                    content_shoucang_text.setText(shoucangCount + "");

                    Log.d("dsdfdsf", article.getInt("is_like") + "," + article.getInt("is_collect"));
                    if (article.getInt("is_like") > 0) {
                        content_dianzan.setImageDrawable(
                                ContextCompat.getDrawable(ContentActivity.this, R.drawable.bluedianzan));
                    } else {
                        content_dianzan.setImageDrawable(
                                ContextCompat.getDrawable(ContentActivity.this, R.drawable.huisedianzan));
                    }
                    if (article.getInt("is_collect") == 1) {
                        isShoucang = 2;
                        content_shoucang
                                .setImageDrawable(ContextCompat.getDrawable(ContentActivity.this, R.drawable.bluestar));
                    } else {
                        isShoucang = 1;
                        content_shoucang.setImageDrawable(
                                ContextCompat.getDrawable(ContentActivity.this, R.drawable.huisestar));
                    }
                    ArrayList<String> arrayList = new ArrayList<>();
                    JSONArray contentPictures = article.getJSONArray("contentPictures");
                    for (int i = 0; i < contentPictures.length(); i++) {
                        JSONObject imageItem = contentPictures.getJSONObject(i);
                        arrayList.add(imageItem.getString("url"));
                    }
                    String[] articles = article.getString("content").split("]");
                    for (int i = 0; i < articles.length; i++) {
                        zitiTextView zitiTextView = initZiTiView();
                        if (i < article.length()) {
                            zitiTextView.setText(articles[i].substring(0, articles[i].length() - 6));

                        } else
                            zitiTextView.setText(articles[i]);
                        content_content.addView(zitiTextView);
                        ImageView imageView = initImage();
                        //imageView.setScaleType(ScaleType.MATRIX);
                        Glide.with(ContentActivity.this).load(ActivityUtils.url_request + arrayList.get(i))
                                .into(imageView);
                        content_content.addView(imageView);
                    }

                } catch (Exception e) {
                }
            }
        });
        downJson.execute(ActivityUtils.url);
    }

    @NonNull
    private ImageView initImage() {
        ImageView imageView = new ImageView(ContentActivity.this);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) content_content
                .getLayoutParams();
        marginLayoutParams.setMargins(dp2px(20), dp2px(15), dp2px(20), dp2px(0));
        // imageView.setLayoutParams(marginLayoutParams);
        imageView.setPadding(0, dp2px(10), 0, dp2px(10));
        return imageView;
    }

    public int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @NonNull
    private zitiTextView initZiTiView() {
        zitiTextView zitiTextView = new zitiTextView(ContentActivity.this);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) content_content
                .getLayoutParams();
        // marginLayoutParams.setMargins(dp2px(20), dp2px(15), dp2px(20), dp2px(0));
        // zitiTextView.setLayoutParams(marginLayoutParams);
        // zitiTextView.setPadding(dp2px(10), dp2px(20), dp2px(10), dp2px(20));
        zitiTextView.setTextColor(getResources().getColor(R.color.gray_dark));
        zitiTextView.setTextSize(17);
        zitiTextView.setLineSpacing(3f, 1.2f);
        zitiTextView.setBackgroundColor(Color.parseColor("#ffffff"));
        return zitiTextView;
    }

    *//**
     * 点击返回
     *//*
    private void click_content_back() {
        finish();
    }

    *//**
     * 点击点赞
     *//*
    private void click_content_dianzan() {
        DownJson downJson = new DownJson(31, 2, -1, article_id, -1, null, null, ActivityUtils.uerid, -1, -1);
        downJson.FreedomLoadTask(new FreedomCallback() {
            @Override
            public void jsonLoaded(String[] json, String tag) {
                try {
                    if (tag.equals("ready")) {
                        if (new JSONObject(json[0]).getInt("result") == 1) {
                            showToast("点赞成功");
                            content_dianzan.setImageDrawable(
                                    ContextCompat.getDrawable(ContentActivity.this, R.drawable.bluedianzan));
                            content_dianzan_text.setText((dianzanCount + 1) + "");
                            String s = "FirstHomezuixinFragment";
                            if (tuiJian) {
                                s = "FirstHometuijianFragment";
                            }
                            Intent intent = new Intent(s);
                            intent.putExtra("isDianzan", true);
                            intent.putExtra("count", (dianzanCount + 1));
                            LocalBroadcastManager.getInstance(ContentActivity.this).sendBroadcast(intent);

                        } else {
                            showToast("已点过赞!");
                        }
                    }
                } catch (Exception e) {
                    showToast("点赞失败!");
                }
            }
        });
        downJson.execute(ActivityUtils.url);
    }

    *//**
     * 点击收藏
     *//*
    private void click_content_shoucang() {
        if (ActivityUtils.uerid == -1) {
            showToast("未登录,无法使用收藏功能!");
        } else {
            DownJson downJson = new DownJson(32, 2, -1, article_id, -1, null, null, ActivityUtils.uerid, isShoucang,
                    -1);
            downJson.FreedomLoadTask(new FreedomCallback() {
                @Override
                public void jsonLoaded(String[] json, String tag) {
                    try {
                        if (tag.equals("ready")) {
                            if (new JSONObject(json[0]).getInt("result") == 1) {
                                if (isShoucang == 1) {
                                    showToast("收藏成功!");
                                    isShoucang = 2;
                                    shoucangCount++;
                                    content_shoucang_text.setText(shoucangCount + "");
                                    content_shoucang.setImageDrawable(
                                            ContextCompat.getDrawable(ContentActivity.this, R.drawable.bluestar));
                                } else {
                                    showToast("取消收藏成功!");
                                    isShoucang = 1;
                                    shoucangCount--;
                                    content_shoucang_text.setText(shoucangCount + "");
                                    content_shoucang.setImageDrawable(
                                            ContextCompat.getDrawable(ContentActivity.this, R.drawable.huisestar));
                                }
                            } else {
                                if (isShoucang == 1) {
                                    showToast("收藏失败!");
                                } else {
                                    showToast("取消收藏失败!");
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (isShoucang == 1) {
                            showToast("收藏失败!");
                        } else {
                            showToast("取消收藏失败!");
                        }
                    }
                }
            });
            downJson.execute(ActivityUtils.url);
        }
    }

    *//**
     * 点击下一条
     *//*
    private void click_next_content() {
        content_content.removeAllViews();
        if (position < article_id_list.size() - 1) {
            final ScaleAnimation sa1 = new ScaleAnimation(1.0f, 0f, 1.0f, 0f, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            sa1.setDuration(200);
            final ScaleAnimation sa2 = new ScaleAnimation(0f, 1.0f, 0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            sa2.setDuration(200);
            content_layout.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            content_layout.startAnimation(sa1);
            sa1.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    position++;
                    content();
                    scrollView.scrollTo(0, 0);
                }
            });
            sa2.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    content_layout.setLayerType(View.LAYER_TYPE_NONE, null);
                }
            });
        } else {
            showToast("已经是最后一条!");
        }
    }*/
}
