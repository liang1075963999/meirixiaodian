package read.eyydf.terr.jokecollection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.tools.ActivityManagerUtil;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;
import read.eyydf.terr.jokecollection.tools.DataLocalSave;
import read.eyydf.terr.jokecollection.tools.DownJson.FreedomCallback;
import read.eyydf.terr.jokecollection.tools.DownJson1;
import read.eyydf.terr.jokecollection.tools.KeybordS;

/**
 * Created by fenghu on 2017/5/18.
 */
@ContentView(R.layout.own_login)
public class LoginActivity extends BaseActivity {
    @ViewInject(R.id.own_login_input_user)
    private EditText own_login_input_user;
    @ViewInject(R.id.own_login_input_password)
    private EditText own_login_input_password;
    @ViewInject(R.id.own_login_delete_user)
    private ImageView own_login_delete_user;
    @ViewInject(R.id.own_login_delete_password)
    private ImageView own_login_delete_password;
    @ViewInject(R.id.beijing)
    private ImageView beiJing;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        x.view().inject(this);
        ActivityManagerUtil.getAppManager().addActivity(this);
        intent = getIntent();
        if (intent != null) {
            own_login_input_user.setText(intent.getStringExtra("user"));
            own_login_input_password.setText(intent.getStringExtra("mima"));
        }
        /*Log.i("xinxi",ShareUtil.getInstance(this).getData("user"));
		if(!ShareUtil.getInstance(this).getData("user").equals("zanwu")){
			own_login_input_user.setText(ShareUtil.getInstance(this).getData("user"));
			own_login_input_password.setText(ShareUtil.getInstance(this).getData("mima"));
		}*/
        //监听输入用户名
        own_login_input_user.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    if (!s.toString().equals("")) {
                        own_login_delete_user.setVisibility(View.VISIBLE);
                    } else {
                        own_login_delete_user.setVisibility(View.GONE);
                    }
                } else {
                    own_login_delete_user.setVisibility(View.GONE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //监听输入密码
        own_login_input_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    if (!s.toString().equals("")) {
                        own_login_delete_password.setVisibility(View.VISIBLE);
                    } else {
                        own_login_delete_password.setVisibility(View.GONE);
                    }
                } else {
                    own_login_delete_password.setVisibility(View.GONE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        if (getApplication().getPackageName().equals("read.eyydf.jokecollection.episode"))
            beiJing.setImageResource(R.mipmap.baoxiaoduanzi);
        else if (getApplication().getPackageName().equals("read.terr.jokecollection.encyclopedia"))
            beiJing.setImageResource(R.mipmap.baoxiaobaike);
        else if (getApplication().getPackageName().equals("read.eyydf.terr.jokecollection"))
            beiJing.setImageResource(R.mipmap.ribaobeijing);
    }

    /**
     * 点击事件
     */
    @Event(value = {R.id.own_login_delete_user, R.id.own_login_delete_password, R.id.own_login_image, R.id.own_register_wenhao, R.id.own_wechat, R.id.own_wechat, R.id.login_exit}, type = View.OnClickListener.class)
    private void click(View v) {
        switch (v.getId()) {
            case R.id.own_login_delete_user:
                click_login_delete_user();
                break;
            case R.id.own_login_delete_password:
                click_login_delete_password();
                break;
            case R.id.own_login_image:
                click_login();
                break;
            case R.id.own_register_wenhao:
                click_register_wenhao();
                break;
            case R.id.own_wechat:
                click_wechat();
                break;
            case R.id.own_qq:
                click_qq();
                break;
            case R.id.login_exit:
                click_exit();
                break;
        }
    }

    /**
     * 点击用户输入删除按钮
     */
    private void click_login_delete_user() {
        own_login_input_user.getText().clear();
        own_login_delete_user.setVisibility(View.GONE);
    }

    /**
     * 点击密码输入删除按钮
     */
    private void click_login_delete_password() {
        own_login_input_password.getText().clear();
        own_login_delete_password.setVisibility(View.GONE);
    }

    /**
     * 点击登录
     */
    private void click_login() {
        final String userName = own_login_input_user.getText().toString();
        final String passName = own_login_input_password.getText().toString();
        if (userName.equals("")) {
            showToast("请输入账号");
            return;
        }
        if (passName.equals("")) {
            showToast("请输入密码");
            return;
        }
        if (userName.contains(" ") || passName.contains(" ")) {
            showToast("输入有空格，请重新输入!");
            return;
        }
        //DownJson downJson = new DownJson(20, -1, -1, -1, -1, userName, passName, -1, -1, -1);
        DownJson1 downJson = new DownJson1(20, 2, -1, -1, -1, userName, passName, -1, -1, -1,2);
        downJson.FreedomLoadTask(new FreedomCallback() {
            @Override
            public void jsonLoaded(String[] json, String tag) {
                try {
                    Log.d("hehehe", json[0]);
                    if (tag.equals("ready")) {
                        JSONObject jsonObject = new JSONObject(json[0]);
                        Log.d("hehehe", "ffffffffffffffffffff" + jsonObject.getInt("result"));
                        if (jsonObject.getInt("result") == 1) {
                            ActivityUtils.uerid = jsonObject.getJSONObject("user").getInt("user_id");
                            DataLocalSave.saveInt(LoginActivity.this, ActivityUtils.uerid, "userid");
                            DataLocalSave.saveString(LoginActivity.this, userName, "zhanghao");
                            DataLocalSave.saveString(LoginActivity.this,passName, "mima");
                            showToast("登录成功!");
                            Intent login = new Intent("MainOwnFragment");
                            login.putExtra("login", true);
                            LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(login);
                            click_exit();
                            return;
                        }
                        showToast("登录失败,账户或密码错误!");
                    } else {
                        showToast("登录失败!");
                    }
                } catch (Exception e) {
                    Log.d("LoginActivity", e.getMessage().toString());
                    showToast("登录失败!");
                }
            }
        });
        downJson.execute(ActivityUtils.baikeribaourl);
    }

    /**
     * 点击注册
     */
    private void click_register_wenhao() {
        startActivitys(RegisterActivity.class);
    }

    /**
     * 点击微信
     */
    private void click_wechat() {

    }

    /**
     * 点击qq
     */
    private void click_qq() {

    }

    /**
     * 点击关闭
     */
    private void click_exit() {
        if (KeybordS.isSoftInputShow(this)) {
            KeybordS.closeKeybord(own_login_input_user, this);
            KeybordS.closeKeybord(own_login_input_password, this);
        }
        finish();
    }
}
