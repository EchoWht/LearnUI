package com.blskye.learnui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public class TitleLayout extends LinearLayout {

    private Button title_back;
    private Button title_edit;

    public TitleLayout(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
        title_back= (Button) findViewById(R.id.title_back);
        title_edit= (Button) findViewById(R.id.title_edit);

//        back 键
        title_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });

//        edit键
        title_edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"click edit",Toast.LENGTH_LONG).show();
            }
        });

    }


}
