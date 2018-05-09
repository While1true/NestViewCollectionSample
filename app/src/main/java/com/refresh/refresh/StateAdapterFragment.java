package com.refresh.refresh;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.nestrefreshlib.RefreshViews.AdapterHelper.StateAdapter;
import com.nestrefreshlib.RefreshViews.RefreshLayout;
import com.nestrefreshlib.RefreshViews.RefreshListener;
import com.nestrefreshlib.State.StateLayout;


/**
 * Created by 不听话的好孩子 on 2018/5/8.
 */

public class StateAdapterFragment extends BaseFragment {
    Handler handler = new Handler();
    private RefreshLayout refreshLayout;
    private StateAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.refreshlayout5;
    }

    int i = 0;

    @Override
    public void onViewCreated(@NonNull View view) {
        refreshLayout = view.findViewById(R.id.refreshlayout);
        view.findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i % 4 == 0)
                    adapter.showLoading();
                else if (i % 4 == 1)
                    adapter.showEmpty();
                else if (i % 4 == 2)
                    adapter.showItem();
                else {
                    adapter.showNomore();
                    RecyclerView getmScroll = refreshLayout.getmScroll();
                    getmScroll.scrollToPosition(adapter.getItemCount()-1);
                }
                i++;
            }
        });
        refreshLayout.setListener(new RefreshListener() {
            @Override
            public void Refreshing() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_LONG).show();
                        refreshLayout.NotifyCompleteRefresh0();
                    }
                }, 500);
            }

            @Override
            public void Loading() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "加载完成", Toast.LENGTH_LONG).show();
                        refreshLayout.NotifyCompleteRefresh0();
                        refreshLayout.setShowNomore(true);
                    }
                }, 500);
            }
        });

        adapter = AdapterUtils.produceStateAdapter(20);
        refreshLayout.setLinearVirtical(adapter);

        //or

//      refreshLayout.setRecyclerviewProperity(adapter,layoutmanage);
    }
}
