package com.feinno.zzy.i;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by zzy on 16/3/20.
 */
public interface IAdapter<VM> {

    void addItem(@NonNull VM item);

    void addItems(@NonNull VM... items);

    void addListItems(@NonNull List<? extends VM> items);

    void removeItem(@NonNull VM item);

    void removeItem(@NonNull int position);

    void removeItems(@NonNull VM... items);

    void removeAll();

    void replaceItem(@NonNull int index, @NonNull VM item);

    boolean move(@NonNull int fromIndex, @NonNull int toIndex);

    VM getItem(int index);

    boolean contains(@NonNull VM item);

    int getListSize();

    long getLastOneSortId();

    long getFirstOneSortId();
}
