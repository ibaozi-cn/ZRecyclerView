package com.feinno.zzy.i;

/**
 * @author zzy05 zhangzhanyong@feinno.com
 * @version V1.0
 * @project: PubLibrary
 * @description: //TODO(用一句话描述该文件做什么)
 * @date 2017/2/8 9:54
 * @updateUser zzy05
 * @update 2017/2/8 9:54
 */

public interface IModel {

    //唯一标识
    String getUUId();

    void setUUId(String UUId);

    //排序ID
    long getSortId();

    void setSortId(long sortId);
}
