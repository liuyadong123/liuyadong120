package com.example.dong.yuekao.net;

import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttpUtils {
    private OkHttpClient okHttpClient;
    private  static  OkhttpUtils okhttpUtils;
    private Handler handler =new Handler();
    private  OkhttpUtils(){
        HttpLoggingInterceptor httpLoggingInterceptor =new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
          }
          public static OkhttpUtils getOkhttpUtils(){
            if (okhttpUtils==null){
                synchronized (OkhttpUtils.class){
                    if (okhttpUtils==null){
                        okhttpUtils=new OkhttpUtils();
                    }
                }
            }
            return  okhttpUtils;
          }
          public void doPost(String url, HashMap<String,String> params, final OkhttpCallback callback){
              FormBody.Builder builder =new FormBody.Builder();
              for (Map.Entry<String, String> P : params.entrySet()) {
                   builder.add(P.getKey(), P.getValue());
              }
              Request request =new Request.Builder().url(url).post(builder.build()).build();
              okHttpClient.newCall(request).enqueue(new Callback() {
                  @Override
                  public void onFailure(Call call, IOException e) {
                      handler.post(new Runnable() {
                          @Override
                          public void run() {
                              callback.Failure("请求失败");
                          }
                      });
                  }

                  @Override
                  public void onResponse(final Call call, Response response) throws IOException {
                      final String result = response.body().string();
                      if (200 == response.code()) {

                          handler.post(new Runnable() {
                              @Override
                              public void run() {
                                 callback.Success(result);
                              }
                          });
                      }
                  }
              });
          }
          public void connTask(){
        if (okHttpClient!=null){
            okHttpClient.dispatcher().cancelAll();
         }
          }


}
