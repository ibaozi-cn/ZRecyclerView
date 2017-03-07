package com.feinno.zzy.util;

import android.support.v7.widget.RecyclerView;

/**
 * @author zzy05 zhangzhanyong@feinno.com
 * @version V1.0
 * @project: ngcc-mobile-circle
 * @description: //TODO(用一句话描述该文件做什么)
 * @date 2016/8/26 10:27
 * @updateUser zzy05
 * @update 2016/8/26 10:27
 */
public interface LastVisibleItemPositionProvider {

    int getLastVisibleItemPosition(RecyclerView.LayoutManager layoutManager);

}
