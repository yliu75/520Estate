package com.example.yonglun.a520estate.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.yonglun.a520estate.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;


public class HomeCarouselFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String ARG_PAGE = "page";
    private int pageNum;

    ImageView mImage;
    ScrollView mScrollView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;



    public static HomeCarouselFragment create(int pageNumber) {
        HomeCarouselFragment fragment = new HomeCarouselFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeCarouselFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            pageNum = getArguments().getInt(ARG_PAGE);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_home_carousel, container, false);

        mImage=(ImageView)rootView.findViewById(R.id.HomeCarouselImage);
        //mScrollView=(ScrollView)rootView.findViewById(R.id.home_scroll_view);
        //mScrollView.scrollTo(0,0);
        //可能由于资源的回收利用，如果这里不重新获取资源状态，在进入这个界面的时候会出现两个第一张。
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            pageNum=getArguments().getInt(ARG_PAGE);
        }
        Picasso.with(getContext()).load("https://dummyimage.com/1600x900/000/fff.png&text="+String.valueOf(pageNum+1))
                .error(R.drawable.placeholder)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .placeholder(R.drawable.placeholder)
                .into(mImage);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mScrollView=(ScrollView)getActivity().findViewById(R.id.home_scroll_view);
        mScrollView.post(new Runnable() {
            public void run() {
                mScrollView.scrollTo(0, 0);
            }
        });
        //mScrollView.scrollTo(0,0);
        super.onViewCreated(view, savedInstanceState);


    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
