package read.eyydf.terr.jokecollection.tools;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;

public class DownJson extends AsyncTask<String, Void, String[]> {
	static public interface FreedomCallback {
		public void jsonLoaded(String[] json, String tag);
	}

	private FreedomCallback jsonCallback;
	private int position = -1;
	private int type = -1;
	private int column = -1;
	private int articleId = -1;
	private int page = -1;
	private String user;
	private String pass;
	private int userId;
	private int collecttype;
	private int classId;

	public void FreedomLoadTask(FreedomCallback jsonCallback) {
		this.jsonCallback = jsonCallback;
	}

	/**
	 * 
	 * @param position 位置
	 * @param type 请求类型 (1,首页；2 栏目； 3文章（内容）列表；4 文章（内容）详情
	 * @param column 大的栏目 
	 * @param articleId 文章id
	 * @param page 分页
	 * @param user 用户名
	 * @param pass 密码
	 * @param userId 用户ID
	 * @param collecttype 1 收藏  2取消收藏
	 * @param clssId 分类
	 */
	public DownJson(int position, int type, int column, int articleId, int page, String user, String pass, int userId, int collecttype, int classId) {
		this.position = position;
		this.type = type;
		this.column = column;
		this.articleId = articleId;
		this.page = page;
		this.user = user;
		this.pass = pass;
		this.userId = userId;
		this.collecttype = collecttype;
		this.classId = classId;
	}

	public void stop() {
		if (isCancelled() && getStatus() == Status.RUNNING) {
			cancel(true);
		}
	}

	@Override
	protected String[] doInBackground(String... params) {
		if (isCancelled())
			return null;
		List<String> json = new ArrayList<>();
		for (int i = 0; i < params.length; i++) {
			try {
				URL url = new URL(params[i]);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
				conn.setReadTimeout(5000);
				conn.setConnectTimeout(5000);
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setRequestMethod("POST");
				conn.setUseCaches(false);
				conn.setInstanceFollowRedirects(true);
				conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
				//conn.setRequestProperty("Accept-Encoding", "gzip");
				conn.setRequestProperty("User-Agent", ActivityUtils.ua);
				conn.connect();
				DataOutputStream out = new DataOutputStream(conn.getOutputStream());
				out.write(POSTTools.getString(position, type, column, articleId, page, user, pass, userId, collecttype, classId));
				out.flush();
				out.close();
				if (conn.getResponseCode() == 200) {
					//String str = ZipUtils.uncompress(conn.getInputStream());
					json.add(read(conn.getInputStream()));
				}
			} catch (Exception e) {}
		}
		if (json.size() == 0) {
			return null;
		}
		return json.toArray(new String [json.size()]);
	}

	/**
	 * 流转为字符串
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	private static String read(InputStream is) throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while ((line = br.readLine()) != null) {
			line = new String(line.getBytes(), "utf-8");
			sb.append(line);
		}
		br.close();
		return sb.toString();
	}

	@Override
	protected void onPostExecute(String[] result) {
		if (result == null)
			jsonCallback.jsonLoaded(null, "fail");
		else
			jsonCallback.jsonLoaded(result, "ready");
	}
}
