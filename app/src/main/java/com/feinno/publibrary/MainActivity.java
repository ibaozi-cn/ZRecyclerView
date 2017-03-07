package com.feinno.publibrary;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.feinno.publibrary.recyclerviewdemo.ItemRoundImageModel;
import com.feinno.publibrary.recyclerviewdemo.ItemRoundImageViewModel;
import com.feinno.publibrary.recyclerviewdemo.ItemTextModel;
import com.feinno.publibrary.recyclerviewdemo.ItemTextViewModel;
import com.feinno.publibrary.util.RandomUtils;
import com.feinno.publibrary.util.UrlUtils;
import com.feinno.publibrary.widget.DragDialog;
import com.feinno.zzy.OnItemClickListener;
import com.feinno.zzy.SortDataSource;
import com.feinno.zzy.ZRecyclerView;
import com.feinno.zzy.vm.BaseSortViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    /**
     * RecyclerView封装框架 主要支持 上啦加载，下拉刷新，自动排序
     */
    private ZRecyclerView zRecyclerView;

    /**
     * 默认是降序排列
     */
    private SortDataSource sortDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        sortDataSource = new SortDataSource();

        //填充数据
        sortDataSource.addListItems(getData());

        zRecyclerView = (ZRecyclerView) findViewById(R.id.recyclerView);

        zRecyclerView.setDataResources(sortDataSource);

        //Item点击事件
        sortDataSource.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Snackbar.make(view, position + "onClick", Snackbar.LENGTH_SHORT).show();

                BaseSortViewModel baseSortViewModel = sortDataSource.getItem(position);
                if (baseSortViewModel.getSortId() == 2) {
                    DragDialog dragDialog = new DragDialog(MainActivity.this, new DragDialog.DragCallBack() {
                        @Override
                        public void move(int oldPosition, int newPosition) {

                        }
                    }, getData());
                    dragDialog.show();
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                Snackbar.make(view, position + "onLongClick", Snackbar.LENGTH_SHORT).show();
            }
        });
        //设置下拉刷新监听
        zRecyclerView.setOnDownLoadListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                zRecyclerView.setRefreshing(true);

                zRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        zRecyclerView.setRefreshing(false);
                        ItemTextModel itemModel = new ItemTextModel();
                        itemModel.sortId = RandomUtils.getRandom(0, 100);
                        itemModel.uuid = UUID.randomUUID().toString();
                        itemModel.text = "下拉" + itemModel.sortId;

                        ItemTextViewModel viewModel = new ItemTextViewModel();
                        viewModel.setModel(itemModel);

                        sortDataSource.addItem(viewModel);
                    }
                }, 3000);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            RecyclerView.LayoutManager layoutManager = zRecyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                zRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            } else {
                zRecyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<BaseSortViewModel> getData() {

        List<BaseSortViewModel> itemList = new ArrayList<>(10);

        ItemTextModel itemModel = new ItemTextModel();
        itemModel.sortId = 0;
        itemModel.uuid = "0";
        itemModel.text = "测试1";
        ItemTextViewModel viewModel1 = new ItemTextViewModel();
        viewModel1.setModel(itemModel);
        itemList.add(viewModel1);

        ItemRoundImageModel itemRoundImageModel = new ItemRoundImageModel();
        itemRoundImageModel.imageUrl = UrlUtils.getRandomImageURl();
        itemRoundImageModel.sortId = 1;
        itemRoundImageModel.uuid = "1";
        ItemRoundImageViewModel itemRoundImageViewModel = new ItemRoundImageViewModel();
        itemRoundImageViewModel.setModel(itemRoundImageModel);
        itemList.add(itemRoundImageViewModel);

        ItemTextModel itemTextModel = new ItemTextModel();
        itemTextModel.sortId = 2;
        itemTextModel.uuid = "2";
        itemTextModel.text = "DragDialog";
        ItemTextViewModel viewModel2 = new ItemTextViewModel();
        viewModel2.setModel(itemTextModel);
        itemList.add(viewModel2);

        return itemList;
    }

}
