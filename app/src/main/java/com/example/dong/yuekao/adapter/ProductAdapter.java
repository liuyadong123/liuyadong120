package com.example.dong.yuekao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dong.yuekao.R;
import com.example.dong.yuekao.bean.ProductBean;
import com.example.dong.yuekao.callback.ShopCallbaack;
import com.example.dong.yuekao.view.Myview;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.CarVH> {
    private List<ProductBean.DataBean.Product> list;
    private Context context;
    private ShopCallbaack shopCallbaack;

    public void setShopCallbaack(ShopCallbaack shopCallbaack) {
        this.shopCallbaack = shopCallbaack;
    }

    public ProductAdapter(List<ProductBean.DataBean.Product> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CarVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).inflate(R.layout.product_item,viewGroup,false);
        CarVH carVH =new CarVH(view);
        return carVH;
    }

    @Override
    public void onBindViewHolder(@NonNull final CarVH carVH, final int i) {
        carVH.title.setText(list.get(i).title);
        carVH.price.setText("￥;"+list.get(i).price);
        carVH.tv_title.setText(list.get(i).subhead);
        String images = list.get(i).images;
        carVH.myview.setJiaNumCallback(new Myview.JiaNumCallback() {
            @Override
            public void onclick(int num) {
            list.get(i).num=num;
            if (shopCallbaack!=null){
                shopCallbaack.notySs();
            }
            }
        });
        String[] split = images.split("!");
        Glide.with(context).load(split[0]).into(carVH.imageView);
        carVH.checkBox.setChecked(list.get(i).ProductChecked);
        carVH.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).ProductChecked=carVH.checkBox.isChecked();
                 if (shopCallbaack!=null){
                     for (ProductBean.DataBean.Product product : list) {
                          if (!product.ProductChecked){
                              shopCallbaack.ShopNoty(false,product.pos);
                              return;
                          }
                         shopCallbaack.ShopNoty(true,product.pos);
                     }
                 }
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CarVH extends RecyclerView.ViewHolder {
        private TextView title,tv_title,price;
        private ImageView imageView;
        private CheckBox checkBox;
        private Myview myview;
        public CarVH(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            checkBox=itemView.findViewById(R.id.checkbox);
            imageView=itemView.findViewById(R.id.image);
            tv_title=itemView.findViewById(R.id.tv_title);
            price=itemView.findViewById(R.id.price);
            myview=itemView.findViewById(R.id.myview);

        }
    }
}
