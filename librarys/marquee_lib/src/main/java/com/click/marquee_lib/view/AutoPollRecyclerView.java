package com.click.marquee_lib.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.lang.ref.WeakReference;

public class AutoPollRecyclerView extends RecyclerView {
    private static final long TIME_AUTO_POLL = 12;
    private AutoPollTask autoPollTask;
    //标示是否正在自动轮询
    private boolean isRunning;
    //标示是否可以自动轮询,可在不需要的是否置false
    private boolean isCanRun;

    public AutoPollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        autoPollTask = new AutoPollTask(this);
    }


    static class AutoPollTask implements Runnable {
        private final WeakReference<AutoPollRecyclerView> mReference;

        //使用弱引用持有外部类引用->防止内存泄漏
        public AutoPollTask(AutoPollRecyclerView reference) {
            this.mReference = new WeakReference<AutoPollRecyclerView>(reference);
        }

        @Override
        public void run() {
            try {
                AutoPollRecyclerView recyclerView = mReference.get();
                if (recyclerView != null && recyclerView.isRunning && recyclerView.isCanRun) {
                    recyclerView.scrollBy(2, 2);
                    recyclerView.postDelayed(recyclerView.autoPollTask, recyclerView.TIME_AUTO_POLL);
                }
            } catch (IllegalStateException e) {
                Log.i("IllegalStateException", " IllegalStateException " + e);
            }

        }
    }

    //开启:如果正在运行,先停止->再开启
    public void start() {
        if (isRunning)
            stop();
        isRunning = true;
        isCanRun = true;
        postDelayed(autoPollTask, TIME_AUTO_POLL);
    }

    public void stop() {
        isRunning = false;
        removeCallbacks(autoPollTask);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            //点击时，如果是在运行的，停止运行
            case MotionEvent.ACTION_DOWN:
                if (isRunning)
                    stop();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                if (isCanRun)
                    start();
                break;
        }
        return super.onTouchEvent(e);
    }
}
