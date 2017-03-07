package com.feinno.publibrary.recyclerviewdemo;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.view.View;

import com.bumptech.glide.Glide;
import com.feinno.publibrary.R;
import com.feinno.publibrary.RoundedImageViewActivity;
import com.feinno.zzy.vm.BaseSortViewModel;

/**
 * @author zzy05 zhangzhanyong@feinno.com
 * @version V1.0
 * @project: PubLibrary
 * @description: //TODO(用一句话描述该文件做什么)
 * @date 2017/2/8 10:36
 * @updateUser zzy05
 * @update 2017/2/8 10:36
 */

public class ItemRoundImageViewModel extends BaseSortViewModel<ItemRoundImageHolder, ItemRoundImageModel> {

    @Override
    public int getLayoutRes() {
        return R.layout.item_roundimageview;
    }

    @Override
    public void bindModel(@NonNull final ItemRoundImageHolder holder, @NonNull final ItemRoundImageModel itemRoundImageModel) {
        Glide.with(holder.getContext()).load(itemRoundImageModel.imageUrl).into(holder.roundedImageView);

        holder.roundedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.getContext() instanceof  Activity){
                    Activity activity = (Activity) holder.getContext();

                    String transitionName = "roundedTransition";
                    Pair pair = new Pair<>(holder.roundedImageView, transitionName);

                    RoundedImageViewActivity.startActivity(itemRoundImageModel.imageUrl, activity, pair);
                }
            }
        });


    }

    @Override
    public String getUUId() {
        return getModel().uuid;
    }

    @Override
    public long getSortId() {
        return getModel().sortId;
    }
}
