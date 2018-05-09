package com.refresh.refresh;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Refresh(View view) {
        showF(RefreshFragment.class);
    }

    public void showF(Class clazz){
        try {
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, (Fragment) clazz.newInstance()).addToBackStack(null).commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void Overscroll(View view) {
        showF(OverscrollFragment.class);
    }
    public void Elasticscroll(View view) {
        showF(ElasticFragment.class);
    }

    public void InnerRefresh(View view) {
        showF(InnerRefreshFragment.class);
    }

    public void HeaderExpand(View view) {
        showF(HeaderExpandFragment.class);
    }

    public void HorizontalScroll(View view) {
        showF(HorizontalFragment.class);
    }
}
