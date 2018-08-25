package read.eyydf.terr.jokecollection.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.activity.ContentActivity;
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

public class ShouCangFragment extends Fragment {
    private PullToRefreshLayout pullToRefreshLayout;
    private PullableListView listView;
    private TextView text_title;
    private List<GetListData> getListDatas;
    private ZuixinListviewAdapter zuixinListviewAdapter;
    private int index;
    private int position;
    private int classId = -1;
    private int type = 3;
    private TextView dianzan_counts;
    private TextView noshoucang;
    private ImageView dianzan_image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shoucang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pullToRefreshLayout = view.findViewById(R.id.pullToRefreshLayout);
        listView = view.findViewById(R.id.type_listview);
        text_title = view.findViewById(R.id.text_title);
        noshoucang = view.findViewById(R.id.noshoucang);

        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                load_more();
            }
        });
       // DownJson downJson = new DownJson(22, 3, -1, -1, index, null, null, ActivityUtils.uerid, -1, -1);
        DownJson1 downJson = new DownJson1(22, 3, -1, -1, index, null, null, ActivityUtils.uerid, -1, -1,2);
        downJson.FreedomLoadTask(new DownJson.FreedomCallback() {
            @Override
            public void jsonLoaded(String[] json, String tag) {
                if (tag.equals("ready")) {
                    try {
                        index++;
                        Log.d("ShouCangFragment", json[0]);
                        JSONObject jsonObject = new JSONObject(json[0]);
                        JSONArray articlelist;
                        if (type == 3) {
                            articlelist = jsonObject.getJSONArray("collectlist");
                        } else {
                            articlelist = jsonObject.getJSONArray("articlelist");
                        }

                        if (articlelist.length() == 0) {
                            //Toast.makeText(getContext(), "没有记录!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        noshoucang.setVisibility(View.GONE);
                        pullToRefreshLayout.setVisibility(View.VISIBLE);
                        getListDatas = new ArrayList<>();
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
                                JSONArray contentPicturesArray = articlelist.getJSONObject(i)
                                        .getJSONArray("contentPictures");
                                if (contentPicturesArray.length() > 0) {
                                    ArrayList<String> contentPictures = new ArrayList<>();
                                    for (int j = 0; j < contentPicturesArray.length(); j++) {
                                        contentPictures.add(contentPicturesArray.getJSONObject(j).getString("url"));
                                        Log.d("ShouCangFragment", contentPicturesArray.getJSONObject(j).getString("url"));
                                    }
                                    paihangListData.setContentPictures(contentPictures);
                                }
                            } catch (Exception e) {
                            }
                            getListDatas.add(paihangListData);
                            listViewSet();
                        }
                        /*DownJson downJson1 = new DownJson(22, 3, -1, -1, 1, null, null, ActivityUtils.uerid, -1, -1);
                        Log.d("fdsfsdaaaaa", "ActivityUtils.uerid:" + ActivityUtils.uerid);
                        downJson1.FreedomLoadTask(new DownJson.FreedomCallback() {
                            @Override
                            public void jsonLoaded(String[] json, String tag) {
                                index = 2;
                                if (tag.equals("ready")) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(json[0]);
                                        JSONArray articlelist;
                                        if (type == 3) {
                                            articlelist = jsonObject.getJSONArray("collectlist");
                                        } else {
                                            articlelist = jsonObject.getJSONArray("articlelist");
                                        }
                                        if (articlelist.length() == 0) {
                                            listViewSet();
                                            return;
                                        }
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
                                            paihangListData.setCountcollect(
                                                    articlelist.getJSONObject(i).getInt("countcollect"));
                                            paihangListData
                                                    .setCountlike(articlelist.getJSONObject(i).getInt("countlike"));
                                            paihangListData
                                                    .setIs_collect(articlelist.getJSONObject(i).getInt("is_collect"));
                                            paihangListData.setIs_like(articlelist.getJSONObject(i).getInt("is_like"));
                                            try {
                                                JSONArray contentPicturesArray = articlelist.getJSONObject(i)
                                                        .getJSONArray("contentPictures");
                                                if (contentPicturesArray.length() > 0) {
                                                    ArrayList<String> contentPictures = new ArrayList<>();
                                                    for (int j = 0; j < contentPicturesArray.length(); j++) {
                                                        contentPictures.add(
                                                                contentPicturesArray.getJSONObject(i).getString("url"));
                                                    }
                                                    paihangListData.setContentPictures(contentPictures);
                                                }
                                            } catch (Exception e) {
                                            }
                                            getListDatas.add(paihangListData);
                                            listViewSet();
                                        }
                                    } catch (Exception e) {
                                    }
                                } else {
                                    listViewSet();
                                }
                            }
                        });
                        downJson1.execute(ActivityUtils.url);*/
                    } catch (Exception e) {
                        Log.d("ShouCangFragment", e.getMessage().toString());
                    }
                } else {
                    return;
                }
            }
        });
        downJson.execute(ActivityUtils.baikeribaourl);
    }

    private void listViewSet() {
        zuixinListviewAdapter = new ZuixinListviewAdapter(getListDatas,
                getContext());
        listView.setAdapter(zuixinListviewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                dianzan_counts = (TextView) arg0.findViewById(R.id.dianzan_counts);
                dianzan_image = (ImageView) arg0.findViewById(R.id.dianzan_image);
                click_xiaohua(arg2);
            }
        });
    }

    /**
     * 点击笑话
     */
    private void click_xiaohua(int position) {
        Intent intent = new Intent(getContext(), ContentActivity.class);
        ArrayList<Integer> article_id_list = new ArrayList<>();
        ArrayList<String> article_name_list = new ArrayList<>();
        ArrayList<String> content_list = new ArrayList<>();
        ArrayList<Integer> countcollect = new ArrayList<>();
        ArrayList<Integer> countlike = new ArrayList<>();
        ArrayList<Integer> is_collect = new ArrayList<>();
        ArrayList<Integer> is_like = new ArrayList<>();
        for (int i = 0; i < getListDatas.size(); i++) {
            article_id_list.add(getListDatas.get(i).getArticle_id());
            article_name_list.add(getListDatas.get(i).getArticle_name());
            content_list.add(getListDatas.get(i).getContent());
            countcollect.add(getListDatas.get(i).getCountcollect());
            countlike.add(getListDatas.get(i).getCountlike());
            is_collect.add(getListDatas.get(i).getIs_collect());
            is_like.add(getListDatas.get(i).getIs_like());
        }
        intent.putIntegerArrayListExtra("article_id_list", article_id_list);
        intent.putStringArrayListExtra("article_name_list", article_name_list);
        intent.putStringArrayListExtra("content_list", content_list);
        intent.putIntegerArrayListExtra("countcollect", countcollect);
        intent.putIntegerArrayListExtra("countlike", countlike);
        intent.putIntegerArrayListExtra("is_collect", is_collect);
        intent.putIntegerArrayListExtra("is_like", is_like);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    /**
     * 加载更多方法
     */
    private void load_more() {
        //DownJson downJson1 = new DownJson(22, 3, -1, -1, index, null, null, ActivityUtils.uerid, -1, -1);
        DownJson1 downJson1 = new DownJson1(22, 3, -1, -1, index, null, null, ActivityUtils.uerid, -1, -1,2);
        downJson1.FreedomLoadTask(new DownJson.FreedomCallback() {
            @Override
            public void jsonLoaded(String[] json, String tag) {
                index++;
                if (tag.equals("ready")) {
                    Log.d("fdsafaaaqqqq", json[0]);
                    final List<GetListData> paihangListDataTemp = new ArrayList<>();
                    if (getListDatas != null)
                        paihangListDataTemp.addAll(getListDatas);
                    try {
                        JSONObject jsonObject = new JSONObject(json[0]);
                        JSONArray articlelist;
                        if (type == 3) {
                            articlelist = jsonObject.getJSONArray("collectlist");
                        } else {
                            articlelist = jsonObject.getJSONArray("articlelist");
                        }
                        if (articlelist.length() == 0) {
                            pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                            Toast.makeText(getContext(), "已加载所有数据!", Toast.LENGTH_SHORT).show();
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
                                JSONArray contentPicturesArray = articlelist.getJSONObject(i)
                                        .getJSONArray("contentPictures");
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
                        getListDatas.clear();
                        getListDatas.addAll(paihangListDataTemp);
                        zuixinListviewAdapter.notifyDataSetChanged();

                    } catch (Exception e) {
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                        Toast.makeText(getContext(), "已加载所有数据!", Toast.LENGTH_SHORT).show();
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
}
