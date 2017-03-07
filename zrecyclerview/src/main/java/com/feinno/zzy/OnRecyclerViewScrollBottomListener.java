package com.feinno.zzy;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.feinno.zzy.util.LastVisibleItemPositionProvider;


/**
 * @author zzy05 zhangzhanyong@feinno.com
 * @version V1.0
 * @project: ngcc-mobile-circle
 * @description: //TODO(用一句话描述该文件做什么)
 * @date 2016/8/26 10:28
 * @updateUser zzy05
 * @update 2016/8/26 10:28
 */
abstract public class OnRecyclerViewScrollBottomListener extends RecyclerView.OnScrollListener {

    protected int mLayoutManagerType;

    private static final int LINEAR = 1;
    private static final int GRID = 2;
    private static final int STAGGERED_GRID = 3;

    private LastVisibleItemPositionProvider mLastVisibleItemPositionProvider;

    /**
     * 触发加载更多阈值，0 默认最后一条，1则 倒数第二条触发价值更多
     */
    private int mLoadingTriggerThreshold;

    public int getLoadingTriggerThreshold() {
        return mLoadingTriggerThreshold;
    }

    public void setLoadingTriggerThreshold(int loadingTriggerThreshold) {
        mLoadingTriggerThreshold = loadingTriggerThreshold;
    }

    /**
     * 最后一个的位置
     */
    private int[] mLastPositions;


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        if (mLayoutManagerType == 0) {
            if (layoutManager instanceof GridLayoutManager) {
                mLayoutManagerType = GRID;
            } else if (layoutManager instanceof LinearLayoutManager) {
                mLayoutManagerType = LINEAR;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                mLayoutManagerType = STAGGERED_GRID;
            } else {
                //do nothing
            }
        }

        /*
      最后一个可见的item的位置
     */
        int lastVisibleItemPosition;

        switch (mLayoutManagerType) {
            case LINEAR:
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                break;
            case GRID:
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                break;
            case STAGGERED_GRID:
                StaggeredGridLayoutManager staggeredGridLayoutManager
                        = (StaggeredGridLayoutManager) layoutManager;
                if (mLastPositions == null) {
                    mLastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(mLastPositions);
                lastVisibleItemPosition = findMax(mLastPositions);
                break;
            default: {
                if (mLastVisibleItemPositionProvider == null) {
                    throw new IllegalStateException("you should set LastVisibleItemPositionProvider when you use custom layoutManager");
                }
                lastVisibleItemPosition = mLastVisibleItemPositionProvider.getLastVisibleItemPosition(layoutManager);
                break;
            }
        }


        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();


        if (dy > 0 && (visibleItemCount > 0 && (lastVisibleItemPosition) >= totalItemCount - 1 - mLoadingTriggerThreshold)) {
            onBottom();
        }

    }

    public void setLastVisibleItemPositionProvider(LastVisibleItemPositionProvider lastVisibleItemPositionProvider) {
        mLastVisibleItemPositionProvider = lastVisibleItemPositionProvider;
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }


    public abstract void onBottom();

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }


}
