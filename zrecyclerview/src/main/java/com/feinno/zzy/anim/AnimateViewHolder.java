package com.feinno.zzy.anim;

import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;

import com.feinno.zzy.BaseViewHolder;


/**
 * 项目名称：circles
 * 类描述：
 * 创建人：zzy(zhangzhanyong@feinno.com)
 * 创建时间：16/8/7 上午10:26
 * 修改人：
 * 修改时间：16/8/7 上午10:26
 * 修改备注：
 */
public abstract class AnimateViewHolder extends BaseViewHolder {

  public AnimateViewHolder(View itemView) {
    super(itemView);
  }

  public void preAnimateAddImpl() {
  }

  public void preAnimateRemoveImpl() {
  }

  public abstract void animateAddImpl(ViewPropertyAnimatorListener listener);

  public abstract void animateRemoveImpl(ViewPropertyAnimatorListener listener);
}
