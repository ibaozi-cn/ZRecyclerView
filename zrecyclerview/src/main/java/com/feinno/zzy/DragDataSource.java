package com.feinno.zzy;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.feinno.zzy.callback.SortListCallBack;


/**
 * @author zzy05 zhangzhanyong@feinno.com
 * @version V1.0
 * @project: ngcc-mobile-circle
 * @description: //TODO 支持拖动，滑动删除功能
 * @date 2016/8/13 15:45
 * @updateUser zzy05
 * @update 2016/8/13 15:45
 */
public abstract class DragDataSource extends SortDataSource {

    ItemTouchHelper.SimpleCallback mSimpleCallback;
    ItemTouchHelper mItemTouchHelper;
    /**
     * 能拖动说明不能排序
     * @param simpleCallback
     */
    public DragDataSource(ItemTouchHelper.SimpleCallback simpleCallback, RecyclerView recyclerView) {
        //设置为不能排序
        super(SortListCallBack.NOSC);
        mSimpleCallback = simpleCallback;
        mItemTouchHelper=new ItemTouchHelper(simpleCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
