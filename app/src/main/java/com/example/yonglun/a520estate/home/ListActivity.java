package com.example.yonglun.a520estate.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.yonglun.a520estate.list.RecyclerAdapter;
import com.example.yonglun.a520estate.models.HouseInfo;
import com.google.android.gms.appindexing.AppIndex;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class ListActivity extends SwipeBackActivity {
    private Toolbar mToolBar;
    private SwipeBackLayout mSwipeBackLayout;
    private Activity mAct;
    private RecyclerAdapter mHouseListAdapter;
    private JSONArray mJsonList;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mTitle=(TextView)findViewById(R.id.list_title);
        switch (getIntent().getFlags()){
            case 0:
                mTitle.setText("租房");
                break;
            case 1:
                mTitle.setText("二手");
                break;
            case 2:
                mTitle.setText("新房");
                break;
            default:
                break;
        }
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        mAct=this;
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAct.finish();
            }
        });



        RequestQueue queue = Volley.newRequestQueue(getBaseContext());

        String url = Globals.mainList;


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

                            mJsonList = new JSONArray(response);
                            String res = "";
                            for (int i=0;i<mJsonList.length();i++){
                                JSONObject item = mJsonList.getJSONObject(i);
                                list.add(new HouseInfo(item));
                            }
                            //accessTokenTV.setText(res);


                            mHouseListAdapter = new RecyclerAdapter(mAct,list,0);
                            RecyclerView RV=(RecyclerView)findViewById(R.id.house_list_view);
                            RV.setLayoutManager(new LinearLayoutManager(mAct));
                            RV.setAdapter(mHouseListAdapter);
                            RV.setNestedScrollingEnabled(false);

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
