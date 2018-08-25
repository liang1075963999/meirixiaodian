package read.eyydf.terr.jokecollection.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;
import read.eyydf.terr.jokecollection.tools.DataLocalSave;
import read.eyydf.terr.jokecollection.tools.FastBlur;
import read.eyydf.terr.jokecollection.views.CircleImageView;
import read.eyydf.terr.jokecollection.views.UserDefineScrollView;

/**
 * Created by fenghu on 2017/5/18.
 */
@ContentView(R.layout.repalce_bg_activity_layout)
public class RepalceBgActivity extends BaseActivity {
	@ViewInject(R.id.own_user_image)
	public CircleImageView own_user_image;
	@ViewInject(R.id.own_user_image_bg)
	public UserDefineScrollView own_user_image_bg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	@Event(value = { R.id.image_back, R.id.repalce_bg, R.id.tuichu_login }, type = View.OnClickListener.class)
	private void click(View v) {
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.repalce_bg:
			Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(intent, 0001);
			break;
		case R.id.tuichu_login:
			ActivityUtils.uerid=-1;
			DataLocalSave.saveInt(RepalceBgActivity.this, -1, "userid");
			Intent login = new Intent("MainOwnFragment");
			login.putExtra("login", false);
			LocalBroadcastManager.getInstance(RepalceBgActivity.this).sendBroadcast(login);
			showToast("退出登陆成功!");
			finish();
			break;
		}
	}

	public void init() {
		x.view().inject(this);
		ImageView back=findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		ActivityUtils.initTranslucentStatus(this);
		ActivityUtils.uerid = DataLocalSave.getInt(this, "userid");
		isLogin();
	}
	
	/**
	 * 登录状态
	 */
	@SuppressLint("NewApi")
	private void isLogin() {
		if (ActivityUtils.uerid != -1) {
			String path = getFilesDir() + "/" + ActivityUtils.uerid + "_own_bg.png";
			if (new File(path).exists()) {
				Bitmap bitmap = BitmapFactory.decodeFile(path);
				own_user_image.setImageBitmap(bitmap);
				Bitmap blurBitmap = FastBlur.toBlur(bitmap, 2);
				//own_user_image_bg.setBackground(new BitmapDrawable(getResources(), blurBitmap));
			} else {
				Drawable drawable = ContextCompat.getDrawable(this, R.drawable.own_bg_xuhua);
				BitmapDrawable bd = (BitmapDrawable) drawable;
				Bitmap bitmap = bd.getBitmap();
				Bitmap blurBitmap = FastBlur.toBlur(bitmap, 2);
				//own_user_image_bg.setBackground(new BitmapDrawable(getResources(), blurBitmap));
			}
		}
	}
	@SuppressLint("NewApi")
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0001 && resultCode == Activity.RESULT_OK && data != null) {
			Uri mImageCaptureUri = data.getData();
			Bitmap bitmap = null;
			if (mImageCaptureUri != null) {
				try {
					bitmap = getBitmapFormUri(this, mImageCaptureUri);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			int degrees = getOrientation(this, mImageCaptureUri);
			bitmap = rotateImage(bitmap, degrees);
			ActivityUtils.saveBitmap(this.getFilesDir() + "/" + ActivityUtils.uerid + "_own_bg.png", bitmap);
			if (bitmap != null) {
				own_user_image.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
				Bitmap blurBitmap = FastBlur.toBlur(bitmap, 2);
				//own_user_image_bg.setBackground(new BitmapDrawable(getResources(), blurBitmap));
				Intent login = new Intent("MainOwnFragment");
				login.putExtra("login", true);
				LocalBroadcastManager.getInstance(RepalceBgActivity.this).sendBroadcast(login);
			}
		}
	}

	/**
	 * 通过uri获取图片并进行压缩
	 *
	 * @param uri
	 */
	public static Bitmap getBitmapFormUri(Activity ac, Uri uri) throws FileNotFoundException, IOException {
		InputStream input = ac.getContentResolver().openInputStream(uri);
		BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
		onlyBoundsOptions.inJustDecodeBounds = true;
		onlyBoundsOptions.inDither = true;// optional
		onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
		BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
		input.close();
		int originalWidth = onlyBoundsOptions.outWidth;
		int originalHeight = onlyBoundsOptions.outHeight;
		if ((originalWidth == -1) || (originalHeight == -1))
			return null;
		// 图片分辨率以480x800为标准
		float hh = 800f;// 这里设置高度为800f
		float ww = 480f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (originalWidth > originalHeight && originalWidth > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (originalWidth / ww);
		} else if (originalWidth < originalHeight && originalHeight > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (originalHeight / hh);
		}
		if (be <= 0)
			be = 1;
		// 比例压缩
		BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
		bitmapOptions.inSampleSize = be;// 设置缩放比例
		bitmapOptions.inDither = true;// optional
		bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
		input = ac.getContentResolver().openInputStream(uri);
		Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
		input.close();

		return compressImage(bitmap);// 再进行质量压缩
	}

	/**
	 * 质量压缩方法
	 *
	 * @param image
	 * @return
	 */
	public static Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			// 第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差 ，第三个参数：保存压缩后的数据的流
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	public int getOrientation(Context context, Uri photoUri) {
		int orientation = 0;
		Cursor cursor = context.getContentResolver().query(photoUri,
				new String[] { MediaStore.Images.ImageColumns.ORIENTATION }, null, null, null);
		if (cursor != null) {
			if (cursor.getCount() != 1) {
				return -1;
			}
			cursor.moveToFirst();
			orientation = cursor.getInt(0);
			cursor.close();
		}
		return orientation;
	}

	public Bitmap rotateImage(Bitmap bmp, int degrees) {
		if (degrees != 0) {
			Matrix matrix = new Matrix();
			matrix.postRotate(degrees);
			return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
		}
		return bmp;
	}
}
