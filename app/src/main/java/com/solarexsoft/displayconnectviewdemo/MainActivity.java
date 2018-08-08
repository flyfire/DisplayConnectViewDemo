package com.solarexsoft.displayconnectviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.solarexsoft.displayconncetview.DisplayConnectView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.dcv_1)
    DisplayConnectView dcv_1;
    @BindView(R.id.dcv_2)
    DisplayConnectView dcv_2;
    @BindView(R.id.dcv_3)
    DisplayConnectView dcv_3;
    @BindView(R.id.dcv_4)
    DisplayConnectView dcv_4;
    @BindView(R.id.dcv_5)
    DisplayConnectView dcv_5;
    @BindView(R.id.dcv_6)
    DisplayConnectView dcv_6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dcv_1.setConnectType(DisplayConnectView.ConnectType.START);
        dcv_6.setConnectType(DisplayConnectView.ConnectType.END);
    }

    @OnClick({R.id.ll_main})
    public void click(View view) {
        DisplayConnectWithTextActivity.goDisplayConnectWithTextActivity(this);
    }
}
