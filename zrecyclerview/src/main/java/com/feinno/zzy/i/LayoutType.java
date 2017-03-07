package com.feinno.zzy.i;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@IntDef(value = {
        LayoutType.GRID,
        LayoutType.LINEAR,
        LayoutType.STAGGERED,
        LayoutType.OTHER
})
@Retention(RetentionPolicy.SOURCE)
public @interface LayoutType {
    int LINEAR = 10001;
    int GRID = 10002;
    int STAGGERED = 10003;
    int OTHER = 10004;

}
