package com.example.dong.yuekao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.dong.yuekao.R;
import com.example.dong.yuekao.bean.ProductBean;
import com.example.dong.yuekao.callback.ProductCallback;
import com.example.dong.yuekao.callback.ShopCallbaack;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.CarVH> implements ShopCallbaack {
    private List<ProductBean.DataBean> list;
    private Context context;
    private ProductCallback productCallback;

    public void setProductCallback(ProductCallback productCallback) {
        this.productCallback = productCallback;
    }

    public ShopAdapter(List<ProductBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CarVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).inflate(R.layout.shop_item,viewGroup,false);
        CarVH carVH =new CarVH(view);
        return carVH;
    }

    @Override
    public void onBindViewHolder(@NonNull final CarVH carVH, final int i) {
        carVH.title.setText(list.get(i).sellerName);
        carVH.checkBox.setChecked(list.get(i).Checked);
       final List<ProductBean.DataBean.Product> list1 =list.get(i).list;
        ProductAdapter productAdapter =new ProductAdapter(list1,context);
        for (ProductBean.DataBean.Product product : list1) {
            product.pos =i;
        }
        carVH.rv.setLayoutManager(new LinearLayoutManager(context));
        carVH.rv.setAdapter(productAdapter);
         productAdapter.setShopCallbaack(this);
        carVH.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 list.get(i).Checked=carVH.checkBox.isChecked();
                for (ProductBean.DataBean.Product product : list1) {
                    product.ProductChecked=list.get(i).Checked;
                }
                notifyDataSetChanged();

                if (productCallback!=null){
                    productCallback.nottttt();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void ShopNoty(boolean ischecked, int pos) {
       list.get(pos).Checked=ischecked;
       notifyDataSetChanged();
        if (productCallback!=null){
            productCallback.nottttt();
        }
    }

    @Override
    public void notySs() {
        if (productCallback!=null){
            productCallback.nottttt();
        }

    }

    public class CarVH extends RecyclerView.ViewHolder {
        private TextView title;
        private RecyclerView rv;
        private CheckBox checkBox;
        public CarVH(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            rv=itemView.findViewById(R.id.recy);
            checkBox=itemView.findViewById(R.id.checkbox);
        }
    }
}
