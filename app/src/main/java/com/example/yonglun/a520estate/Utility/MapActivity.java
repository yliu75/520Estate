package com.example.yonglun.a520estate.Utility;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener;
import com.amap.api.services.poisearch.PoiSearch.SearchBound;
import com.example.yonglun.a520estate.R;

import java.util.List;

import static android.R.attr.apiKey;

public class MapActivity extends Activity implements OnPoiSearchListener {

    MapView mMapView = null;
    Button mFoodBtn = null;
    Button mGasBtn = null;
    MapActivity mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        mFoodBtn = (Button) findViewById(R.id.button_food);
        mGasBtn = (Button) findViewById(R.id.button_gas);
        mContext = this;
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        //定义了一个地图view
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);

        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        MapsInitializer.setApiKey(getString(R.string.map_apikey));
        AMap aMap = mMapView.getMap();

        //设置中心点和缩放比例
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(31.771397,119.881861)));
        aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
        CameraPosition cameraPosition = aMap.getCameraPosition();
        MarkerOptions mo = new MarkerOptions();
        mo.position(new LatLng(31.771397,119.881861)).title("地址").snippet("Default");
        mo.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(),R.drawable.map_marker)));
        final Marker marker = aMap.addMarker(mo);

        //aMap.setPointToCenter(100,100);
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。


        PoiSearch.Query query = new PoiSearch.Query("", "050000", "常州");

        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        query.setPageSize(30);// 设置每页最多返回多少条poi item
        query.setPageNum(1);//设置查询页码
        PoiSearch poiSearch = new PoiSearch(this, query);

        LatLonPoint p=new LatLonPoint(31.771397,119.881861);
        poiSearch.setBound(new SearchBound(p, 1000));
        poiSearch.setOnPoiSearchListener(this);

        poiSearch.searchPOIAsyn();

        mFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PoiSearch.Query query1 = new PoiSearch.Query("", "050000", "常州");
                query1.setPageSize(30);// 设置每页最多返回多少条poi item
                query1.setPageNum(1);//设置查询页码
                PoiSearch poiSearch = new PoiSearch(mContext, query1);

                LatLonPoint p=new LatLonPoint(31.771397,119.881861);
                poiSearch.setBound(new SearchBound(p, 1000));
                poiSearch.setOnPoiSearchListener(mContext);

                poiSearch.searchPOIAsyn();
            }
        });

        mGasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PoiSearch.Query query2 = new PoiSearch.Query("", "010100", "常州");
                query2.setPageSize(30);// 设置每页最多返回多少条poi item
                query2.setPageNum(1);//设置查询页码
                PoiSearch poiSearch = new PoiSearch(mContext, query2);

                LatLonPoint p=new LatLonPoint(31.771397,119.881861);
                poiSearch.setBound(new SearchBound(p, 2000));
                poiSearch.setOnPoiSearchListener(mContext);

                poiSearch.searchPOIAsyn();
            }
        });

    }


    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        mMapView.getMap().clear();
        MarkerOptions mo = new MarkerOptions();
        mo.position(new LatLng(31.771397,119.881861)).title("地址").snippet("Default");
        mo.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(),R.drawable.map_marker)));
        final Marker marker1 = mMapView.getMap().addMarker(mo);
        for (PoiItem item : poiResult.getPois()) {
            LatLng point = new LatLng(item.getLatLonPoint().getLatitude(), item.getLatLonPoint().getLongitude());
            final Marker marker = mMapView.getMap().addMarker(new MarkerOptions().position(point).title(item.getTitle()).snippet("DefaultMarker"));

        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
