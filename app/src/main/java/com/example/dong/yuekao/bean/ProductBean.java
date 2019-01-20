package com.example.dong.yuekao.bean;

import java.util.List;

public class ProductBean {


    public String msg;
    public String code;
    public List<DataBean> data;


    public  class DataBean {
        public String sellerName;
        public String sellerid;
        public  boolean Checked;
        public List<Product> list;
        public class  Product{
           public String title;
           public double price;
           public String subhead;
           public boolean ProductChecked;
           public int num;
           public String images;
           public int pos;
        }
    }
}
