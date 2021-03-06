package com.example.abhishek.volleytwo;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

// Use this file when needed.

/**
 * Created by abhishek on 17/7/17.
 */

public class MySingleton {


    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mcontext;

    private MySingleton(Context context){
        mcontext = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(mcontext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context context){
        if(mInstance == null){
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public void addToRequestQue(Request request){
        requestQueue.add(request);
    }
}