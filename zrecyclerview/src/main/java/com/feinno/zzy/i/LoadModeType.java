package com.feinno.zzy.i;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 */
@IntDef(value = {LoadModeType.CLICK_LOAD, LoadModeType.AUTO_LOAD})
@Retention(RetentionPolicy.SOURCE)
public @interface LoadModeType {

    int CLICK_LOAD = 19901005;
    int AUTO_LOAD = 19910707;

}
