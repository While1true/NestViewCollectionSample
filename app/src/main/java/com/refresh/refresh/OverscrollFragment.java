package com.refresh.refresh;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nestrefreshlib.RefreshViews.RefreshLayout;


/**
 * Created by 不听话的好孩子 on 2018/5/8.
 */

public class OverscrollFragment extends BaseFragment {
    private RefreshLayout refreshLayout;

    @Override
    public int getLayoutId() {
        return R.layout.refreshlayout;
    }

    @Override
    public void onViewCreated(@NonNull View view) {
        refreshLayout = view.findViewById(R.id.refreshlayout);
        refreshLayout.getAttrsUtils().setOVERSCROLL(true);
        RecyclerView recyclerView = refreshLayout.getmScroll();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(AdapterUtils.produceAdapter(20));
    }
}
