package read.eyydf.terr.jokecollection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import read.eyydf.terr.jokecollection.tools.DownJson.FreedomCallback;
import read.eyydf.terr.jokecollection.tools.DownJson1;
import read.eyydf.terr.jokecollection.tools.KeybordS;

/**
 * Created by fenghu on 2017/5/18.
 */
@ContentView(R.layout.own_register)
public class RegisterActivity extends BaseActivity {
    @ViewInject(R.id.own_register_input_user)
    private EditText own_register_input_user;
    @ViewInject(R.id.own_register_input_password)
    private EditText own_register_input_password;
    @ViewInject(R.id.own_register_delete_user)
    private ImageView own_register_delete_user;
    @ViewInject(R.id.own_register_delete_password)
    private ImageView own_register_delete_password;
    @ViewInject(R.id.beijing)
    private ImageView beiJing;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        x.view().inject(this);
        ActivityManagerUtil.getAppManager().addActivity(this);
        //监听输入用户名
        own_register_input_user.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    if (!s.toString().equals("")) {
                        own_register_delete_user.setVisibility(View.VISIBLE);
                    } else {
                        own_register_delete_user.setVisibility(View.GONE);
                    }
                } else {
                    own_register_delete_user.setVisibility(View.GONE);
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
        own_register_input_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    if (!s.toString().equals("")) {
                        own_register_delete_password.setVisibility(View.VISIBLE);
                    } else {
                        own_register_delete_password.setVisibility(View.GONE);
                    }
                } else {
                    own_register_delete_password.setVisibility(View.GONE);
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
    @Event(value = {R.id.own_register_delete_user, R.id.own_register_delete_password, R.id.own_register_image, R.id.register_exit}, type = View.OnClickListener.class)
    private void click(View v) {
        switch (v.getId()) {
            case R.id.own_register_delete_user:
                click_register_delete_user();
                break;
            case R.id.own_register_delete_password:
                click_register_delete_password();
                break;
            case R.id.own_register_image:
                click_register();
                break;
            case R.id.register_exit:
                click_exit();
                break;
        }
    }

    /**
     * 点击用户输入删除按钮
     */
    private void click_register_delete_user() {
        own_register_input_user.getText().clear();
        own_register_delete_user.setVisibility(View.GONE);
    }

    /**
     * 点击密码输入删除按钮
     */
    private void click_register_delete_password() {
        own_register_input_password.getText().clear();
        own_register_delete_password.setVisibility(View.GONE);
    }

    /**
     * 点击注册
     */
    private void click_register() {
        String userName = own_register_input_user.getText().toString();
        String passName = own_register_input_password.getText().toString();
        if (userName.equals("")) {
            showToast("请输入账号");
            return;
        }
        if (passName.equals("")) {
            showToast("请输入密码");
            return;
        }
        if (userName.length()<6) {
            showToast("账号至少为6位");
            return;
        }
        if (passName.length()<6) {
            showToast("密码至少为6位");
            return;
        }
        if (userName.contains(" ") || passName.contains(" ")) {
            showToast("输入有空格，请重新输入!");
            return;
        }
        //DownJson downJson = new DownJson(21, -1, -1, -1, -1, userName, passName, -1, -1, -1);
        DownJson1 downJson = new DownJson1(21, -1, -1, -1, -1, userName, passName, -1, -1, -1,2);
        downJson.FreedomLoadTask(new FreedomCallback() {
            @Override
            public void jsonLoaded(String[] json, String tag) {
                try {
                    if (tag.equals("ready")) {
                        if (new JSONObject(json[0]).getInt("result") == 1) {
                            showToast("注册成功!");
                            Log.d("RegisterActivity", own_register_input_user.getText().toString());
                            Log.d("RegisterActivity", own_register_input_password.getText().toString());
                           /* ShareUtil.getInstance(RegisterActivity.this).addData("user",own_register_input_user.getText().toString());
                            ShareUtil.getInstance(RegisterActivity.this).addData("mima",own_register_input_password.getText().toString());*/
                            click_exit();
                            return;
                        }
                        if (new JSONObject(json[0]).getInt("result") == 2) {
                            showToast("用户已存在，请重新输入!");
                            click_register_delete_user();
                            click_register_delete_password();
                            return;
                        }
                        showToast("注册失败!");
                    } else {
                        showToast("注册失败!");
                    }
                } catch (Exception e) {
                    showToast("注册失败!");
                }
            }
        });
        downJson.execute(ActivityUtils.baikeribaourl);
    }

    /**
     * 点击关闭
     */
    private void click_exit() {
        if (KeybordS.isSoftInputShow(this)) {
            KeybordS.closeKeybord(own_register_input_user, this);
            KeybordS.closeKeybord(own_register_input_password, this);
        }
        ActivityManagerUtil.getAppManager().finishAllActivity();
        Intent intent=new Intent(this,LoginActivity.class);
        intent.putExtra("user",own_register_input_user.getText().toString());
        intent.putExtra("mima",own_register_input_password.getText().toString());
        startActivity(intent);
        finish();
    }
}
