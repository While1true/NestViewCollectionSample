package com.refresh.refresh;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.nestrefreshlib.Adpater.Impliment.SAdapter;
import com.nestrefreshlib.RecyclerviewFloatHelper.PositionFloatView;
import com.nestrefreshlib.RecyclerviewFloatHelper.ViewTypeFloatView;
import com.nestrefreshlib.RefreshViews.RefreshLayout;
import com.nestrefreshlib.RefreshViews.RefreshListener;


/**
 * Created by 不听话的好孩子 on 2018/5/8.
 */

public class FloatFragment extends BaseFragment {
    Handler handler = new Handler();
    private RefreshLayout refreshLayout;
    private FrameLayout root;

    @Override
    public int getLayoutId() {
        return R.layout.refreshlayout3;
    }

    @Override
    public void onViewCreated(@NonNull View view) {
        refreshLayout = view.findViewById(R.id.refreshlayout);
        root = view.findViewById(R.id.root);
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

        SAdapter adapter = AdapterUtils.produceAdapter(20);
        refreshLayout.setLinearVirtical(adapter);
        new ViewTypeFloatView(root,adapter.getItemViewType(0)).attachRecyclerview((RecyclerView) refreshLayout.getmScroll());
        //or new PositionFloatView(root,1,3,5...)

        //or

//      refreshLayout.setRecyclerviewProperity(adapter,layoutmanage);
    }
}
