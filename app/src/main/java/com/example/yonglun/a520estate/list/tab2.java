package com.example.yonglun.a520estate.list;

import android.icu.text.IDNA;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.yonglun.a520estate.R;
import com.example.yonglun.a520estate.main.MainActivity;
import com.example.yonglun.a520estate.models.HouseInfo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class tab2 extends Fragment {
    public static tab2 newInstance() {
        tab2 fragment = new tab2();
        return fragment;
    }

    private CoordinatorLayout mCoordinatorLayout;
    private RecyclerAdapter mHouseListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {






        return inflater.inflate(R.layout.fragment_tab2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        //Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

       // String htmlStr="<font color='#ffffff'>520房产 </font>";
//        if (Build.VERSION.SDK_INT >= 24) {
//            toolbar.setTitle(Html.fromHtml(htmlStr,Html.FROM_HTML_MODE_LEGACY));
//        } else {
//            toolbar.setTitle(Html.fromHtml(htmlStr));
//        }


//        String[] fakedata={"1","2","3","4","5","6","7","8","9","10","11","12","13"};
//        List<String> fakeData=new ArrayList<String>(Arrays.asList(fakedata));

        ArrayList<HouseInfo> list=new ArrayList<>();
        list.add(new HouseInfo("房源 1","http://utility.oss-cn-shanghai.aliyuncs.com/tengrun/dev/img/sample_rooms/room_1.jpg",1));
        list.add(new HouseInfo("房源 2","http://utility.oss-cn-shanghai.aliyuncs.com/tengrun/dev/img/sample_rooms/room_2.jpg",2));
        list.add(new HouseInfo("房源 3","http://utility.oss-cn-shanghai.aliyuncs.com/tengrun/dev/img/sample_rooms/room_3.jpg",3));
        list.add(new HouseInfo("房源 4","http://utility.oss-cn-shanghai.aliyuncs.com/tengrun/dev/img/sample_rooms/room_4.jpg",4));
        list.add(new HouseInfo("房源 5","http://utility.oss-cn-shanghai.aliyuncs.com/tengrun/dev/img/sample_rooms/room_5.jpg",5));
        list.add(new HouseInfo("房源 6","http://utility.oss-cn-shanghai.aliyuncs.com/tengrun/dev/img/sample_rooms/room_6.jpg",6));
        list.add(new HouseInfo("房源 7","http://utility.oss-cn-shanghai.aliyuncs.com/tengrun/dev/img/sample_rooms/room_7.jpg",7));
        list.add(new HouseInfo("房源 8","http://utility.oss-cn-shanghai.aliyuncs.com/tengrun/dev/img/sample_rooms/room_8.jpg",8));
        for (int i = 9; i < 100; i++) {
            int random = new Random().nextInt(8)+1;
            list.add(new HouseInfo("房源 "+String.valueOf(i),"http://utility.oss-cn-shanghai.aliyuncs.com/tengrun/dev/img/sample_rooms/room_"+String.valueOf(random)+".jpg",random));

        }

        mHouseListAdapter = new RecyclerAdapter(getActivity(),list,0);
        //View rootView = inflater.inflate(R.layout.fragment_tab2,container);
        RecyclerView RV=(RecyclerView)getActivity().findViewById(R.id.house_list_view);
        RV.setLayoutManager(new LinearLayoutManager(getContext()));

        RV.setAdapter(mHouseListAdapter);

        //CollapsingToolbarLayout collapsingToolbar =(CollapsingToolbarLayout)getActivity().findViewById(R.id.CollapsingToolbar);

        //collapsingToolbar.setTitle("520");
        //mCoordinatorLayout = (CoordinatorLayout)getView().findViewById(R.id.coordinatorLayout);
    }
}