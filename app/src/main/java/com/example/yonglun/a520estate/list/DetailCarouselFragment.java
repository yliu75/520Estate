package com.example.yonglun.a520estate.list;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yonglun.a520estate.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailCarouselFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailCarouselFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailCarouselFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String ARG_PAGE = "page";
    private static int pageNum=0;


    ImageView mImage;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public static DetailCarouselFragment create(int pageNumber) {
        DetailCarouselFragment fragment = new DetailCarouselFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public DetailCarouselFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailCarouselFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailCarouselFragment newInstance(String param1, String param2) {
        DetailCarouselFragment fragment = new DetailCarouselFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            pageNum=getArguments().getInt(ARG_PAGE);
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_detail_carousel, container, false);
        // Inflate the layout for this fragment
        mImage=(ImageView)rootView.findViewById(R.id.DetailCarouselPagerImageView);
        //可能由于资源的回收利用，如果这里不重新获取资源状态，在进入这个界面的时候会出现两个第一张。
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            pageNum=getArguments().getInt(ARG_PAGE);
        }
        Picasso.with(getContext()).load("https://dummyimage.com/1600x1200/000/fff.png&text="+String.valueOf(pageNum+1))
                .error(R.drawable.placeholder)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .placeholder(R.drawable.placeholder)
                .into(mImage);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
