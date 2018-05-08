package com.refresh.refresh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 不听话的好孩子 on 2018/5/7.
 */

public abstract class BaseFragment extends Fragment implements View.OnTouchListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewCreated(view);
        view.setOnTouchListener(this);
    }

    public abstract int getLayoutId();

    public abstract void onViewCreated(@NonNull View view);

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }
}
