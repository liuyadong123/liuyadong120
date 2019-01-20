package com.example.dong.yuekao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.yuekao.R;

public class Myview  extends LinearLayout {
    private TextView jia,jian;
    private EditText ed_num;
    private  int num=1;
    public Myview(Context context) {
        super(context);
        init(context);
    }

    public Myview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Myview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        View view =LayoutInflater.from(context).inflate(R.layout.view_item,this,true);
        jia=view.findViewById(R.id.jia);
        jian=view.findViewById(R.id.jian);
        ed_num=view.findViewById(R.id.ed_num);
        ed_num.setText("1");
        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                ed_num.setText(num+"");
                if (jiaNumCallback!=null){
                    jiaNumCallback.onclick(num);
                }
            }
        });
        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num--;
                if (num==0){
                    num=1;
                    Toast.makeText(context,"不能再减了",Toast.LENGTH_SHORT).show();
                }
                ed_num.setText(num+"");
                if (jiaNumCallback!=null){
                    jiaNumCallback.onclick(num);
                }
            }
        });
    }
    private JiaNumCallback jiaNumCallback;

    public void setJiaNumCallback(JiaNumCallback jiaNumCallback) {
        this.jiaNumCallback = jiaNumCallback;
    }
    public  interface  JiaNumCallback{
        void onclick(int num);
    }
}
