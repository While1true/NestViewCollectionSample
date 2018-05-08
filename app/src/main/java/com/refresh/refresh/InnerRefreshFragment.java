package com.refresh.refresh;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.nestrefreshlib.RefreshViews.AdapterHelper.AdapterScrollListener;
import com.nestrefreshlib.RefreshViews.RefreshLayout;
import com.nestrefreshlib.RefreshViews.RefreshListener;
import com.nestrefreshlib.RefreshViews.RefreshWrap.RefreshAdapterHandler;


/**
 * Created by 不听话的好孩子 on 2018/5/8.
 */

public class InnerRefreshFragment extends BaseFragment {
    Handler handler = new Handler();
    private RefreshLayout refreshLayout;
    private RefreshAdapterHandler refreshAdapterHandler;

    @Override
    public int getLayoutId() {
        return R.layout.refreshlayout;
    }

    @Override
    public void onViewCreated(@NonNull View view) {
        refreshLayout = view.findViewById(R.id.refreshlayout);
        refreshLayout.setListener(new RefreshListener() {
            @Override
            public void Refreshing() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.NotifyCompleteRefresh0();
                    }
                }, 500);
            }

            @Override
            public void Loading() {
                refreshAdapterHandler.stopLoading("正在加载...");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshAdapterHandler.stopLoading("没有更多了");
                    }
                }, 500);
            }
        });

        refreshAdapterHandler = new RefreshAdapterHandler();
        refreshAdapterHandler.attachRefreshLayout(refreshLayout,AdapterUtils.produceAdapter(20),new LinearLayoutManager(getContext()));
        //自动加载
        RecyclerView recyclerView= refreshLayout.getmScroll();
        recyclerView.addOnScrollListener(new AdapterScrollListener(refreshLayout));
    }
}
