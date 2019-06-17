package com.click.marquee_lib.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.click.marquee_lib.R;
import com.click.marquee_lib.adapter.MarqueeAdapter;
import com.click.marquee_lib.interfaces.CallBackItem;

import java.util.ArrayList;


/**
 * Created by jhonjson on 2019/6/15.
 *
 * @Description: 自循环 RecyclerView
 * @csdn https://blog.csdn.net/github_34402358
 */
public class MarqueeRecyclerViews extends FrameLayout {
    private View rootView;
    private AutoPollRecyclerView apRvView;
    private MarqueeAdapter marqueeAdapter;
    private LooperLayoutManager layoutManager;

    public MarqueeRecyclerViews(@NonNull Context context) {
        this(context, null);
    }

    public MarqueeRecyclerViews(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeRecyclerViews(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化布局
     *
     * @param context
     */
    private void initView(Context context) {
        layoutManager = new LooperLayoutManager();
        rootView = LayoutInflater.from(context).inflate(R.layout.view_marquee_recyclerview, this, true);
        apRvView = rootView.findViewById(R.id.apRv_View);

    }

    /*赋值*/
    public void setData(ArrayList<String> mStr, final CallBackItem callBackItem) {

        if (marqueeAdapter == null) {
            marqueeAdapter = new MarqueeAdapter(mStr);
            layoutManager.setLooperEnable(true);
            apRvView.setAdapter(marqueeAdapter);
            apRvView.setLayoutManager(layoutManager);
            marqueeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (callBackItem != null) {
                        callBackItem.callBackPosition(position, adapter.getData().get(position));
                    }
                }
            });
        } else {
            marqueeAdapter.addData(mStr);
            marqueeAdapter.notifyItemRangeChanged(marqueeAdapter.getData().size() - mStr.size(), marqueeAdapter.getData().size());
        }

    }

    /*开启动画*/
    public void startAnimation() {

        if (apRvView != null) {
            apRvView.start();
        }
    }

    /*关闭动画*/
    public void stopAnimation() {
        if (apRvView != null) {
            apRvView.stop();
        }

    }


}