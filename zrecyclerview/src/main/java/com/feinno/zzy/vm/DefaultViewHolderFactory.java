package com.feinno.zzy.vm;

import android.view.View;

import com.feinno.zzy.BaseViewHolder;
import com.feinno.zzy.i.IViewHolderFactory;

import java.lang.reflect.Constructor;

/**
 * @param <ViewHolder>
 */
class DefaultViewHolderFactory<ViewHolder extends BaseViewHolder>  implements IViewHolderFactory<ViewHolder> {


    @Override
    public ViewHolder create(View v, Class<? extends ViewHolder> clazz) {
        try {
            try {
                Constructor<? extends ViewHolder> constructor = clazz.getDeclaredConstructor(View.class);
                //could be that the constructor is not public
                constructor.setAccessible(true);
                return constructor.newInstance(v);
            } catch (NoSuchMethodException e) {
                //maybe that viewholder has a default view
                return clazz.newInstance();
            }
        } catch (Exception e) {
            // I am really out of options, you could have just set
            throw new RuntimeException("You have to provide a ViewHolder with a constructor which takes a view!");
        }
    }
}
