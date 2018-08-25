package read.eyydf.terr.jokecollection.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class DownImage extends AsyncTask<String, Void, Bitmap[]> {
	static public interface FreedomCallback {
		public void imageLoaded(Bitmap[] imageDrawable, String tag);
	}

	private FreedomCallback imageCallback;
	private int timeOut = -1;

	public void FreedomLoadTask(FreedomCallback imageCallback) {
		this.imageCallback = imageCallback;
	}

	public DownImage(int timeOut) {
		this.timeOut = timeOut;
	}

	public void stop() {
		if (isCancelled() && getStatus() == Status.RUNNING) {
			cancel(true);
		}
	}

	@Override
	protected Bitmap[] doInBackground(String... params) {
		if (isCancelled())
			return null;
		if (params == null) {
			return null;
		}
		if (params.length == 0) {
			return null;
		}
		Bitmap[] bitmaps = new Bitmap[params.length];
		try {
			// 2017.1.23 加入bitmaps[X]非空判断
			boolean isEmpty = false;
			for (int i = 0; i <= params.length - 1; i++) {
				if (params[i] == null) {
					continue;
				}
				URL url = new URL(params[i]);
				URLConnection urlC = url.openConnection();
				if (timeOut == -1) {
				} else {
					urlC.setReadTimeout(timeOut);
					urlC.setConnectTimeout(timeOut);
				}
				InputStream is = urlC.getInputStream();

				byte[] bytes = toByteArray(is);
				bitmaps[i] = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
				// 2017.1.23 修改 防止bitmaps[i]为空
				// bitmaps[i] = BitmapFactory.decodeStream(is);
				if (bitmaps[i] == null) {
					isEmpty = true;
				}
				is.close();
				bytes = null;
			}
			if (isEmpty) {
				bitmaps = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 2017.11.28修改 防止为null
			// bitmaps = null;
		}
		return bitmaps;
	}

	@Override
	protected void onPostExecute(Bitmap[] result) {
		if (result == null)
			imageCallback.imageLoaded(null, "fail");
		else
			imageCallback.imageLoaded(result, "ready");
	}

	public byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}
}
