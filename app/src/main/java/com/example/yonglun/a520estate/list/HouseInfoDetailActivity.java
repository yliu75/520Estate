package com.example.yonglun.a520estate.list;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yonglun.a520estate.R;
import com.example.yonglun.a520estate.Utility.Globals;
import com.example.yonglun.a520estate.Utility.MapActivity;
import com.example.yonglun.a520estate.home.HomeCarouselFragment;
import com.example.yonglun.a520estate.models.HouseInfo;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

//public class HouseInfoDetailActivity extends AppCompatActivity  {
public class HouseInfoDetailActivity extends SwipeBackActivity {

    private static int NUM_PAGES = 5;


    private ImageView mMap;
    private TextView mPosition;
    private TextView mDetailTitle;
    private TextView mDetailStatus;
    private TextView mDetailSubTitle;
    private TextView mDetailInfo;
    private TextView mDetailPrice;
    private TextView mContactInfo;



    private Toolbar mToolBar;
    private JSONObject mJson;
    //private Button mBackButton;
    //private Button mChooseButton;
    private Activity mAct;
    private SwipeBackLayout mSwipeBackLayout;
    //private Context mContext;

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAct=this;
        setContentView(R.layout.activity_house_info_detail);
        mMap = (ImageView) findViewById(R.id.map);
        mDetailTitle=(TextView)findViewById(R.id.DetailTitle);
        mDetailSubTitle=(TextView)findViewById(R.id.DetailSubtitle);
        mDetailInfo=(TextView)findViewById(R.id.DetailDetailInfo);
        mDetailStatus=(TextView)findViewById(R.id.DetailStatus);
        mDetailPrice=(TextView)findViewById(R.id.DetailPrice);
        mContactInfo=(TextView)findViewById(R.id.contactInfo);

//        mPosition = (TextView) findViewById(R.id.position);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);

        RequestQueue queue = Volley.newRequestQueue(mAct);

        String url = Globals.apartmentDetail+getIntent().getFlags()+"";


//             Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            mJson = new JSONObject(response);
                            mDetailTitle.setText(mJson.getString("Title"));
                            //mDetailTitle.setMaxWidth(getWindowManager().getDefaultDisplay().getWidth()-150);
                            switch (mJson.getInt("Status")){
                                case 0:
                                    mDetailStatus.setText("出租");
                                    break;
                                case 1:
                                    mDetailStatus.setText("二手出售");
                                    break;
                                case 2:
                                    mDetailStatus.setText("新房出售");
                                    break;
                            }
                            Double price = mJson.getDouble("Price");
                            mDetailPrice.setText((price>10000?Math.round(price/1000)/10+"万元":price+"元")+(mJson.getInt("Period")!=-1?"/月":""));
                            mDetailSubTitle.setText(mJson.getString("City")+"-"+mJson.getString("District"));
                            mDetailInfo.setText(mJson.getString("Description"));
                            mContactInfo.setText("联系方式："+mJson.getString("Contacter")+" "+mJson.getString("Contact"));
                            JSONArray photoJson = mJson.getJSONArray("PhotoList");
                            NUM_PAGES=photoJson.length();
                            Globals.mPhotoAddressList.clear();
                            for (int i=0;i<photoJson.length();i++){
                                Globals.mPhotoAddressList.add(photoJson.getJSONObject(i).getString("Url"));
                            }

                            mPager = (ViewPager) findViewById(R.id.house_info_detail_viewPager);
                            mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
                            mPager.setAdapter(mPagerAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //accessTokenTV.setText("That didn't work!");
            }
        }){
            @Override
            public byte[] getBody() throws AuthFailureError {
                String httpPostBody="";
                // usually you'd have a field with some values you'd want to escape, you need to do it yourself if overriding getBody. here's how you do it
                try {
                    httpPostBody=httpPostBody+"&randomFieldFilledWithAwkwardCharacters="+ URLEncoder.encode("{{%stuffToBe Escaped/","UTF-8");
                } catch (UnsupportedEncodingException exception) {
                    Log.e("ERROR", "exception", exception);
                    // return null and don't pass any POST string if you encounter encoding error
                    return null;
                }
                return httpPostBody.getBytes();
            }

        };

//             Add the request to the RequestQueue.
        queue.add(stringRequest);
        //mChooseButton = (Button) findViewById(R.id.choose_btn);
        /*
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
        */
        //mBackButton = (Button) findViewById(R.id.back_button);

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


        mMap.setOnClickListener(new View.OnClickListener() {
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

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }




        @Override
        public Fragment getItem(int position) {

            Log.d("debug",String.valueOf(position));
            if (Globals.mPhotoAddressList.isEmpty()){
                return DetailCarouselFragment.create(position);
            }else{
                return DetailCarouselFragment.create(Globals.mPhotoAddressList.get(position));

            }
        }


        @Override
        public int getCount() {
            return NUM_PAGES;
        }
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
