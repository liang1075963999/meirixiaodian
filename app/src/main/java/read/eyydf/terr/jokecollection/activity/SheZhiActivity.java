package read.eyydf.terr.jokecollection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;

/**
 * Created by liang on 2018/8/22.
 */
public class SheZhiActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private RelativeLayout xiugainicheng;
    private RelativeLayout xiugaiqianming;
    private RelativeLayout xiugaitouxiang;
    private RelativeLayout guanyuapp;
    private RelativeLayout yijianfankui;
    private RelativeLayout yonghuxieyi;

    @Override
    public void init() {
        ActivityUtils.initTranslucentStatus(this);
        back = findViewById(R.id.back);
        xiugainicheng = findViewById(R.id.xiugainicheng);
        xiugaiqianming = findViewById(R.id.xiugaiqianming);
        xiugaitouxiang = findViewById(R.id.xiugaitouxiang);
        guanyuapp = findViewById(R.id.guanyuapp);
        yijianfankui = findViewById(R.id.yijianfankui);
        yonghuxieyi = findViewById(R.id.yonghuxieyi);
        back.setOnClickListener(this);
        xiugainicheng.setOnClickListener(this);
        xiugaiqianming.setOnClickListener(this);
        xiugaitouxiang.setOnClickListener(this);
        guanyuapp.setOnClickListener(this);
        yijianfankui.setOnClickListener(this);
        yonghuxieyi.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);
        init();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.xiugainicheng:
                if (ActivityUtils.uerid != -1) {
                    intent = new Intent(SheZhiActivity.this, NiChengActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.xiugaiqianming:
                if (ActivityUtils.uerid != -1) {
                    intent = new Intent(SheZhiActivity.this, QianMingActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.xiugaitouxiang:
                if (ActivityUtils.uerid != -1) {
                    intent = new Intent(SheZhiActivity.this, RepalceBgActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.guanyuapp:
                intent = new Intent(SheZhiActivity.this, OwnGuanyuShowActivity.class);
                startActivity(intent);
                break;
            case R.id.yijianfankui:
                if (ActivityUtils.uerid != -1) {
                    intent = new Intent(SheZhiActivity.this, OwnHezuoShowActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.yonghuxieyi:
                intent = new Intent(SheZhiActivity.this, OwnXieyiShowActivity.class);
                startActivity(intent);
                break;
        }
    }
}
