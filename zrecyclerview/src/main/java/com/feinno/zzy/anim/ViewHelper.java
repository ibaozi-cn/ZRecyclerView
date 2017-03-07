package com.feinno.zzy.anim;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * 项目名称：circles
 * 类描述：
 * 创建人：zzy(zhangzhanyong@feinno.com)
 * 创建时间：16/8/7 上午10:26
 * 修改人：
 * 修改时间：16/8/7 上午10:26
 * 修改备注：
 */

public final class ViewHelper {

  public static void clear(View v) {
    ViewCompat.setAlpha(v, 1);
    ViewCompat.setScaleY(v, 1);
    ViewCompat.setScaleX(v, 1);
    ViewCompat.setTranslationY(v, 0);
    ViewCompat.setTranslationX(v, 0);
    ViewCompat.setRotation(v, 0);
    ViewCompat.setRotationY(v, 0);
    ViewCompat.setRotationX(v, 0);
    ViewCompat.setPivotY(v, v.getMeasuredHeight() / 2f);
    ViewCompat.setPivotX(v, v.getMeasuredWidth() / 2f);
    ViewCompat.animate(v).setInterpolator(null).setStartDelay(0);
  }
}
