package com.example.abhishek.mystack;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    // <What should I fetch, what should I do in mean time, result>
    // Like a thread, runs without disturbing the app
    // Async task
    public class SetUpFetch extends AsyncTask< String, Void, String >{

        // We are doing all these things in background.
        @Override
        protected String doInBackground(String... strings) {

            URL url;
            String page="";
            HttpURLConnection urlConnection = null;

            try{
                // storing url from the strings
                url = new URL(strings[0]);
                // open the connection (Like opening the Browser)
                urlConnection = (HttpURLConnection) url.openConnection();
                // getting input stream(Like clicking the enter)
                InputStream input = urlConnection.getInputStream();
                // Read the inputstream
                InputStreamReader read = new InputStreamReader(input);

                // read the data bit and store in data
                int data = read.read();
                // run while loop till we encounter -1
                while(data!=-1){
                    char storeData = (char) data;
                    page += storeData;
                    data = read.read();
                }

                // return the page
                return page;
            }
            catch (Exception e){
                e.printStackTrace();
                return "Cannot fetch web page";
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetUpFetch request = new SetUpFetch();

        String page=null;

        try{
            page = request.execute("http://www.HiteshChoudhary.com/").get();
            Log.i("Done", "Doneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        }
        catch(Exception e){
            e.printStackTrace();
        }

        Log.i("Stackoverflow: ", page);
    }
}
