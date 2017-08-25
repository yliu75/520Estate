package com.example.yonglun.a520estate.profile;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yonglun.a520estate.R;
import com.example.yonglun.a520estate.list.RecyclerAdapter;
import com.example.yonglun.a520estate.models.HouseInfo;

import java.util.ArrayList;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class ContactListActivity extends SwipeBackActivity {

    private Activity mAct;
    private SwipeBackLayout mSwipeBackLayout;
    private RecyclerAdapter mContactHouseListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAct = this;

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        setContentView(R.layout.activity_contact_list);
        //getActionBar().setDisplayHomeAsUpEnabled(false);

        ArrayList<HouseInfo> list=new ArrayList<>();
        list.add(new HouseInfo("房源 1","http://utility.oss-cn-shanghai.aliyuncs.com/tengrun/dev/img/sample_rooms/room_1.jpg"));

        mContactHouseListAdapter = new RecyclerAdapter(this,list,1);
        RecyclerView RV=(RecyclerView)findViewById(R.id.house_list_view);
        RV.setLayoutManager(new LinearLayoutManager(this));

        RV.setAdapter(mContactHouseListAdapter);
    }

}
