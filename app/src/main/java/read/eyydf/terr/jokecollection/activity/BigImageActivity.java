package read.eyydf.terr.jokecollection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import read.eyydf.terr.jokecollection.views.PhotoView;

/**
 * Created by liang on 2018/8/10.
 */
public class BigImageActivity extends BaseActivity {
    private Intent intent;
    private PhotoView imageView;
    private String mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        mDatas = intent.getStringExtra("images");
        imageView = new PhotoView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        setContentView(imageView);
        RequestOptions requestOptions=RequestOptions.overrideOf(1000,2000).encodeQuality(100).sizeMultiplier(0.5f);
        Glide.with(this).load(mDatas).apply(requestOptions).into(imageView);
        imageView.enable();
    }

    @Override
    public void init() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
