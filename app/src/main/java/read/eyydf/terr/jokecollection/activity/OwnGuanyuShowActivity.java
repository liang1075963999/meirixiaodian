package read.eyydf.terr.jokecollection.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;
import read.eyydf.terr.jokecollection.views.zitiTextView;

/**
 * Created by fenghu on 2017/5/18.
 */
@ContentView(R.layout.own_guanyu_show_activity)
public class OwnGuanyuShowActivity extends BaseActivity {
    private Timer timer;
    private ImageView logo;
    private zitiTextView zitiTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Event(value = {R.id.image_back, R.id.check}, type = View.OnClickListener.class)
    private void click(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.check:
                timer = new Timer(true);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                showToast("当前已是最新版本!");
                            }
                        });
                    }
                }, 1000);
                break;
        }
    }

    public void init() {
        x.view().inject(this);
        ActivityUtils.initTranslucentStatus(this);
        ImageView back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        logo = findViewById(R.id.logo);
        zitiTextView = findViewById(R.id.appBanBen);
        if (getApplication().getPackageName().equals("read.eyydf.jokecollection.episode"))
            logo.setImageResource(R.mipmap.logo);
        else if (getApplication().getPackageName().equals("read.terr.jokecollection.encyclopedia"))
            logo.setImageResource(R.mipmap.baikeicon);
        else if (getApplication().getPackageName().equals("read.eyydf.terr.jokecollection"))
            logo.setImageResource(R.mipmap.baoxiaoribaologo);
        if (getApplication().getPackageName().equals("read.eyydf.terr.jokecollection"))
            zitiTextView.setText("爆笑日报" + ActivityUtils.getAppVersion(this) + "版");
        if (getApplication().getPackageName().equals("read.terr.jokecollection.encyclopedia"))
            zitiTextView.setText("爆笑百科" + ActivityUtils.getAppVersion(this) + "版");
        if (getApplication().getPackageName().equals("read.eyydf.jokecollection.episode"))
            zitiTextView.setText("每日笑点" + ActivityUtils.getAppVersion(this) + "版");
    }
}
