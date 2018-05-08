package com.refresh.refresh;

import android.widget.Adapter;

import com.nestrefreshlib.Adpater.Base.Holder;
import com.nestrefreshlib.Adpater.Impliment.BaseHolder;
import com.nestrefreshlib.Adpater.Impliment.PositionHolder;
import com.nestrefreshlib.Adpater.Impliment.SAdapter;
import com.nestrefreshlib.RefreshViews.AdapterHelper.StateAdapter;

/**
 * Created by 不听话的好孩子 on 2018/5/8.
 */

public class AdapterUtils {
    public static SAdapter produceAdapter(int size){
        SAdapter adapter=new SAdapter(size)
                .addType(R.layout.item1,new PositionHolder() {
                    @Override
                    public void onBind(Holder holder, int i) {
                        holder.setText(R.id.textView,"第"+i+"个item");
                    }

                    @Override
                    public boolean istype(int position) {
                        return position%3==0;
                    }
                }).addType(R.layout.item2, new PositionHolder() {
                    @Override
                    public void onBind(Holder holder, int i) {
                        holder.setText(R.id.textView,"第"+i+"个item");
                    }

                    @Override
                    public boolean istype(int i) {
                        return true;
                    }
                });
        return adapter;
    }
}
