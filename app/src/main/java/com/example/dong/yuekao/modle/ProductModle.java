package com.example.dong.yuekao.modle;

import com.example.dong.yuekao.api.ProductApi;
import com.example.dong.yuekao.contract.ProductContract;
import com.example.dong.yuekao.net.OkhttpCallback;
import com.example.dong.yuekao.net.OkhttpUtils;

import java.util.HashMap;

public class ProductModle implements ProductContract.IProductModle {
    @Override
    public void product(HashMap<String, String> params, final RequestCallback callback) {
        OkhttpUtils.getOkhttpUtils().doPost(ProductApi.Product_user, params, new OkhttpCallback() {
            @Override
            public void Success(String result) {
                callback.success(result);
            }

            @Override
            public void Failure(String msg) {
              callback.failure(msg);
            }
        });
    }
}
