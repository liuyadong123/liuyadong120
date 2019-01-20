package com.example.dong.yuekao.presenter;

import com.example.dong.yuekao.bean.ProductBean;
import com.example.dong.yuekao.contract.ProductContract;
import com.example.dong.yuekao.modle.ProductModle;
import com.example.dong.yuekao.modle.RequestCallback;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class ProductPresenter extends ProductContract.ProductPresenter {
    private ProductModle modle;
    private ProductContract.IProductView view;
    public ProductPresenter(ProductContract.IProductView view){
        modle=new ProductModle();
        this.view=view;
    }
    @Override
    public void product(HashMap<String, String> params) {
         modle.product(params, new RequestCallback() {
             @Override
             public void success(String result) {
                 ProductBean productBean = new Gson().fromJson(result, ProductBean.class);
                 List<ProductBean.DataBean> data = productBean.data;

                 view.success(data);
             }

             @Override
             public void failure(String msg) {
                 view.Failure(msg);
             }
         });
    }
}
