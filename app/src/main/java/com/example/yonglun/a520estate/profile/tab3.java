package com.example.yonglun.a520estate.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yonglun.a520estate.R;
import com.example.yonglun.a520estate.Utility.MapActivity;


public class tab3 extends Fragment {
    public static tab3 newInstance() {
        tab3 fragment = new tab3();
        return fragment;
    }


    ImageView mImageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageView = (ImageView)getActivity().findViewById(R.id.fake_profile);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "Recycle Click" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ContactListActivity.class);
                //intent.putExtra("position",position);
                //intent.putExtra("header",info.getThumbnail());
                getActivity().startActivityForResult(intent,1);
                //mContext.startActivity(intent);
            }
        });
    }
}