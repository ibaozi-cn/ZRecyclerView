package com.feinno.publibrary.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.feinno.publibrary.R;
import com.feinno.zzy.DragDataSource;
import com.feinno.zzy.OnItemClickListener;
import com.feinno.zzy.ZRecyclerView;
import com.feinno.zzy.callback.DragTounchCallback;
import com.feinno.zzy.vm.BaseSortViewModel;

import java.util.List;

/**
 * @author zzy05 zhangzhanyong@feinno.com
 * @version V1.0
 * @project: ngcc-mobile-circle
 * @description: //TODO(用一句话描述该文件做什么)
 * @date 2016/9/9 11:01
 * @updateUser zzy05
 * @update 2016/9/9 11:01
 */
public class DragDialog extends AlertDialog implements DragTounchCallback.ItemDragCallback {

    private LinearLayout mLinearLayout;
    private ZRecyclerView mZRecyclerView;
    private DragDataSource mDragDataSource;
    private DragTounchCallback mTounchCallback;
    private final DragCallBack mDragCallBack;
    private List<BaseSortViewModel> modelList;
    private boolean isDrag;

    public DragDialog(Context context, DragCallBack dragCallBack, List<BaseSortViewModel> modelList) {
        super(context, R.style.DragDialog);
        this.mDragCallBack = dragCallBack;
        this.modelList = modelList;
        Window window = this.getWindow();
        window.setGravity(Gravity.TOP);
        setCanceledOnTouchOutside(true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLinearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.recyclerview, null);
        mZRecyclerView = (ZRecyclerView) mLinearLayout.findViewById(R.id.recyclerView);

        mTounchCallback = new DragTounchCallback(this);

        mDragDataSource = new DragDataSource(mTounchCallback, mZRecyclerView.getRecyclerView()) {
            @Override
            public void onLoadModel() {
                addListItems(modelList);
            }
        };
        mZRecyclerView.setDataResources(mDragDataSource);

        mZRecyclerView.getRecyclerView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isShowing() && event.getAction() == MotionEvent.ACTION_UP) {
                    if (!isDrag) {
                        dismiss();
                    }else{
                        isDrag = false;
                    }
                }
                return false;
            }
        });

        setContentView(mLinearLayout);
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        mDragDataSource.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                dismiss();
                onItemClickListener.onClick(view, position);
            }

            @Override
            public void onLongClick(View view, int position) {
                onItemClickListener.onLongClick(view, position);
            }
        });
    }

    public BaseSortViewModel getItem(int position) {
        return mDragDataSource.getItem(position);
    }

    public DragDataSource getDataSource() {
        return mDragDataSource;
    }

    @Override
    public boolean itemTouchOnMove(int oldPosition, int newPosition) {
        isDrag = true;
        if (mDragCallBack != null)
            mDragCallBack.move(oldPosition, newPosition);
        return mDragDataSource.move(oldPosition, newPosition);
    }

    public interface DragCallBack {
        void move(int oldPosition, int newPosition);
    }

}
