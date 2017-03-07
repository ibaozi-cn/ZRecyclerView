package com.feinno.publibrary.recyclerviewdemo;

import android.support.annotation.NonNull;

import com.feinno.publibrary.R;
import com.feinno.zzy.anim.ViewAnimator;
import com.feinno.zzy.vm.BaseSortViewModel;

/**
 * @author zzy05 zhangzhanyong@feinno.com
 * @version V1.0
 * @project: PubLibrary
 * @description: //TODO(用一句话描述该文件做什么)
 * @date 2017/2/5 10:39
 * @updateUser zzy05
 * @update 2017/2/5 10:39
 */

public class ItemTextViewModel extends BaseSortViewModel<ItemTextHolder, ItemTextModel> {

    @Override
    public int getLayoutRes() {
        return R.layout.item_text;
    }

    @Override
    public void bindModel(@NonNull ItemTextHolder holder, @NonNull ItemTextModel itemModel) {
        holder.textView.setText(itemModel.text);
    }

    @Override
    public long getSortId() {
        return getModel().sortId;
    }

    @Override
    public String getUUId() {
        return getModel().uuid;
    }

    @Override
    public void startViewAnimation(ItemTextHolder Holder) {
        super.startViewAnimation(Holder);
        ViewAnimator.animate(Holder.itemView).bounceIn().duration(2000).start();
    }
}
