package com.feinno.zzy.callback;

import android.support.annotation.IntDef;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;

import com.feinno.zzy.vm.BaseSortViewModel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author zzy05 zhangzhanyong@feinno.com
 * @version V1.0
 * @project: ngcc-mobile-circle
 * @description: //TODO 按照sortId排序
 * @date 2016/8/13 13:43
 * @updateUser zzy05
 * @update 2016/8/13 13:43
 */
public class SortListCallBack extends SortedList.Callback<BaseSortViewModel> {

    private RecyclerView.Adapter mAdapter;
    private OnDataChangeListener mOnDataChange;

    public void setOnDataChangeListener(OnDataChangeListener onDataChange) {
        mOnDataChange = onDataChange;
    }

    /**
     * 升序
     */
    public static final int ASC = 0;
    /**
     * 降序
     */
    public static final int DESC = 1;
    /**
     * 无序
     */
    public static final int NOSC = 2;

    @IntDef({ASC, DESC, NOSC})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Sequence {
    }

    /**
     * 0 降序 1 升序 2 无序 默认0 降序
     */
    @Sequence
    private int sequence = DESC;

    public int getSequence() {
        return sequence;
    }

    public void setSequence(@Sequence int sequence) {
        this.sequence = sequence;
    }

    public SortListCallBack(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
    }

    public SortListCallBack(RecyclerView.Adapter adapter, @Sequence int sequence) {
        mAdapter = adapter;
        this.sequence = sequence;
    }

    @Override
    public int compare(BaseSortViewModel o1, BaseSortViewModel o2) {
        if (sequence == NOSC) return 0;
        if (o1.getSortId() < o2.getSortId()) {
            if (sequence == ASC) return -1;
            else return 1;
        } else if (o1.getSortId() > o2.getSortId()) {
            if (sequence == ASC) return 1;
            else return -1;
        }
        return 0;
    }

    @Override
    public void onInserted(int position, int count) {
        mAdapter.notifyItemRangeInserted(position, count);
        if (mOnDataChange != null)
            mOnDataChange.onInserted(position, count);
    }

    @Override
    public void onRemoved(int position, int count) {
        if (mOnDataChange != null)
            mOnDataChange.onRemoved(position, count);
        mAdapter.notifyItemRangeRemoved(position, count);
    }

    @Override
    public void onMoved(int fromPosition, int toPosition) {
        mAdapter.notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onChanged(int position, int count) {
        synchronized (this) {
            if (mOnDataChange != null)
                mOnDataChange.onChanged(position, count);
            mAdapter.notifyItemRangeChanged(position, count);
        }
    }

    @Override
    public boolean areContentsTheSame(BaseSortViewModel oldItem, BaseSortViewModel newItem) {
        return false;
    }

    @Override
    public boolean areItemsTheSame(BaseSortViewModel item1, BaseSortViewModel item2) {
        return sequence != NOSC && (item1.getUUId().equals(item2.getUUId()));
    }

    public interface OnDataChangeListener {
        void onChanged(int position, int count);

        void onInserted(int position, int count);

        void onRemoved(int position, int count);
    }
}
