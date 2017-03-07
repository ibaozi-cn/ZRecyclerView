package com.feinno.publibrary.recyclerviewdemo;

import android.view.View;
import android.widget.TextView;

import com.feinno.publibrary.R;
import com.feinno.zzy.BaseViewHolder;

/**
 * @author zzy05 zhangzhanyong@feinno.com
 * @version V1.0
 * @project: PubLibrary
 * @description: //TODO(用一句话描述该文件做什么)
 * @date 2017/2/5 10:36
 * @updateUser zzy05
 * @update 2017/2/5 10:36
 */

public class ItemTextHolder extends BaseViewHolder {


    public TextView textView;

    public ItemTextHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void initView() {
        textView = getView(R.id.text);
    }
}
