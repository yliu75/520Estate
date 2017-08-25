package com.example.yonglun.a520estate.list;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yonglun.a520estate.R;
import com.example.yonglun.a520estate.Utility.MapActivity;


import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

//public class HouseInfoDetailActivity extends AppCompatActivity  {
public class HouseInfoDetailActivity extends SwipeBackActivity {
    private ImageView mHeader;
    private TextView mPosition;
    private Toolbar mToolBar;
    private Button mBackButton;
    private Button mChooseButton;
    private Activity mAct;
    private SwipeBackLayout mSwipeBackLayout;
    //private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_info_detail);
        mHeader = (ImageView) findViewById(R.id.header);
//        mPosition = (TextView) findViewById(R.id.position);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mChooseButton = (Button) findViewById(R.id.choose_btn);
        mChooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new AlertDialog.Builder(v.getContext())
                        .setMessage("请问您确定要购买此房联系人信息吗？")
                        .setCancelable(false)
                        .setPositiveButton("是的", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Toast.makeText(v.getContext(), "成功" , Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("我再想想", null)
                        .show();
            }
        });

        //mBackButton = (Button) findViewById(R.id.back_button);
        mAct = this;

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        //setSupportActionBar(mToolBar);
//        Intent intent = getIntent();
//        if (intent.hasExtra("header")) {
//            Picasso.with(this).load(intent.getStringExtra("header"))
//                    .error(R.drawable.placeholder)
//                    .placeholder(R.drawable.placeholder)
//                    .into(mHeader);
//        }
//        if (intent.hasExtra("position")) {
//
//            mPosition.setText(String.valueOf(intent.getIntExtra("position", 0)));
//
//        }
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAct.finish();
            }
        });
        //mBackButton.setOnClickListener();
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.


        mHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "Recycle Click" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mAct, MapActivity.class);
                //intent.putExtra("position",position);
                //intent.putExtra("header",info.getThumbnail());
                mAct.startActivityForResult(intent,1);
                //mContext.startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }





}
