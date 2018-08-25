package read.eyydf.terr.jokecollection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.adpater.ZuixinListviewAdapter;
import read.eyydf.terr.jokecollection.model.GetListData;
import read.eyydf.terr.jokecollection.pullableview.PullToRefreshLayout;
import read.eyydf.terr.jokecollection.pullableview.PullableListView;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;
import read.eyydf.terr.jokecollection.tools.DownJson;
import read.eyydf.terr.jokecollection.tools.DownJson1;

/**
 * Created by liang on 2018/8/22.
 */
@ContentView(R.layout.activity_baoxiao)
public class BaoXiaoActivity extends BaseActivity {
    @ViewInject(R.id.pullToRefreshLayout)
    private PullToRefreshLayout pullToRefreshLayout;
    @ViewInject(R.id.zuixin_listview)
    private PullableListView listView;
    private List<GetListData> zuixinListDatas;
    private ZuixinListviewAdapter zuixinListviewAdapter;
    private int index;
    private int type;
    private TextView dianzan_counts;
    private ImageView dianzan_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public void init() {
        x.view().inject(this);
        ActivityUtils.initTranslucentStatus(this);
        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                load_more();
            }
        });
        //DownJson downJson = new DownJson(3, 3, -1, -1, 0, null, null, -1, -1, -1);
        DownJson1 downJson = new DownJson1(35, 2, -1, -1, index, null, null, -1, -1, -1, 2);
        downJson.FreedomLoadTask(new DownJson.FreedomCallback() {
            @Override
            public void jsonLoaded(String[] json, String tag) {
                if (tag.equals("ready")) {
                    try {
                        index++;
                        JSONObject jsonObject = new JSONObject(json[0]);
                        JSONArray articlelist = jsonObject.getJSONArray("columnlist");
                        zuixinListDatas = new ArrayList<>();
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
                            } catch (Exception e) {
                            }
                            zuixinListDatas.add(paihangListData);
                        }
                        //DownJson downJson1 = new DownJson(3, 3, -1, -1, 1, null, null, -1, -1, -1);
                        DownJson1 downJson1 = new DownJson1(35, 2, -1, -1, index, null, null, -1, -1, -1, 2);
                        downJson1.FreedomLoadTask(new DownJson.FreedomCallback() {
                            @Override
                            public void jsonLoaded(String[] json, String tag) {
                                if (tag.equals("ready")) {
                                    try {
                                        index++;
                                        JSONObject jsonObject = new JSONObject(json[0]);
                                        JSONArray articlelist = jsonObject.getJSONArray("columnlist");
                                        for (int i = 0; i < articlelist.length(); i++) {
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
                                            zuixinListDatas.add(paihangListData);
                                        }
                                        if (zuixinListDatas == null) {
                                            return;
                                        }
                                        listViewSet();
                                    } catch (Exception e) {
                                    }
                                } else {
                                    listViewSet();
                                }
                            }
                        });
                        downJson1.execute(ActivityUtils.baikeribaourl);
                    } catch (Exception e) {
                    }
                }
            }
        });
        downJson.execute(ActivityUtils.baikeribaourl);
    }

    private void listViewSet() {
        zuixinListviewAdapter = new ZuixinListviewAdapter(zuixinListDatas,
                this);
        listView.setAdapter(zuixinListviewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                dianzan_counts = (TextView) arg1.findViewById(R.id.dianzan_counts);
                dianzan_image = (ImageView) arg1.findViewById(R.id.dianzan_image);
                click_xiaohua(arg2);
            }
        });
    }

    private void load_more() {
        DownJson1 downJson1 = new DownJson1(35, 2, -1, -1, index, null, null, -1, -1, -1, 2);
        //DownJson downJson1 = new DownJson(3, 3, -1, -1, index, null, null, -1, -1, -1);
        downJson1.FreedomLoadTask(new DownJson.FreedomCallback() {
            @Override
            public void jsonLoaded(String[] json, String tag) {
                if (tag.equals("ready")) {
                    index++;
                    final List<GetListData> paihangListDataTemp = new ArrayList<>();
                    paihangListDataTemp.addAll(zuixinListDatas);
                    int count = zuixinListDatas.size();
                    try {
                        JSONObject jsonObject = new JSONObject(json[0]);
                        JSONArray articlelist = jsonObject.getJSONArray("columnlist");
                        if (articlelist.length() == 0) {
                            pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                            Toast.makeText(BaoXiaoActivity.this, "已加载所有数据!", Toast.LENGTH_SHORT).show();
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
                            } catch (Exception e) {
                            }
                            paihangListDataTemp.add(paihangListData);
                        }
                        zuixinListDatas.clear();
                        zuixinListDatas.addAll(paihangListDataTemp);
                        zuixinListviewAdapter.notifyDataSetChanged();
                        listView.setSelection(count - 2);

                    } catch (Exception e) {
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                        Toast.makeText(BaoXiaoActivity.this, "已加载所有数据!", Toast.LENGTH_SHORT).show();
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

    private void click_xiaohua(int position) {
        Intent intent = new Intent(this, ContentActivity.class);
        ArrayList<Integer> article_id_list = new ArrayList<>();
        ArrayList<String> article_name_list = new ArrayList<>();
        ArrayList<String> content_list = new ArrayList<>();
        ArrayList<Integer> countcollect = new ArrayList<>();
        ArrayList<Integer> countlike = new ArrayList<>();
        ArrayList<Integer> is_collect = new ArrayList<>();
        ArrayList<Integer> is_like = new ArrayList<>();
        for (int i = 0; i < zuixinListDatas.size(); i++) {
            article_id_list.add(zuixinListDatas.get(i).getArticle_id());
            article_name_list.add(zuixinListDatas.get(i).getArticle_name());
            content_list.add(zuixinListDatas.get(i).getContent());
            countcollect.add(zuixinListDatas.get(i).getCountcollect());
            countlike.add(zuixinListDatas.get(i).getCountlike());
            is_collect.add(zuixinListDatas.get(i).getIs_collect());
            is_like.add(zuixinListDatas.get(i).getIs_like());
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
