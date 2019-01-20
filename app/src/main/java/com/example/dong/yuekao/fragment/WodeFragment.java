package com.example.dong.yuekao.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dong.yuekao.R;

public class WodeFragment extends Fragment {
    private ImageView iv;
    private Button dian;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.wode_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        initData();
    }

    private void initData() {
    }

    private void initView(View view) {
        iv=view.findViewById(R.id.iv);
        dian=view.findViewById(R.id.dian);
        dian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(iv,"rotationY",0,180);
                ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(iv,"scaleY",0,2);
                AnimatorSet set =new AnimatorSet();
                set.play(objectAnimator).with(objectAnimator1);
                set.setDuration(5000);
                set.start();
            }
        });

    }
}
