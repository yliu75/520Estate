package com.example.yonglun.a520estate.home;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.autonavi.ae.gmap.gesture.EAMapPlatformGestureInfo;
import com.example.yonglun.a520estate.R;
import com.example.yonglun.a520estate.Utility.Globals;
import com.example.yonglun.a520estate.list.HouseDetailContactActivity;
import com.example.yonglun.a520estate.list.HouseInfoDetailActivity;
import com.example.yonglun.a520estate.list.RecyclerAdapter;
import com.example.yonglun.a520estate.models.HouseInfo;
import com.example.yonglun.a520estate.profile.ContactListActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import static android.R.attr.button;
import static android.R.attr.drawable;
import static android.R.attr.imageButtonStyle;
import static android.support.v4.app.ActivityCompat.invalidateOptionsMenu;
import static android.support.v4.content.ContextCompat.getColor;


public class home extends Fragment {





    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    private boolean touchGreyFlag=false;
//    private float touchGreyX=0;
//    private float touchGreyY=0;

    private PagerAdapter mPagerAdapter;
    private HomeInfoRecyclerAdapter mHomeInfoListAdapter;
    private ScrollView mHomeScrollView;
    private TextView accessTokenTV;
    private JSONArray homeJson;

    public static home newInstance() {
        home fragment = new home();
        return fragment;
    }
    private Activity mAct;
    private Button publishBtn;
    //private TextView accessTokenTV;
    private ImageView mFakeHome;
    private ImageButton homeIcon0;
    private ImageButton homeIcon1;
    private ImageButton homeIcon2;
    private ImageButton homeIcon3;
    private ImageButton homeIcon4;
    private ImageButton homeIcon5;
    private ImageButton homeIcon6;
    private ImageButton homeIcon7;

    private ArrayList<ImageButton> homeIconList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mAct=getActivity();
        homeIconList=new ArrayList<>();


        mPager = (ViewPager)getActivity().findViewById(R.id.pager);
        mHomeScrollView =(ScrollView) getActivity().findViewById(R.id.home_scroll_view);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        homeIcon0 = (ImageButton)getActivity().findViewById(R.id.homeIcon0);
        homeIcon1 = (ImageButton)getActivity().findViewById(R.id.homeIcon1);
        homeIcon2 = (ImageButton)getActivity().findViewById(R.id.homeIcon2);
        homeIcon3 = (ImageButton)getActivity().findViewById(R.id.homeIcon3);



        homeIconList.add(homeIcon0);
        homeIconList.add(homeIcon1);
        homeIconList.add(homeIcon2);
        homeIconList.add(homeIcon3);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mHomeScrollView.setOnScrollChangeListener(new ScrollView.OnScrollChangeListener(){

                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    for (final ImageButton item: homeIconList) {
                        item.setBackgroundColor(getColor(mAct,R.color.white));

                    }
                }
            });
        }

        for (final ImageButton item: homeIconList) {
            item.setOnTouchListener(new View.OnTouchListener() {


                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN){
                        item.setBackgroundColor(getColor(mAct,R.color.lightGrey));
                        touchGreyFlag=true;
                        Intent intent = new Intent(getContext(),ListActivity.class);
                        intent.addFlags(homeIconList.indexOf(item));


                        getActivity().startActivityForResult(intent,1);
//                        touchGreyX=event.getX();
//                        touchGreyY=event.getY();
                    }

                    if (event.getAction() == MotionEvent.ACTION_UP){
                        //
                        // item.setBackgroundColor(getColor(mAct,R.color.white));

                        if (touchGreyFlag){
                            touchGreyFlag=false;


                            int colorFrom = getColor(mAct,R.color.lightGrey);
                            int colorTo = getColor(mAct,R.color.white);
                            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
                            colorAnimation.setDuration(150); // milliseconds

                            colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                                @Override
                                public void onAnimationUpdate(ValueAnimator animator) {
                                    item.setBackgroundColor((int) animator.getAnimatedValue());
                                }

                            });
                            colorAnimation.start();
                        }

                    }

                    return false;
                }
            });


            accessTokenTV = (TextView)getActivity().findViewById(R.id.accessToken);
            RequestQueue queue = Volley.newRequestQueue(getContext());
            String url = Globals.homeList;


//             Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //accessTokenTV.setText("Response is: "+ response);
                        try {
                            //String a="{\"ID\":4,\"Title\":\"红梅公园边三室两厅两卫132平米\",\"Country\":\"中国\",\"Province\":\"江苏\",\"City\":\"常州\",\"District\":\"天宁区\",\"Area\":null,\"ZipCode\":\"213000\",\"AddressTxt\":\"红梅新村32幢甲单元401\",\"Status\":0,\"Price\":2500.00,\"Period\":12,\"Cover\":\"1\",\"Contacter\":null,\"Contact\":null,\"Photos\":[3,4,5],\"PhotoStr\":\"3,4,5\"}";
                            ArrayList<HouseInfo> list=new ArrayList<>();

                            homeJson = new JSONArray(response);
                            String res = "";
                            for (int i=0;i<homeJson.length();i++){
                                JSONObject item = homeJson.getJSONObject(i);
                                list.add(new HouseInfo(item));
                            }
                            //accessTokenTV.setText(res);


                            mHomeInfoListAdapter = new HomeInfoRecyclerAdapter(getActivity(),list,0);
                            RecyclerView RV=(RecyclerView)getActivity().findViewById(R.id.homeinfo_recyclerView);
                            RV.setLayoutManager(new LinearLayoutManager(getContext()));
                            RV.setAdapter(mHomeInfoListAdapter);
                            RV.setNestedScrollingEnabled(false);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                accessTokenTV.setText("That didn't work!");
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




            mHomeScrollView.scrollTo(0,0);

        }







//        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                // When changing pages, reset the action bar actions since they are dependent
//                // on which page is currently active. An alternative approach is to have each
//                // fragment expose actions itself (rather than the activity exposing actions),
//                // but for simplicity, the activity provides the actions in this sample.
//                getActivity().invalidateOptionsMenu();
//            }
//        });



        //publishBtn = (Button)getActivity().findViewById(R.id.publish_button);
        //mFakeHome = (ImageView)getActivity().findViewById(R.id.fake_home);
//        publishBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), PublishApt.class);
//                getActivity().startActivityForResult(intent,1);
//
//            }
//        });

        //accessTokenTV = (TextView)getActivity().findViewById(R.id.accessToken);
        //RequestQueue queue = Volley.newRequestQueue(getContext());
        //String url ="https://tengrunmedia.com/Token";


        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        accessTokenTV.setText("Response is: "+ response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                accessTokenTV.setText("That didn't work!");
//            }
//        }){
//            @Override
//            public byte[] getBody() throws AuthFailureError {
//                String httpPostBody="grant_type=password&username=t1%40t.com&password=Yon4%24glu";
//                // usually you'd have a field with some values you'd want to escape, you need to do it yourself if overriding getBody. here's how you do it
//                try {
//                    httpPostBody=httpPostBody+"&randomFieldFilledWithAwkwardCharacters="+ URLEncoder.encode("{{%stuffToBe Escaped/","UTF-8");
//                } catch (UnsupportedEncodingException exception) {
//                    Log.e("ERROR", "exception", exception);
//                    // return null and don't pass any POST string if you encounter encoding error
//                    return null;
//                }
//                return httpPostBody.getBytes();
//            }
//
//        };

        // Add the request to the RequestQueue.
        //queue.add(stringRequest);
//        mFakeHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), HouseInfoDetailActivity.class);
//                getActivity().startActivityForResult(intent,1);
//
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }




    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return HomeCarouselFragment.create(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}