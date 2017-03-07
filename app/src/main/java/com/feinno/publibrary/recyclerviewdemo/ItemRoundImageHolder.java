package com.feinno.publibrary.recyclerviewdemo;

import android.view.View;

import com.feinno.publibrary.R;
import com.feinno.publibrary.widget.RoundedImageView;
import com.feinno.zzy.BaseViewHolder;

/**
 * @author zzy05 zhangzhanyong@feinno.com
 * @version V1.0
 * @project: PubLibrary
 * @description: //TODO(用一句话描述该文件做什么)
 * @date 2017/2/8 10:34
 * @updateUser zzy05
 * @update 2017/2/8 10:34
 */

public class ItemRoundImageHolder extends BaseViewHolder {

    public RoundedImageView roundedImageView;

    public ItemRoundImageHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void initView() {
        roundedImageView = getView(R.id.roundedImageView);
    }
}
