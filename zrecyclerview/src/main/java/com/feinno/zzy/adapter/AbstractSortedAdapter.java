package com.feinno.zzy.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.feinno.zzy.BaseViewHolder;
import com.feinno.zzy.OnItemClickListener;
import com.feinno.zzy.callback.SortListCallBack;
import com.feinno.zzy.i.IAdapter;
import com.feinno.zzy.i.IDataSource;
import com.feinno.zzy.vm.BaseSortViewModel;

import java.util.List;

/**
 * 项目名称：circle 类描述： 创建人：zzy(zhangzhanyong@feinno.com) 创建时间：16/8/2 上午10:42 修改人： 修改时间：16/8/2 上午10:42
 * 修改备注：
 */
public abstract class AbstractSortedAdapter extends RecyclerView.Adapter<BaseViewHolder> implements IAdapter<BaseSortViewModel>, IDataSource {

    protected SortedList<BaseSortViewModel> mItemSortedList;
    private OnItemClickListener mOnItemClickListener;
    private SparseArray<BaseSortViewModel> mBaseViewModelSparseArray = new SparseArray<>();

    @SortListCallBack.Sequence
    protected int mSequence = SortListCallBack.DESC;

    private void addItemType(final BaseSortViewModel vm) {
        BaseSortViewModel baseSortViewModel = new BaseSortViewModel() {
            @Override
            public String getUUId() {
                return "";
            }

            @Override
            public long getSortId() {
                return 0;
            }

            @Override
            public int getLayoutRes() {
                return vm.getLayoutRes();
            }

            @Override
            public BaseViewHolder getViewHolder(ViewGroup parent) {
                return vm.getViewHolder(parent);
            }

            @Override
            public BaseViewHolder getViewHolder(View itemView) {
                return vm.getViewHolder(itemView);
            }

            @Override
            public void bindModel(@NonNull BaseViewHolder holder, @NonNull Object o) {

            }
        };
        mBaseViewModelSparseArray.put(vm.getItemType(), baseSortViewModel);
    }

    private BaseSortViewModel getTypeItem(int type) {
        return mBaseViewModelSparseArray.get(type);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        try {
            BaseSortViewModel item = getItem(position);
            holder.itemView.setTag(item);
            item.bindModel(holder, item.getModel());
            if (item.isAnimation())
                item.startViewAnimation(holder);
        } catch (Exception e) {
            Log.e("onBindViewHolder", e.toString());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getItemType();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final BaseViewHolder vh = getTypeItem(viewType).getViewHolder(parent);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null)
                    mOnItemClickListener.onClick(v, vh.getAdapterPosition());
            }
        });
        vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null)
                    mOnItemClickListener.onLongClick(v, vh.getAdapterPosition());
                return true;
            }
        });
        return vh;
    }


    @Override
    public int getItemCount() {
        return mItemSortedList.size();
    }

    @Override
    public void addItem(@NonNull BaseSortViewModel item) {
        addItemType(item);
        mItemSortedList.add(item);
    }


    @Override
    public void addItems(@NonNull BaseSortViewModel... items) {
        for (BaseSortViewModel item : items) {
            addItemType(item);
        }
        mItemSortedList.addAll(items);
    }

    @Override
    public void addListItems(@NonNull List<? extends BaseSortViewModel> items) {
        mItemSortedList.beginBatchedUpdates();
        for (BaseSortViewModel item :
                items) {
            addItemType(item);
            addItem(item);
        }
        mItemSortedList.endBatchedUpdates();
    }

    @Override
    public void removeItem(@NonNull BaseSortViewModel item) {
        int index = mItemSortedList.indexOf(item);
        if (index > -1 && index < getItemCount())
            mItemSortedList.remove(item);
    }

    @Override
    public void removeItem(@NonNull int position) {
        if (position > -1 && position < getItemCount())
            mItemSortedList.removeItemAt(position);
    }

    public void removeItem(@NonNull int... position) {
        for (int aPosition : position) {
            removeItem(aPosition);
        }
    }

    @Override
    public void removeItems(@NonNull BaseSortViewModel... items) {
        for (BaseSortViewModel item :
                items) {
            removeItem(item);
        }
    }

    @Override
    public void removeAll() {
        mBaseViewModelSparseArray.clear();
        mItemSortedList.clear();
    }

    @Override
    public void replaceItem(@NonNull int index, @NonNull BaseSortViewModel item) {
        mItemSortedList.updateItemAt(index, item);
    }

    public void updateItem(@NonNull BaseSortViewModel item) {
        int inde = mItemSortedList.indexOf(item);
        if (inde > -1) {
            mItemSortedList.updateItemAt(inde, item);
        }
    }

    @Override
    public synchronized boolean move(@NonNull int fromIndex, @NonNull int toIndex) {
        mItemSortedList.recalculatePositionOfItemAt(fromIndex);
        mItemSortedList.recalculatePositionOfItemAt(toIndex);
        return true;
    }

    @Override
    public BaseSortViewModel getItem(int index) {
        if (index < getItemCount())
            return mItemSortedList.get(index);
        return null;
    }

    @Override
    public boolean contains(@NonNull BaseSortViewModel item) {
        return mItemSortedList.indexOf(item) > -1;
    }

    @Override
    public int getListSize() {
        return mItemSortedList.size();
    }

    public AbstractSortedAdapter getAdapter() {
        return this;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public SortedList<BaseSortViewModel> getItemSortedList() {
        return mItemSortedList;
    }

    protected void setItemSortedList(SortedList<BaseSortViewModel> itemSortedList) {
        mItemSortedList = itemSortedList;
    }

    public int getSequence() {
        return mSequence;
    }

    @Override
    public long getFirstOneSortId() {
        return mItemSortedList.get(0).getSortId();
    }

    @Override
    public long getLastOneSortId() {
        return mItemSortedList.get(getItemCount() - 1).getSortId();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        removeAll();
        mBaseViewModelSparseArray.clear();
        super.onDetachedFromRecyclerView(recyclerView);
    }
}
