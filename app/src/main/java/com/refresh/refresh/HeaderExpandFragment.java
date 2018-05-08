package com.refresh.refresh;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.nestrefreshlib.RefreshViews.RefreshLayout;
import com.nestrefreshlib.RefreshViews.RefreshListener;


/**
 * Created by 不听话的好孩子 on 2018/5/8.
 */

public class HeaderExpandFragment extends BaseFragment {
    Handler handler = new Handler();
    private RefreshLayout refreshLayout;
    private View img;
    private int height;

    @Override
    public int getLayoutId() {
        return R.layout.refreshlayout2;
    }

    @Override
    public void onViewCreated(@NonNull View view) {
        refreshLayout = view.findViewById(R.id.refreshlayout);
        img = view.findViewById(R.id.imagview);
        refreshLayout.setListener(new RefreshLayout.Callback() {
           @Override
           public void call(RefreshLayout.State state) {

           }

           @Override
           public void call(RefreshLayout.State t, int scroll) {
               super.call(t, scroll);
               if(t== RefreshLayout.State.PULL_HEADER){
                   if(height==0) {
                       height = img.getHeight();
                   }
                   img.getLayoutParams().height=height-scroll;
                   img.requestLayout();
               }
           }
       });
    }
}
