package read.eyydf.terr.jokecollection.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.activity.ContentActivity;
import read.eyydf.terr.jokecollection.adpater.PaihangListviewAdapter;
import read.eyydf.terr.jokecollection.model.GetListData;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;
import read.eyydf.terr.jokecollection.tools.DownJson.FreedomCallback;
import read.eyydf.terr.jokecollection.tools.DownJson1;

/**
 * Created by fenghu on 2017/5/15.
 */
@SuppressLint("ValidFragment")
@ContentView(R.layout.viewpagerfirsthomepaihangfragment)
public class FirstHomepaihangFragment extends Fragment {
	@ViewInject(R.id.paihang_listview)
	private ListView listView;
	private List<GetListData> paihangListDatas;
	private PaihangListviewAdapter paihangListviewAdapter;

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

	private void init() {
		//DownJson downJson = new DownJson(4, 3, -1, -1, 0, null, null, -1, -1, -1);
		DownJson1 downJson = new DownJson1(3, 4, -1, -1, 0, null, null, -1, -1, -1,2);
		downJson.FreedomLoadTask(new FreedomCallback() {
			@Override
			public void jsonLoaded(String[] json, String tag) {
				if (tag.equals("ready")) {
					try {
						JSONObject jsonObject = new JSONObject(json[0]);
						JSONArray articlelist = jsonObject.getJSONArray("articlelist");
						paihangListDatas = new ArrayList<>();
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
										Log.d("FirstHomepaihangFragmen", contentPicturesArray.get(j).toString());
										contentPictures.add(contentPicturesArray.getJSONObject(j).getString("url"));
									}
									paihangListData.setContentPictures(contentPictures);
								}
							} catch (Exception e) {}
							paihangListDatas.add(paihangListData);
						}
						if (getActivity() != null) {
							if (paihangListDatas == null) {
								return;
							}
							listViewSet();
						}
					} catch (Exception e) {
					}
				}
			}
		});
		downJson.execute(ActivityUtils.baikeribaourl);
	}
	/**
	 * 设置listView
	 */
	private void listViewSet() {
		paihangListviewAdapter = new PaihangListviewAdapter(paihangListDatas,
				getActivity());
		listView.setAdapter(paihangListviewAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				click_xiaohua(arg2);
			}
		});
	}

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
			for (int i = 0; i < paihangListDatas.size(); i++) {
				article_id_list.add(paihangListDatas.get(i).getArticle_id());
				article_name_list.add(paihangListDatas.get(i).getArticle_name());
				content_list.add(paihangListDatas.get(i).getContent());
				countcollect.add(paihangListDatas.get(i).getCountcollect());
				countlike.add(paihangListDatas.get(i).getCountlike());
				is_collect.add(paihangListDatas.get(i).getIs_collect());
				is_like.add(paihangListDatas.get(i).getIs_like());
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
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
