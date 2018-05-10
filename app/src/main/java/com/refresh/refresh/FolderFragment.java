package com.refresh.refresh;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.nestrefreshlib.RefreshViews.RefreshLayout;
import com.nestrefreshlib.RefreshViews.RefreshListener;

import jp.wasabeef.recyclerview.animators.FadeInRightAnimator;
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator;


/**
 * Created by 不听话的好孩子 on 2018/5/8.
 */

public class FolderFragment extends BaseFragment {
    Handler handler = new Handler();
    private RefreshLayout refreshLayout;

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

//        refreshLayout.setLinearVirtical(AdapterUtils.produceFolderAdapter());
        FadeInUpAnimator animator = new FadeInUpAnimator();
        refreshLayout.setRecyclerviewProperity(AdapterUtils.produceFolderAdapter(),new LinearLayoutManager(getContext()), animator);

        //or

//      refreshLayout.setRecyclerviewProperity(adapter,layoutmanage);
    }
}
