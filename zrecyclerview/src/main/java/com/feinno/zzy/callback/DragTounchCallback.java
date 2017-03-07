package com.feinno.zzy.callback;

import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * based on the sample from
 * https://github.com/AleBarreto/DragRecyclerView
 */
public class DragTounchCallback extends ItemTouchHelper.SimpleCallback {

    public interface ItemDragCallback {
        boolean itemTouchOnMove(int oldPosition, int newPosition);
    }
    public interface ItemTouchHelperViewHolder {

        void onItemSelected();

        void onItemClear();
    }
    public interface IDraggable<T> {

        boolean isDraggable();

        T setDraggable(boolean draggable);
    }

    //our callback
    private ItemDragCallback mCallbackItemTouch; // interface
    private boolean mIsDragEnabled = true;

    private int mDirections = UP_DOWN;

    public static final int ALL = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
    public static final int UP_DOWN = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
    public static final int LEFT_RIGHT = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

    @IntDef({ALL, UP_DOWN, LEFT_RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Directions {
    }

    public DragTounchCallback() {
        super(UP_DOWN, 0);
    }

    public DragTounchCallback(@Directions int directions) {
        super(directions, 0);
        this.mDirections = directions;
    }

    public DragTounchCallback(@Directions int directions, ItemDragCallback itemTouchCallback) {
        super(directions, 0);
        this.mDirections = directions;
        this.mCallbackItemTouch = itemTouchCallback;
    }

    public DragTounchCallback(ItemDragCallback itemTouchCallback) {
        super(UP_DOWN, 0);
        this.mCallbackItemTouch = itemTouchCallback;
    }

    public void setIsDragEnabled(boolean mIsDragEnabled) {
        this.mIsDragEnabled = mIsDragEnabled;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return mIsDragEnabled;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        if (mCallbackItemTouch == null) {
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter instanceof ItemDragCallback) {
                ItemDragCallback itemAdapter = (ItemDragCallback) adapter;
                itemAdapter.itemTouchOnMove(source.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }
            throw new RuntimeException("DragTounchCallback without an callback is only allowed when using the DragAdapter");
        }
        return mCallbackItemTouch.itemTouchOnMove(source.getAdapterPosition(), target.getAdapterPosition()); // information to the interface
    }

    @Override
    public int getDragDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.itemView.getTag() instanceof IDraggable) {
            if (((IDraggable) viewHolder.itemView.getTag()).isDraggable()) {
                return super.getDragDirs(recyclerView, viewHolder);
            } else {
                return 0;
            }
        } else {
            return mDirections;
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        // swiped disabled
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        // We only want the active item to change
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder instanceof ItemTouchHelperViewHolder) {
                // Let the view holder know that this item is being moved or dragged
                ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
                itemViewHolder.onItemSelected();
            }
        }

        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        viewHolder.itemView.setAlpha(1);

        if (viewHolder instanceof ItemTouchHelperViewHolder) {
            // Tell the view holder it's time to restore the idle state
            ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
            itemViewHolder.onItemClear();
        }
    }

    //获取拖动
    @Override
    public RecyclerView.ViewHolder chooseDropTarget(RecyclerView.ViewHolder selected, List<RecyclerView.ViewHolder> dropTargets, int curX, int curY) {
        return super.chooseDropTarget(selected, dropTargets, curX, curY);
    }

    //返回值作为用户视为拖动的距离
    @Override
    public float getMoveThreshold(RecyclerView.ViewHolder viewHolder) {
        return super.getMoveThreshold(viewHolder);
    }

    //返回值滑动消失的距离，滑动小于这个值不消失，大于消失
    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
        return super.getSwipeEscapeVelocity(defaultValue);
    }

    //设置手指离开后ViewHolder的动画时间
    @Override
    public long getAnimationDuration(RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
        return super.getAnimationDuration(recyclerView, animationType, animateDx, animateDy);
    }

    //返回值滑动消失的距离, 这里是相对于RecycleView的宽度，0.5f表示为RecycleView的宽度的一半，取值为0~1f之间
    @Override
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        return super.getSwipeThreshold(viewHolder);
    }

    //返回值作为滑动的流程程度，越小越难滑动，越大越好滑动
    @Override
    public float getSwipeVelocityThreshold(float defaultValue) {
        return 0.5f;
    }

    //当用户拖动一个视图出界的ItemTouchHelper调用
    @Override
    public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int viewSize, int viewSizeOutOfBounds, int totalSize, long msSinceStartScroll) {
        return super.interpolateOutOfBoundsScroll(recyclerView, viewSize, viewSizeOutOfBounds, totalSize, msSinceStartScroll);
    }
}