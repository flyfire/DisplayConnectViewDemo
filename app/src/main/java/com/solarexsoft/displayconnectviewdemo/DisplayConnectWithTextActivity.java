package com.solarexsoft.displayconnectviewdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.solarexsoft.displayconncetview.DisplayConnectWithTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <pre>
 *    Author: houruhou
 *    CreatAt: 11:52/2018/8/8
 *    Desc:
 * </pre>
 */
public class DisplayConnectWithTextActivity extends AppCompatActivity {
    @BindView(R.id.dcv_1)
    DisplayConnectWithTextView dcv_1;

    int i;
    String fixedText = "Solarex";
    boolean isfinished = false;

    public static void goDisplayConnectWithTextActivity(Context context) {
        Intent intent = new Intent(context, DisplayConnectWithTextActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayconnectwithtext);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_main})
    public void click(View view) {
        i++;
        String str = fixedText + i;
        dcv_1.setText(str);
        isfinished = !isfinished;
        dcv_1.setFinished(isfinished);
    }
}
