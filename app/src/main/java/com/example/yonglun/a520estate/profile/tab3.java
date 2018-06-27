package com.example.yonglun.a520estate.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yonglun.a520estate.R;
import com.example.yonglun.a520estate.Utility.Globals;
import com.example.yonglun.a520estate.Utility.MapActivity;

import org.w3c.dom.Text;


public class tab3 extends Fragment {
    ImageView mAvatar;
    TextView mUsernameTV;
    TextView mCreditTV;
    public static tab3 newInstance() {
        tab3 fragment = new tab3();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_tab3, container, false);

//        mAvatar=(ImageView)rootView.findViewById(R.id.profile_avatar);
        mUsernameTV=(TextView)rootView.findViewById(R.id.profile_username_textview);
//        mCreditTV=(TextView)rootView.findViewById(R.id.profile_credits_textview);

        if (Globals.isLogged) {
            mUsernameTV.setText(Globals.username);
        } else {
            mUsernameTV.setText("登录/注册");
        }

            return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}