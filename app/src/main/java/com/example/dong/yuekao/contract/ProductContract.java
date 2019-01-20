package com.example.dong.yuekao.contract;

import com.example.dong.yuekao.bean.ProductBean;
import com.example.dong.yuekao.modle.RequestCallback;

import java.util.HashMap;
import java.util.List;

public interface ProductContract {
    abstract  class  ProductPresenter{
        public  abstract  void product(HashMap<String,String> params);
    }
    interface  IProductModle{
        void product(HashMap<String,String> params,RequestCallback callback);
    }
    interface  IProductView{
        void success(List<ProductBean.DataBean> list);
        void Failure(String msg);
    }
}
