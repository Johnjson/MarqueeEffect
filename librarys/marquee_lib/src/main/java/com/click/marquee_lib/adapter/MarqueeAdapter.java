package com.click.marquee_lib.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import com.click.marquee_lib.R;

import java.util.List;

/**
 * @author：jhonjson
 * @date：2019/6/17
 * @describe：横向广告
 */
public class MarqueeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MarqueeAdapter(@Nullable List<String> data) {
        super(R.layout.marquee_item_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        TextView mTitle = helper.getView(R.id.tv_view);
        mTitle.setText(item);
    }
}
