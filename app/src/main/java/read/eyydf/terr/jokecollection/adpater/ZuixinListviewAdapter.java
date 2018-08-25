package read.eyydf.terr.jokecollection.adpater;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.activity.WebViewActivity;
import read.eyydf.terr.jokecollection.model.BannerData;
import read.eyydf.terr.jokecollection.model.GetListData;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;
import read.eyydf.terr.jokecollection.tools.DownJson;
import read.eyydf.terr.jokecollection.views.MyTransition;

import static read.eyydf.terr.jokecollection.activity.MainActivity.sendGetRequest;

/**
 * Created by fenghu on 2017/5/19.
 */

public class ZuixinListviewAdapter extends BaseAdapter {
    private Context context;
    private List<GetListData> list;
    private BannerData bannerData;
    private ArrayList<String> getBannerList;
    private static boolean is = true;

    public ZuixinListviewAdapter(List<GetListData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        // 返回布局的个数
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        // 返回布局的类型
        if (list.get(position).getContentPictures() == null) {
            return 0;
        }
        if (list.get(position).getContentPictures().size() == 0) {
            return 0;
        }
        int type = list.get(position).getImageNum();
        if (type == 2) {
            type = 1;
        }
        if (type == 3) {
            type = 2;
        }
        return type;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        try {
//            if (position != 2 && position != 6 && position != 12 && position != 20) {

            ViewHolder_no_image holder_no_image = null;
            ViewHolder_one_image holder_one_image = null;
            ViewHolder_three_image holder_three_image = null;
            int type = list.get(position).getImageNum();
            if (list.get(position).getContentPictures().size() > 0) {
                type = list.get(position).getContentPictures().size();
                Log.d("qqqqqq", "type:" + type);
                if (type > 0) {
                    if (type == 1 || type == 2) {
                        type = 1;
                    } else {
                        type = 3;
                    }
                }
            } else {
                type = 0;
            }

//                if (view == null) {
            switch (type) {
                case 0:
                    view = LayoutInflater.from(context).inflate(R.layout.viewpagerfirsthomezuixinfragmentitme_no_image,
                            null);
                    holder_no_image = new ViewHolder_no_image();
                    holder_no_image.title = (TextView) view.findViewById(R.id.title);
                    holder_no_image.content = (TextView) view.findViewById(R.id.content);
                    holder_no_image.dianzan_counts = (TextView) view.findViewById(R.id.dianzan_counts);
                    holder_no_image.shouCang = (TextView) view.findViewById(R.id.shoucang);
                    holder_no_image.dianzan_image = (ImageView) view.findViewById(R.id.dianzan_image);
                    //view.setTag(holder_no_image);
                    break;
                case 1:
                    view = LayoutInflater.from(context).inflate(R.layout.viewpagerfirsthomezuixinfragmentitme_one_image,
                            null);
                    holder_one_image = new ViewHolder_one_image();
                    holder_one_image.title = (TextView) view.findViewById(R.id.title);
                    holder_one_image.content = (TextView) view.findViewById(R.id.content);
                    holder_one_image.dianzan_counts = (TextView) view.findViewById(R.id.dianzan_counts);
                    holder_one_image.shouCang = (TextView) view.findViewById(R.id.shoucang);
                    holder_one_image.dianzan_image = (ImageView) view.findViewById(R.id.dianzan_image);
                    holder_one_image.one_image = (ImageView) view.findViewById(R.id.one_image);
                    //view.setTag(holder_one_image);
                    break;
                case 3:
                    view = LayoutInflater.from(context).inflate(R.layout.viewpagerfirsthomezuixinfragmentitme_three_image,
                            null);
                    holder_three_image = new ViewHolder_three_image();
                    holder_three_image.title = (TextView) view.findViewById(R.id.title);
                    holder_three_image.dianzan_counts = (TextView) view.findViewById(R.id.dianzan_counts);
                    holder_three_image.shouCang = (TextView) view.findViewById(R.id.shoucang);
                    holder_three_image.dianzan_image = (ImageView) view.findViewById(R.id.dianzan_image);
                    holder_three_image.three_image_one = (ImageView) view.findViewById(R.id.three_image_one);
                    holder_three_image.three_image_two = (ImageView) view.findViewById(R.id.three_image_two);
                    holder_three_image.three_image_three = (ImageView) view.findViewById(R.id.three_image_three);
                    //view.setTag(holder_three_image);
                    break;
            }
//                } else {
//                    switch (type) {
//                        case 0:
//                            holder_no_image = (ViewHolder_no_image) view.getTag();
//                            break;
//                        case 1:
//                            holder_one_image = (ViewHolder_one_image) view.getTag();
//                            break;
//                        case 3:
//                            holder_three_image = (ViewHolder_three_image) view.getTag();
//                            break;
//                    }
//                }
            Log.d("ertrtert", list.get(position).getContent());
            switch (type) {
                case 0:
                    holder_no_image.title.setText(list.get(position).getArticle_name().trim());
                    holder_no_image.content.setText(list.get(position).getContent().trim());
                    holder_no_image.dianzan_counts.setText(list.get(position).getCountlike() + "点赞");
                    holder_no_image.shouCang.setText(list.get(position).getCountcollect() + "收藏");
                    if (list.get(position).getIs_like() == 1) {
                        //holder_no_image.dianzan_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.bluedianzan));
                    }
                    break;
                case 1:
                    holder_one_image.title.setText(list.get(position).getArticle_name().trim());
                    holder_one_image.content.setText(list.get(position).getContent().trim());
                    RequestOptions requestOptions = RequestOptions.centerCropTransform().optionalTransform(new MyTransition(context));
                    if (list.get(position).getContentPictures().size() > 0) {
                        Glide.with(context).applyDefaultRequestOptions(requestOptions).load(list.get(position).getContentPictures().get(0))
                                .into(holder_one_image.one_image);
                        Log.d("fdsfsdfdsfaaaa", list.get(position).getContentPictures().get(0));
                    }
                    holder_one_image.dianzan_counts.setText(list.get(position).getCountlike() + "点赞");
                    holder_one_image.shouCang.setText(list.get(position).getCountcollect() + "收藏");
                    if (list.get(position).getIs_like() == 1) {
                        //holder_no_image.dianzan_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.bluedianzan));
                    }
                    break;
                case 3:
                    holder_three_image.title.setText(list.get(position).getArticle_name().trim());
                    RequestOptions requestOptions1 = RequestOptions.centerCropTransform().optionalTransform(new MyTransition(context));
                    if (list.get(position).getContentPictures().size() > 0) {
                        Glide.with(context).applyDefaultRequestOptions(requestOptions1).load(list.get(position).getContentPictures().get(0))
                                .into(holder_three_image.three_image_one);
                    }
                    if (list.get(position).getContentPictures().size() > 1) {
                        Glide.with(context).applyDefaultRequestOptions(requestOptions1).load(list.get(position).getContentPictures().get(1))
                                .into(holder_three_image.three_image_two);
                    }
                    if (list.get(position).getContentPictures().size() > 2) {
                        Glide.with(context).applyDefaultRequestOptions(requestOptions1).load(list.get(position).getContentPictures().get(2))
                                .into(holder_three_image.three_image_three);
                    }
                    holder_three_image.dianzan_counts.setText(list.get(position).getCountlike() + "点赞");
                    holder_three_image.shouCang.setText(list.get(position).getCountcollect() + "收藏");
                    if (list.get(position).getIs_like() == 1) {
                        //holder_no_image.dianzan_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.bluedianzan));
                    }
                    break;
            }
          /*  } else {
                view = LayoutInflater.from(context).inflate(R.layout.bannerlayout,
                        null);
                final ViewHolder_banner viewHolder_banner = new ViewHolder_banner();
                viewHolder_banner.banner = view.findViewById(R.id.tuijian_banner_viewpager);
                if(is){
                    getBannerData(viewHolder_banner.banner, position);
                    Log.d("ZuixinListviewAdapter", "yici");
                }
            }*/
        } catch (Exception e) {
            Log.d("ZuixinListviewAdapter", e.getMessage().toString());
        }
        Log.d("jiance", list.get(position).getArticle_id() + ",收藏数为" + list.get(position).getCountcollect() + ",点赞数位" + list.get(position).getCountlike());
        return view;
    }

    private void getBannerData(final ImageView imageView, final int position) {
        final DownJson downJson = new DownJson(1, 1, -1, -1, 0, null, null, -1, -1, -1);
        downJson.FreedomLoadTask(new DownJson.FreedomCallback() {
            @Override
            public void jsonLoaded(String[] json, String tag) {
                if (tag.equals("ready")) {
                    try {
                        JSONObject jsonObject = new JSONObject(json[0]);
                        getBannerList = new ArrayList<>();
                        JSONArray bannerlist = jsonObject.getJSONArray("bannerlist");
                        Log.d("FirstHometuijiane", bannerlist.toString());
                        for (int i = 0; i < bannerlist.length(); i++) {
                            JSONObject jsonObject1 = bannerlist.getJSONObject(i);
                            JSONArray jsonArray = jsonObject1.getJSONArray("bannerImgs");
                            for (int j = 0; j < jsonArray.length(); j++) {
                                getBannerList.add(ActivityUtils.url_request + jsonArray.getJSONObject(j).getString("url"));
                            }
                        }
                    } catch (Exception e) {

                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String response = null;
                                if (context.getPackageName().equals("read.eyydf.jokecollection.episode"))
                                    response = sendGetRequest("http://app.51babyapp.com/AD/HUDONG/xiaohua3_01.json");
                                else if (context.getPackageName().equals("read.terr.jokecollection.encyclopedia"))
                                    response = sendGetRequest("http://app.51babyapp.com/AD/HUDONG/xiaohua2_01.json");
                                else if (context.getPackageName().equals("read.eyydf.terr.jokecollection"))
                                    response = sendGetRequest("http://app.51babyapp.com/AD/HUDONG/xiaohua1_01.json");
                                JSONObject jsonObject = new JSONObject(response);
                                bannerData = new BannerData();
                                bannerData.setOpen(jsonObject.getBoolean("isOpen"));
                                JSONArray jsonArray = jsonObject.getJSONArray("url");
                                Random random = new Random();
                                bannerData.setUrl(jsonArray.getString(random.nextInt(jsonArray.length())));
                                ((Activity) context).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (bannerData.isOpen() && getBannerList.size() != 0) {
                                            is = false;
                                            Log.d("ZuixinListviewAdapter", "hello");
                                            initImage(imageView, position);
                                        }
                                    }
                                });
                            } catch (Exception e) {
                                Log.d("ZuixinListviewAdap", e.getMessage().toString());
                            }
                        }
                    }).start();
                }
            }
        });
        downJson.execute(ActivityUtils.url);
    }

    private void initImage(ImageView imageView, int position) {
        imageView.setVisibility(View.VISIBLE);
        try {
            switch (position) {
                case 2:
                    Glide.with(context).load(getBannerList.get(0)).into(imageView);
                    break;
                case 6:
                    Glide.with(context).load(getBannerList.get(1)).into(imageView);
                    break;
                case 12:
                    Glide.with(context).load(getBannerList.get(2)).into(imageView);
                    break;
                case 20:
                    Glide.with(context).load(getBannerList.get(3)).into(imageView);
                    break;
            }
        } catch (Exception e) {

        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(context, WebViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("geturl", bannerData.getUrl());
                intent.putExtra("url", bundle);
                context.startActivity(intent);
            }
        });

        /*Log.d("ZuixinListviewAdapter", "我要初始化了");
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
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.setViewPagerIsScroll(false);
        banner.setBannerAnimation(Transformer.Default);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                final Intent intent = new Intent(context, WebViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("geturl", bannerData.getUrl());
                intent.putExtra("url", bundle);
                context.startActivity(intent);
            }
        });
        banner.start();*/
    }

    class ViewHolder_no_image {
        TextView title, content, dianzan_counts, shouCang;
        ImageView dianzan_image;
    }

    class ViewHolder_one_image {
        TextView title, content, dianzan_counts, shouCang;
        ImageView dianzan_image, one_image;
    }

    class ViewHolder_three_image {
        TextView title, dianzan_counts, shouCang;
        ImageView dianzan_image, three_image_one, three_image_two, three_image_three;
    }

    class ViewHolder_banner {
        ImageView banner;
    }
}
