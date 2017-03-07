package com.feinno.zzy.i;

import android.view.View;

import com.feinno.zzy.BaseViewHolder;


/**
 * Created by zzy on 16/7/24.
 */
public interface IViewHolderFactory<ViewHolder extends BaseViewHolder> {
    ViewHolder create(View v, Class<? extends ViewHolder> clazz);
}
