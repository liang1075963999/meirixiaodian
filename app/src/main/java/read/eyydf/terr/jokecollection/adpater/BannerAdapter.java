package read.eyydf.terr.jokecollection.adpater;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.activity.MyApplication;

/**
 * Created by liang on 2018/8/1.
 */

public class BannerAdapter extends PagerAdapter {
    private List<String> imgUrlList;

    public BannerAdapter(List<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }

    @Override
    public int getCount() {
        return imgUrlList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(MyApplication.getMyApplicationContext());
        imageView.setImageResource(R.mipmap.ic_launcher);
        if(position==1){
            imageView.setImageResource(R.mipmap.logo);
        }
        container.addView(imageView);
        return container.getChildAt(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
