package com.refresh.refresh;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.nestrefreshlib.RefreshViews.RefreshLayout;
import com.nestrefreshlib.RefreshViews.RefreshListener;
import com.nestrefreshlib.State.StateLayout;


/**
 * Created by 不听话的好孩子 on 2018/5/8.
 */

public class StateFragment extends BaseFragment {
    Handler handler = new Handler();
    private RefreshLayout refreshLayout;
    private StateLayout stateLayout;

    @Override
    public int getLayoutId() {
        return R.layout.refreshlayout5;
    }

    int i = 0;

    @Override
    public void onViewCreated(@NonNull View view) {
        refreshLayout = view.findViewById(R.id.refreshlayout);
        stateLayout = view.findViewById(R.id.root);
        view.findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i % 3 == 0)
                    stateLayout.showLoading();
                else if (i % 3 == 1)
                    stateLayout.showEmpty();
                else if (i % 3 == 2)
                    stateLayout.showItem();
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

        refreshLayout.setLinearVirtical(AdapterUtils.produceAdapter(20));

        //or

//      refreshLayout.setRecyclerviewProperity(adapter,layoutmanage);
    }
}
