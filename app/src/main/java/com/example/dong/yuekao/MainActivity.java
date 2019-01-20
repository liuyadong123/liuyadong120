package com.example.dong.yuekao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.dong.yuekao.activity.ResigActivity;
import com.example.dong.yuekao.activity.ShopActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
   private EditText ed_name,ed_pwd;
   private CheckBox jizhu;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private String name;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        ed_name=findViewById(R.id.ed_name);
        ed_pwd=findViewById(R.id.ed_pwd);
        jizhu=findViewById(R.id.jizhu);
        sharedPreferences=getSharedPreferences("test",MODE_PRIVATE);
        edit = sharedPreferences.edit();
        boolean jizhus = sharedPreferences.getBoolean("jizhu", false);
        if (jizhus){
            String name = sharedPreferences.getString("name", null);
            String pwd = sharedPreferences.getString("pwd", null);
            ed_pwd.setText(pwd);
            ed_name.setText(name);
            jizhu.setChecked(true);
        }


    }

    private void initData() {
    }

    //登陆
    public void deng(View view) {
        name = ed_name.getText().toString();
        pwd = ed_pwd.getText().toString();
        if (jizhu.isChecked()){
            edit.putString("name", name);
            edit.putString("pwd", pwd);
            edit.putBoolean("jizhu",true);
            edit.commit();
        }
        Intent intent =new Intent(MainActivity.this,ShopActivity.class);
        startActivity(intent);


    }
    //注册
    public void zhuce(View view) {

        Intent intent =new Intent(MainActivity.this,ResigActivity.class);
        startActivity(intent);
    }
     //第三方登陆
    public void san(View view) {
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(MainActivity.this).setShareConfig(config);
        UMShareAPI.get(this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Intent intent =new Intent(MainActivity.this,ShopActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


}
