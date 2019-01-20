package com.example.dong.yuekao.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dong.yuekao.MainActivity;
import com.example.dong.yuekao.R;

public class ResigActivity extends AppCompatActivity {
      private EditText ed_name,ed_pwd,ed_quepwd;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resig);
        ed_name=findViewById(R.id.ed_name);
        ed_pwd=findViewById(R.id.ed_name);
        sharedPreferences=getSharedPreferences("test",MODE_PRIVATE);
        edit = sharedPreferences.edit();
        String name = ed_name.getText().toString();
        String pwd = ed_pwd.getText().toString();
        edit.putString("name",name);
        edit.putString("pwd",pwd);
        edit.commit();


    }
    public void zhu(View view) {
        Intent intent =new Intent(ResigActivity.this,MainActivity.class);
        String name = sharedPreferences.getString("name", null);
        String pwd = sharedPreferences.getString("pwd", null);
        intent.putExtra("name",name);
        intent.putExtra("pwd",pwd);
         startActivity(intent);

    }
}
