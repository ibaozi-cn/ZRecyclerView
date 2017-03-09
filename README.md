# ZRecyclerView
    定制RecyclerView，实现MVVM框架。

    项目中总会遇到各种复杂的需求，让你忙的不可开交，有的时候一个问题就要解决好几天，甚至于放弃。不管再忙，作为一个技术爱好者，是不是应该给自己补充些能量，说不定下次解决问题时就会有新的思路，新的方法。好了不扯了，进入主题。
## MVVM理解

    都说是从MVC演变过来的，我相信任何一种事物的产生都有它必然的道理，既然被人总结出来，就有它的用武之地，编程中没有好与坏，只有适与不适，那么什么是MVVM？

    MVVM是Model-View-ViewModel的简写，同样也是分为三部分：

    Model：代表你的基本业务逻辑

    View：显示内容的视图

    ViewModel：将前面两者联系在一起的对象

### 它的优点：

    1. 低耦合。视图（View）可以独立于Model变化和修改，一个ViewModel可以绑定到不同的"View"上，当View变化的时候Model可以不变，当Model变化的时候View也可以不变。

    2. 可重用性。你可以把一些视图逻辑放在一个ViewModel里面，让很多view重用这段视图逻辑。

    3. 独立开发。开发人员可以专注于业务逻辑和数据的开发（ViewModel），设计人员可以专注于页面设计，使用Expression Blend可以很容易设计界面并生成xml代码。

    4. 可测试。界面素来是比较难于测试的，而现在测试可以针对ViewModel来写。
## RecyclerView为什么要实现MVVM

    平时研发过程中我们遇到的是，不同的页面需要写不同的Adapter 去适配，需要不同的数据填充，如果是同样的数据，不同的展现形式，或者只有部分复用的数据呢，这时候不得不去重新写一个新的adapter去组织这些数据，并以不同的UI展示，MVVM就是要解决这样的问题，抽象出ViewModel层，让Model和View充分复用，一个页面可以随意组合。这样千变万化的页面无非还是那些数据，我多写几个不同的View即可。下面讲解实现思路。
## View层实现

    这一层相对简单，都知道RecyclerView.ViewHolder吧，这个类不正好作为我们的View层使用吗，顺便也抽象下，直接粘代码好丑：

    public abstract class BaseViewHolder extends RecyclerView.ViewHolder { 
    /** 
     * views缓存 
     */ 
    private final SparseArray<View> views;
    private Context mContext;
 
    public BaseViewHolder(View itemView) {
        super(itemView);
        views = new SparseArray<>();
        mContext = itemView.getContext();
        initView(); 
    } 
 
    public <T extends View> T getView(@IdRes int viewId) {
        return retrieveView(viewId);
    } 
 
    private <T extends View> T retrieveView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            if(view==null)return null;
            views.put(viewId, view);
        } 
        return (T) view;
    } 
 
    public Context getContext() {
        return mContext;
    } 
 
    public abstract void initView(); 
    }
## Model层

    这一层也相对简单，完完全全就是你的数据对象，跟谁都可以结合。代码示例：

    public class ItemTextModel { 
        public String text;
        public long sortId;
        public String uuid;
    } 
## ViewModel层

    重点来了，这一层如何实现的呢？在RecyclerView 列表中一个ViewModel可以看做一个Item，一个Item有它的View和Model，将Model的数据填充到View上这就是这一层要做的事对吧。那么抽象出个类叫BaseViewModel，它的属性肯定有View 和Model，方法肯定是要将Model绑定到View上，如下方法

    public abstract void bindModel(@NonNull VH holder,@NonNull Model model)；

    这是个抽象方法，主要是为了在Adapter下面的onBindViewHolder方法中调用，实现业务逻辑的下移，使Adapter变成通用的适配器，以后只要写ViewModel就行了，任何不同的页面展示都可以使用这一个Adapter。

    public void onBindViewHolder(BaseViewHolder  holder,int  position){  

                BaseViewModel  vm= getItem(position);

                 vm.bindModel(holder,vm.getModel());

    } 

    代码展示的比较少，也简单说了下思路，具体还是要你到源码中一探究竟，当然这里没有写完，只是描述了MVVM的简单思路，我做了个Demo，里面还包括了对android.support.v7.util.SortedList 的封装运用，android.support.v7.widget.helper.ItemTouchHelper的封装运用。
