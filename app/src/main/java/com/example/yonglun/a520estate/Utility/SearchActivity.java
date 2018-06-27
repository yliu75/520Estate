package com.example.yonglun.a520estate.Utility;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.yonglun.a520estate.R;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class SearchActivity extends SwipeBackActivity {

    private android.widget.SearchView searchView;
    private RecyclerView recyclerView;
    private Activity mAct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAct=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        searchView=(android.widget.SearchView)findViewById(R.id.search_view);
        recyclerView=(RecyclerView) findViewById(R.id.search_result_recyclerView);
        //searchView.setQuery("test",true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocus();
        searchView.setOnCloseListener(new android.widget.SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if (searchView.getQuery()!="") {
                    searchView.setQuery("", true);
                }else {
                    mAct.finish();
                }
                return false;
            }
        });
        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                recyclerView.requestFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }

}
