package com.example.yonglun.a520estate.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yonglun.a520estate.R;
import com.example.yonglun.a520estate.list.HouseDetailContactActivity;
import com.example.yonglun.a520estate.list.HouseInfoDetailActivity;
//import com.example.yonglun.a520estate.list.RecyclerAdapter;
import com.example.yonglun.a520estate.list.RecyclerAdapter;
import com.example.yonglun.a520estate.models.HouseInfo;
import com.squareup.picasso.Cache;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by yliu7 on 2017/11/1 0001.
 */

public class HomeInfoRecyclerAdapter extends RecyclerView.Adapter<HomeInfoRecyclerAdapter.InfoHolder> {


    enum Area
    {
        新北区, 钟楼区, 天宁区, 戚区, 武进区, 金坛区, 溧阳区;

        private static final List<HomeInfoRecyclerAdapter.Area> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static HomeInfoRecyclerAdapter.Area randomArea()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }
    private ArrayList<HouseInfo> houseInfoList;
    private Context mContext;
    private int mParameter;


    public HomeInfoRecyclerAdapter(Context context, ArrayList<HouseInfo> infos,int para) {
        mContext=context;
        houseInfoList = infos;
        mParameter=para;

    }





    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_info_item, null);
        InfoHolder holder = new InfoHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final InfoHolder holder, final int position) {
        holder.setIsRecyclable(false);
        final HouseInfo info = houseInfoList.get(position);
        HomeInfoRecyclerAdapter.InfoHolder.textView.setText(info.getTitle());
        HomeInfoRecyclerAdapter.InfoHolder.position=position;
        HomeInfoRecyclerAdapter.InfoHolder.mPrice.setText(info.price>10000?info.price/10000+"万元":Math.round(info.price)+"元"+(info.rentOrSale?"/月":""));
        HomeInfoRecyclerAdapter.InfoHolder.mArea.setText(info.city+"-"+ info.district);
        int lower = new Random().nextInt(12)+5;
        HomeInfoRecyclerAdapter.InfoHolder.mAcreage.setText(info.size+"平米");

        if (!TextUtils.isEmpty(info.getThumbnail())) {
            Picasso.with(mContext).load(info.getThumbnail())
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(HomeInfoRecyclerAdapter.InfoHolder.itemImage);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Recycle Click" + position, Toast.LENGTH_SHORT).show();
                if (mParameter==0){
                    Intent intent = new Intent(mContext,HouseInfoDetailActivity.class);
                    intent.putExtra("position",position);
                    intent.putExtra("header",info.getThumbnail());
                    intent.setFlags(houseInfoList.get(position).id);
                    ((Activity)mContext).startActivityForResult(intent,1);
                }else{
                    if (mParameter==1){
                        Intent intent = new Intent(mContext,HouseDetailContactActivity.class);
                        intent.putExtra("position",position);
                        intent.putExtra("header",info.getThumbnail());
                        ((Activity)mContext).startActivityForResult(intent,1);
                    }
                }

                //mContext.startActivity(intent);
            }
        });

        Log.d(String.valueOf(holder.getAdapterPosition()),"onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return (null != houseInfoList ? houseInfoList.size() : 0);
    }



    public static class InfoHolder extends RecyclerView.ViewHolder {
        //2
        private ImageView mItemImage;
        private TextView mItemDate;
        private TextView mTitle;
        private TextView mItemDescription;
        private HouseInfo mInfo;
        protected static TextView textView;
        protected static TextView mPrice;
        protected static TextView mArea;
        protected static TextView mAcreage;
        protected static ImageView itemImage;
        protected static int position;

        //3
        private static final String INFO_KEY = "INFO";

        //4
        public InfoHolder(View v) {
            super(v);

//            mItemImage = (ImageView) v.findViewById(R.id.item_image);
//            mItemDate = (TextView) v.findViewById(R.id.item_date);
//            mItemDescription = (TextView) v.findViewById(R.id.item_description);
            textView = (TextView) v.findViewById(R.id.title);
            itemImage = (ImageView) v.findViewById(R.id.thumbnail);
            mPrice = (TextView) v.findViewById(R.id.price);
            mArea = (TextView) v.findViewById(R.id.area);
            mAcreage = (TextView)v.findViewById(R.id.acreage);
            //v.setOnClickListener(this);
        }

        //5
//        @Override
//        public void onClick(View v) {
//            Log.d("RecyclerView", textView.getText()+" CLICK!");
//        }





    }
}
