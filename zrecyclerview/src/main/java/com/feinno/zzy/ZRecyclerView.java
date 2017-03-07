package com.feinno.zzy;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.feinno.zzy.adapter.AbstractSortedAdapter;


/**
 * Created by zzy on 16/3/20.
 * 默认线性布局
 */
public class ZRecyclerView extends LinearLayout {

    private ZRecyclerView mZRecyclerView = ZRecyclerView.this;

    private AbstractSortedAdapter mDataResources;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;
    private LayoutParams mLayoutParams;


    private boolean isSwipeEnable = false;

    /**
     * 滚动监听
     */
    private OnScrollCallback mScrollCallback;

    /**
     * 滑动监听 默认当滑动到最后一项时加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                if (mScrollCallback != null) {
                    mScrollCallback.onStateChanged(mZRecyclerView, newState);
                    if (!recyclerView.canScrollVertically(1)) {
                        mScrollCallback.onScrollToBottom();
                    }
                    if (!recyclerView.canScrollVertically(-1)) {
                        mScrollCallback.onScrollToTop();
                    }
                }
            }

        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (mScrollCallback != null) {
                if (dy > 0) {
                    mScrollCallback.onScrollDown(mZRecyclerView, dy);
                } else {
                    mScrollCallback.onScrollUp(mZRecyclerView, dy);
                }
            }

        }
    };

    public ZRecyclerView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public ZRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public ZRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    /**
     * 初始化布局
     */
    private void init() {
        setOrientation(VERTICAL);
        mLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(mLayoutParams);

        mSwipeRefreshLayout = new SwipeRefreshLayout(mContext);
        mSwipeRefreshLayout.setEnabled(isSwipeEnable);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setLayoutParams(mLayoutParams);

        SwipeRefreshLayout.LayoutParams params = new SwipeRefreshLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mRecyclerView = new RecyclerView(mContext);
        mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        mRecyclerView.setLayoutParams(params);
        SimpleItemAnimator simpleItemAnimator = (SimpleItemAnimator) mRecyclerView.getItemAnimator();
        simpleItemAnimator.setSupportsChangeAnimations(false);

        mSwipeRefreshLayout.addView(mRecyclerView);

        addView(mSwipeRefreshLayout);

    }


    /**
     * 设置布局管理器
     *
     * @param layoutManager
     */
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mLayoutManager = layoutManager;
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    /**
     * 设置是否启动刷新
     *
     * @param isRefreshing
     */
    public void setRefreshing(boolean isRefreshing) {
        mSwipeRefreshLayout.setRefreshing(isRefreshing);
    }

    /**
     * 设置是否允许刷新
     *
     * @param isSwipeEnable
     */
    public void setIsSwipeEnable(Boolean isSwipeEnable) {
        this.isSwipeEnable = isSwipeEnable;
        mSwipeRefreshLayout.setEnabled(isSwipeEnable);
    }

    public void scrollToPosition(int position) {
        mRecyclerView.smoothScrollToPosition(position);
    }

    public void scrollToPositionOffset(int position, int offset) {
        if (mLayoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mLayoutManager;
            linearLayoutManager.scrollToPositionWithOffset(position, offset);
        }
    }

    /**
     * 设置数据源
     *
     * @param dataResources
     */
    public void setDataResources(AbstractSortedAdapter dataResources) {
        if (dataResources == null) {
            throw new NullPointerException("dataResources is null");
        }
        this.mDataResources = dataResources;
        mRecyclerView.setAdapter(dataResources);
    }

    public AbstractSortedAdapter getDataResources() {
        return mDataResources;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    /**
     * 设置下拉加载监听
     *
     * @param onDownLoadListener
     */
    public void setOnDownLoadListener(final SwipeRefreshLayout.OnRefreshListener onDownLoadListener) {
        mSwipeRefreshLayout.setEnabled(true);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setRefreshing(true);
                onDownLoadListener.onRefresh();
            }
        });
    }

    /**
     * 监听滑动监听，可以监听 滑动到底部 顶部，上下滑动事件
     *
     * @param scrollCallback
     */
    public void setScrollCallback(OnScrollCallback scrollCallback) {
        this.mScrollCallback = scrollCallback;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }


    public interface OnScrollCallback {

        void onStateChanged(ZRecyclerView recycler, int state);

        void onScrollUp(ZRecyclerView recycler, int dy);

        void onScrollDown(ZRecyclerView recycler, int dy);

        void onScrollToBottom();

        void onScrollToTop();
    }

    public void setOnActionDownEvent(onActionDownEvent onTouchEvent) {
        mOnTouchEvent = onTouchEvent;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN)
            if (mOnTouchEvent != null) {
                mOnTouchEvent.onDown(ev);
            }
        return super.dispatchTouchEvent(ev);
    }

    private onActionDownEvent mOnTouchEvent;

    public interface onActionDownEvent {
        void onDown(MotionEvent ev);
    }

}
