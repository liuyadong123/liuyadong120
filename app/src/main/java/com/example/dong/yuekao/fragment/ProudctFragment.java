package com.example.dong.yuekao.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.dong.yuekao.R;
import com.example.dong.yuekao.adapter.ShopAdapter;
import com.example.dong.yuekao.bean.ProductBean;
import com.example.dong.yuekao.callback.ProductCallback;
import com.example.dong.yuekao.contract.ProductContract;
import com.example.dong.yuekao.net.OkhttpUtils;
import com.example.dong.yuekao.presenter.ProductPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProudctFragment extends Fragment implements ProductContract.IProductView,ProductCallback {
    private ProductPresenter presenter;
    private ShopAdapter shopAdapter;
    private RecyclerView rv;
    private CheckBox checkBox;
    private  List<ProductBean.DataBean> data;
    private TextView tv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.product_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        initData();
    }

    private void initData() {
        presenter=new ProductPresenter(this);
        HashMap<String,String> params =new HashMap<>();
        params.put("uid","71");
        presenter.product(params);
    }

    private void initView(View view) {
        rv=view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        checkBox=view.findViewById(R.id.checkbox);
        tv=view.findViewById(R.id.tv);
        data=new ArrayList<>();
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    for (ProductBean.DataBean datum : data) {
                         datum.Checked=true;
                        for (ProductBean.DataBean.Product product : datum.list) {
                            product.ProductChecked=true;
                        }
                    }
                }else {
                    for (ProductBean.DataBean datum : data) {
                        datum.Checked=false;
                        for (ProductBean.DataBean.Product product : datum.list) {
                            product.ProductChecked=false;
                        }
                    }
                }
                shopAdapter.notifyDataSetChanged();
                getPrice();
            }
        });

    }

    @Override
    public void success(List<ProductBean.DataBean> list) {
        if (list!=null){
            data=list;
            for (ProductBean.DataBean datum : data) {
                for (ProductBean.DataBean.Product product : datum.list) {
                     product.num=1;
                }
            }
        }
      shopAdapter=new ShopAdapter(list,getActivity());
        shopAdapter.setProductCallback(this);
      rv.setAdapter(shopAdapter);
    }

    @Override
    public void Failure(String msg) {

    }
    public  void getPrice(){
       double numm=0;
        for (ProductBean.DataBean datum : data) {
            for (ProductBean.DataBean.Product product : datum.list) {
                if (product.ProductChecked){
                    numm+=product.price*product.num;
                }
            }
        }
        tv.setText("合计:￥"+numm);


    }

    @Override
    public void nottttt() {
        getPrice();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkhttpUtils.getOkhttpUtils().connTask();
    }
}
