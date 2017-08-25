package com.example.yonglun.a520estate.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.yonglun.a520estate.list.HouseDetailContactActivity;
import com.example.yonglun.a520estate.list.HouseInfoDetailActivity;
import com.example.yonglun.a520estate.profile.ContactListActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static android.R.attr.button;
import static android.support.v4.app.ActivityCompat.invalidateOptionsMenu;


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

    private PagerAdapter mPagerAdapter;

    public static home newInstance() {
        home fragment = new home();
        return fragment;
    }
    private Button publishBtn;
    //private TextView accessTokenTV;
    private ImageView mFakeHome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mPager = (ViewPager)getActivity().findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);


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
        public ScreenSlidePagerAdapter(FragmentManager fm) {
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