package com.feinno.zzy.vm;

import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feinno.zzy.BaseViewHolder;
import com.feinno.zzy.i.IModel;
import com.feinno.zzy.i.IViewHolderFactory;

import java.lang.reflect.ParameterizedType;

/**
 * 项目名称：circles
 * 类描述：
 * 创建人：zzy(zhangzhanyong@feinno.com)
 * 创建时间：16/8/3 下午7:50
 * 修改人：
 * 修改时间：16/8/3 下午7:50
 * 修改备注：
 */
public abstract class BaseSortViewModel<VH extends BaseViewHolder, Model> implements IModel{

    /**
     * 数据
     */
    private Model model;
    /**
     * ViewHolder创建工厂
     */
    private IViewHolderFactory<VH> viewHolderFactory = new DefaultViewHolderFactory<>();
    /**
     * 是否加载动画
     */
    private boolean isAnimation = true;
    /**
     * 排序用
     */
    protected long sortId = 0L;
    /**
     * 唯一标识
     */
    protected String UUID = "";

    /**
     * 资源类型
     *
     */
    protected  int itemType = Integer.MIN_VALUE;

    public BaseSortViewModel() {
    }

    public BaseSortViewModel(Model model) {
        this.model = model;
    }

    @LayoutRes
    public abstract int getLayoutRes();

    public VH getViewHolder(ViewGroup parent) {
        return getViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutRes(), parent, false));
    }

    public VH getViewHolder(View itemView) {
        return viewHolderFactory.create(itemView, viewHolderType());
    }

    /**
     * gets the viewHolder via the generic superclass
     *
     * @return the class of the VH
     */
    private Class<? extends VH> viewHolderType() {
        return ((Class<? extends VH>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    /**
     * 设置自定义 ViewHolderFactory 负责反射创建ViewHolder对象
     *
     * @param viewHolderFactory
     */
    public void setViewHolderFactory(IViewHolderFactory<VH> viewHolderFactory) {
        this.viewHolderFactory = viewHolderFactory;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public abstract void bindModel(@NonNull VH holder, @NonNull Model model);

    public int getItemType() {
        return itemType == Integer.MIN_VALUE ? getLayoutRes() : itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    /**
     * 是否加载过动画
     *
     * @return
     */
    public boolean isAnimation() {
        return isAnimation;
    }

    /**
     * 是否重复加载
     * 设置true 就会一直加载动画
     *
     * @param animation
     */
    public void setAnimationRepeat(boolean animation) {
        isAnimation = animation;
    }

    /**
     * 子类重写该方法加动画效果
     *
     * @param Holder
     */
    @CallSuper
    public void startViewAnimation(VH Holder) {
        //默认加载一次动画
        setAnimationRepeat(false);
    }

    @Override
    public void setSortId(long sortId) {
        this.sortId = sortId;
    }

    @Override
    public void setUUId(String UUId) {
        this.UUID = UUId;
    }
}
