package com.click.guide.marqueeeffect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.click.marquee_lib.interfaces.CallBackItem;
import com.click.marquee_lib.view.MarqueeRecyclerViews;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CallBackItem {

    private MarqueeRecyclerViews marqueeRecyclerViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        marqueeRecyclerViews = findViewById(R.id.mRv_View);
        initData();
    }


    private void initData() {
        ArrayList<String> data = new ArrayList<String>();
        String[] str = new String[]{"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA1111111", "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB222222", "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC33333"};
        for (int i = 0; i < 3; i++) {
            data.add(i, str[i]);
        }
        marqueeRecyclerViews.setData(data, this);
        marqueeRecyclerViews.startAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopAnimation();
    }

    private void startAnimation() {
        if (marqueeRecyclerViews != null) {
            marqueeRecyclerViews.startAnimation();
        }
    }

    private void stopAnimation() {
        if (marqueeRecyclerViews != null) {
            marqueeRecyclerViews.stopAnimation();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopAnimation();
    }

    @Override
    public void callBackPosition(int position, Object o) {

        Toast.makeText(this, "点击位置 " + position + " 返回数据 " + (String) o, Toast.LENGTH_LONG).show();
    }

}
