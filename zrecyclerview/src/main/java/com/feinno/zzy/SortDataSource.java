package com.feinno.zzy;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;

import com.feinno.zzy.adapter.AbstractSortedAdapter;
import com.feinno.zzy.callback.SortListCallBack;
import com.feinno.zzy.vm.BaseSortViewModel;


/**
 * @author zzy05 zhangzhanyong@feinno.com
 * @version V1.0
 * @project: ngcc-mobile-circle
 * @description: //TODO 主要支持 降序升序等排序功能
 * @date 2016/8/13 14:55
 * @updateUser zzy05
 * @update 2016/8/13 14:55
 */
public class SortDataSource extends AbstractSortedAdapter {

    SortListCallBack mSortListCallBack;

    public SortDataSource(@SortListCallBack.Sequence int sequence) {
        this.mSequence = sequence;
        mSortListCallBack = new SortListCallBack(this, sequence);
        setItemSortedList(new SortedList<>(BaseSortViewModel.class, mSortListCallBack));
        onLoadModel();
    }

    public SortDataSource() {
        mSortListCallBack = new SortListCallBack(this);
        setItemSortedList(new SortedList<>(BaseSortViewModel.class, mSortListCallBack));
        onLoadModel();
    }

    private RecyclerView.OnScrollListener mOnScrollListener;

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        recyclerView.removeOnScrollListener(mOnScrollListener);
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onLoadModel() {

    }
}
