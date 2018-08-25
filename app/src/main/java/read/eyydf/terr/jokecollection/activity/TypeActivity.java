package read.eyydf.terr.jokecollection.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
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
import read.eyydf.terr.jokecollection.tools.DownJson.FreedomCallback;

@ContentView(R.layout.type_activity_layout)
public class TypeActivity extends BaseActivity {
	@ViewInject(R.id.pullToRefreshLayout)
	private PullToRefreshLayout pullToRefreshLayout;
	@ViewInject(R.id.type_listview)
	private PullableListView listView;
	@ViewInject(R.id.text_title)
	private TextView text_title;
	private List<GetListData> getListDatas;
	private ZuixinListviewAdapter zuixinListviewAdapter;
	private int index;
	private int position;
	private int classId = -1;
	private int type = 2;
	private TextView dianzan_counts;
	private ImageView dianzan_image;
	private BroadcastReceiver br;
	
	@Override
	public void init() {
		x.view().inject(this);
		text_title.setText(getIntent().getStringExtra("title"));
		position = getIntent().getIntExtra("position", 0);
		if (position == 0) {
			showToast("加载错误!");
			finish();
			return;
		}
		else if (position == 11) {
			classId = 1;
		}
		else if (position == 12) {
			classId = 2;
		}
		else if (position == 13) {
			classId = 3;
		}
		else if (position == 14) {
			classId = 4;
		}
		else if (position == 15) {
			classId = 5;
		}
		else if (position == 16) {
			classId = 6;
		}
		else if (position == 22) {
			classId = -1;
			type = 3;
		}
		else if (position == 23) {
			classId = -1;
			type = 3;
		} else {
			showToast("没有记录!");
			finish();
			return;
		}
		
		// 广播
		LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("FirstHomezuixinFragment");
		br = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				final boolean isDianzan = intent.getBooleanExtra("isDianzan", false);
				final int count = intent.getIntExtra("count", 0);
				if (isDianzan) {
					runOnUiThread(new Runnable() {
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
		
		pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
			}

			@Override
			public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
				load_more();
			}
		});
		DownJson downJson = new DownJson(position, type, -1, -1, 0, null, null, ActivityUtils.uerid, -1, classId);
		downJson.FreedomLoadTask(new FreedomCallback() {
			@Override
			public void jsonLoaded(String[] json, String tag) {
				if (tag.equals("ready")) {
					try {
						JSONObject jsonObject = new JSONObject(json[0]);
						JSONArray articlelist;
						if (type == 3) {
							articlelist = jsonObject.getJSONArray("collectlist");
						}else {
							articlelist = jsonObject.getJSONArray("articlelist");
						}

						if (articlelist.length() == 0) {
							showToast("没有记录!");
							finish();
							return;
						}
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
										contentPictures.add(contentPicturesArray.getJSONObject(i).getString("url"));
									}
									paihangListData.setContentPictures(contentPictures);
								}
							} catch (Exception e) {}
							getListDatas.add(paihangListData);
						}
						DownJson downJson1 = new DownJson(position, type, -1, -1, 1, null, null, ActivityUtils.uerid, -1, classId);
						downJson1.FreedomLoadTask(new FreedomCallback() {
							@Override
							public void jsonLoaded(String[] json, String tag) {
								index = 2;
								if (tag.equals("ready")) {
									try {
										JSONObject jsonObject = new JSONObject(json[0]);
										JSONArray articlelist;
										if (type == 3) {
											articlelist = jsonObject.getJSONArray("collectlist");
										}else {
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
											} catch (Exception e) {}
											getListDatas.add(paihangListData);
											listViewSet();
										}
									} catch (Exception e) {}
								} else {
									listViewSet();
								}
							}
						});
						downJson1.execute(ActivityUtils.url);
					} catch (Exception e) {
					}
				}else {
					showToast("没有记录!");
					finish();
					return;
				}
			}
		});
		downJson.execute(ActivityUtils.url);
	}

	/**
	 * 设置listView
	 */
	private void listViewSet() {
		zuixinListviewAdapter = new ZuixinListviewAdapter(getListDatas,
				TypeActivity.this);
		listView.setAdapter(zuixinListviewAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
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
		Intent intent = new Intent(TypeActivity.this, ContentActivity.class);
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
		DownJson downJson1 = new DownJson(position, type, -1, -1, index, null, null, ActivityUtils.uerid, -1, classId);
		downJson1.FreedomLoadTask(new FreedomCallback() {
			@Override
			public void jsonLoaded(String[] json, String tag) {
				index++;
				if (tag.equals("ready")) {
					final List<GetListData> paihangListDataTemp = new ArrayList<>();
					paihangListDataTemp.addAll(getListDatas);
					try {
						JSONObject jsonObject = new JSONObject(json[0]);
						JSONArray articlelist;
						if (type == 3) {
							articlelist = jsonObject.getJSONArray("collectlist");
						}else {
							articlelist = jsonObject.getJSONArray("articlelist");
						}
						if (articlelist.length() == 0) {
							pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
							showToast("已加载所有数据!");
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
										contentPictures.add(contentPicturesArray.getJSONObject(i).getString("url"));
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
						showToast("已加载所有数据!");
						return;
					}
					pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
				} else {
					pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
				}
			}
		});
		downJson1.execute(ActivityUtils.url);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	@Event(value = { R.id.image_back }, type = View.OnClickListener.class)
	private void click(View v) {
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		LocalBroadcastManager.getInstance(this).unregisterReceiver(br);
	}
}
