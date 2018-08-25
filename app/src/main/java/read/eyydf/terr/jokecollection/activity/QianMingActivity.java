package read.eyydf.terr.jokecollection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;
import read.eyydf.terr.jokecollection.tools.DataLocalSave;
import read.eyydf.terr.jokecollection.tools.DownJson;
import read.eyydf.terr.jokecollection.tools.DownJson1;

/**
 * Created by liang on 2018/8/23.
 */

public class QianMingActivity extends BaseActivity {
    private EditText editText;
    private Button tijiao;
    @Override
    public void init() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qianming);
        ActivityUtils.initTranslucentStatus(this);
        editText=findViewById(R.id.nicheng);
        tijiao=findViewById(R.id.tijiao);
        ImageView back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText.getText().toString().equals("")){
                    DownJson1 downJson = new DownJson1(20, 3, -1, -1, -1, DataLocalSave.getString(QianMingActivity.this, "zhanghao"), DataLocalSave.getString(QianMingActivity.this, "mima"), ActivityUtils.uerid, -1, -1, 2,editText.getText().toString(),1);
                    downJson.FreedomLoadTask(new DownJson.FreedomCallback() {
                        @Override
                        public void jsonLoaded(String[] json, String tag) {
                            try {
                                Log.d("hahaha", json[0]);
                                if (tag.equals("ready")) {
                                    JSONObject jsonObject = new JSONObject(json[0]);
                                    if (jsonObject.getString("message").equals("修改成功")) {
                                 /*   wenzi.setText(jsonObject.getJSONObject("user").getString("nickname"));
                                    qianming.setText(jsonObject.getJSONObject("user").getString("signature"));*/
                                        Toast.makeText(QianMingActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                        Intent login = new Intent("MainOwnFragment");
                                        login.putExtra("login", true);
                                        LocalBroadcastManager.getInstance(QianMingActivity.this).sendBroadcast(login);
                                        finish();
                                    }
                                    // showToast("登录失败,账户或密码错误!");
                                } else {
                                    Toast.makeText(QianMingActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                                    // showToast("登录失败!");
                                }
                            } catch (Exception e) {
                                Log.d("LoginActivity", e.getMessage().toString());
                                // showToast("登录失败!");
                            }
                        }
                    });
                    downJson.execute(ActivityUtils.baikeribaourl);
                }
                else {
                    Toast.makeText(QianMingActivity.this, "签名不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
