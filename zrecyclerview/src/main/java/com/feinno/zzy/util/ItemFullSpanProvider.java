package com.feinno.zzy.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author zzy05 zhangzhanyong@feinno.com
 * @version V1.0
 * @project: ngcc-mobile-circle
 * @description: //TODO(用一句话描述该文件做什么)
 * @date 2016/8/26 10:04
 * @updateUser zzy05
 * @update 2016/8/26 10:04
 */
public interface ItemFullSpanProvider {

    /**
     * @param itemView RecyclerView itemView
     * @param rv RecyclerView
     * @param contentViewOrLoadMoreView true mean ContentView ,false mean LoadMoreView
     */
    void setItemFullSpan(View itemView, RecyclerView rv, boolean contentViewOrLoadMoreView);
}
