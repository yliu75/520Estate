package com.example.yonglun.a520estate.main;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.yonglun.a520estate.R;
import com.example.yonglun.a520estate.Utility.Globals;
import com.example.yonglun.a520estate.Utility.SearchActivity;
import com.example.yonglun.a520estate.home.home;
import com.example.yonglun.a520estate.list.tab2;
import com.example.yonglun.a520estate.profile.tab3;


public class MainActivity extends AppCompatActivity {

        private Activity mAct;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            mAct=this;
            Globals.screenWidth= getWindowManager().getDefaultDisplay().getWidth();
            Globals.screenHeight= getWindowManager().getDefaultDisplay().getHeight();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            BottomNavigationView bottomNavigationView = (BottomNavigationView)
                    findViewById(R.id.navigation);

            bottomNavigationView.setOnNavigationItemSelectedListener
                    (new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            Fragment selectedFragment = null;
                            switch (item.getItemId()) {
                                case R.id.action_item1:
                                    selectedFragment = home.newInstance();
                                    break;
                                case R.id.action_item2:
                                    selectedFragment = tab2.newInstance();
                                    break;
                                case R.id.action_item3:
                                    selectedFragment = tab3.newInstance();
                                    break;
                            }
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout, selectedFragment);
                            transaction.commit();
                            return true;
                        }
                    });

            //Manually displaying the first fragment - one time only
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, home.newInstance());
            transaction.commit();
            //Used to select an item programmatically
            //bottomNavigationView.getMenu().getItem(2).setChecked(true);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.search_menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.search_menu){
            Intent intent = new Intent(this,SearchActivity.class);
            mAct.startActivityForResult(intent,1);
        }
        return super.onOptionsItemSelected(item);
    }
}

