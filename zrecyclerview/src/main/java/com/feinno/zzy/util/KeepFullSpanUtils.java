package com.feinno.zzy.util;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author zzy05 zhangzhanyong@feinno.com
 * @version V1.0
 * @project: ngcc-mobile-circle
 * @description: //TODO(用一句话描述该文件做什么)
 * @date 2016/8/26 10:04
 * @updateUser zzy05
 * @update 2016/8/26 10:04
 */
public class KeepFullSpanUtils {

    public static void setFullSpanForStaggered(View loadMoreView, boolean matchParent) {

        ViewGroup.LayoutParams layoutParams = loadMoreView.getLayoutParams();
        if (layoutParams == null || !(layoutParams instanceof StaggeredGridLayoutManager.LayoutParams)) {
            layoutParams = new StaggeredGridLayoutManager.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, matchParent ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT);
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
            loadMoreView.setLayoutParams(layoutParams);

        } else {
            StaggeredGridLayoutManager.LayoutParams slp = (StaggeredGridLayoutManager.LayoutParams) layoutParams;

            if (matchParent) {
                if (!slp.isFullSpan() || slp.height != ViewGroup.LayoutParams.MATCH_PARENT) {
                    slp.setFullSpan(true);
                    slp.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    loadMoreView.setLayoutParams(layoutParams);
                }
            } else {
                if (!slp.isFullSpan() || slp.height == ViewGroup.LayoutParams.MATCH_PARENT) {
                    slp.setFullSpan(true);
                    slp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    loadMoreView.setLayoutParams(layoutParams);
                }
            }
        }
    }


    public  static void setFullSpanForGird(final GridLayoutManager gridLayoutManager, final GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        final int spanCount = gridLayoutManager.getSpanCount();
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == gridLayoutManager.getItemCount() - 1) {
                    return spanCount;
                }
                return spanSizeLookup.getSpanSize(position);
            }
        });
    }


    public static void setFullSpanForLinear(View loadMoreView, boolean heightMatchParent) {
        ViewGroup.LayoutParams lp = loadMoreView.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, heightMatchParent ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT);
            loadMoreView.setLayoutParams(lp);
        } else {
            if (heightMatchParent) {

                if (lp.height != ViewGroup.LayoutParams.MATCH_PARENT) {
                    lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    loadMoreView.setLayoutParams(lp);
                }

            } else {

                if (lp.height == ViewGroup.LayoutParams.MATCH_PARENT) {
                    lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    loadMoreView.setLayoutParams(lp);
                }

            }
        }
    }
}
