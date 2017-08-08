package com.example.yonglun.a520estate.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yonglun.a520estate.R;
import com.example.yonglun.a520estate.list.HouseInfoDetailActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static android.R.attr.button;


public class home extends Fragment {
    public static home newInstance() {
        home fragment = new home();
        return fragment;
    }
    private Button publishBtn;
    private TextView accessTokenTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        publishBtn = (Button)getActivity().findViewById(R.id.publish_button);
        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PublishApt.class);
                getActivity().startActivityForResult(intent,1);

            }
        });
        accessTokenTV = (TextView)getActivity().findViewById(R.id.accessToken);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url ="https://tengrunmedia.com/Token";


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        accessTokenTV.setText("Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                accessTokenTV.setText("That didn't work!");
            }
        }){
            @Override
            public byte[] getBody() throws AuthFailureError {
                String httpPostBody="grant_type=password&username=t1%40t.com&password=Yon4%24glu";
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

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}