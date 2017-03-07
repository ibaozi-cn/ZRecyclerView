package com.feinno.zzy;

import android.view.View;

/**
 * @author zzy05 zhangzhanyong@feinno.com
 * @version V1.0
 * @project: ngcc-mobile-circle
 * @description: //TODO(用一句话描述该文件做什么)
 * @date 2016/8/26 13:46
 * @updateUser zzy05
 * @update 2016/8/26 13:46
 */
public interface OnItemClickListener {

    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
