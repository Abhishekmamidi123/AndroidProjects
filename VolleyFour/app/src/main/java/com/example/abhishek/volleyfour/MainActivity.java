package com.example.abhishek.volleyfour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String myURL = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.buttonapi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAP", "TAPPED");

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myURL, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Log.i("JSON", "JSON: " + jsonObject);

                                try {
                                    String info = jsonObject.getString("weather");
                                    Log.i("INFO", "INFO "+info);

                                    JSONArray ar = new JSONArray(info);

                                    for(int i=0; i<ar.length(); i++){
                                        JSONObject parObj = ar.getJSONObject(i);
                                        Log.i("ID", "ID: "+parObj.getString("id"));
                                        Log.i("MAIN", "MAIN: "+parObj.getString("main"));
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    JSONObject coord = jsonObject.getJSONObject("coord");
                                    Log.i("COOR", "COOR: "+ coord);

                                    // We can do this in many ways.
                                    // String coor = jsonObject.getString("coord");
                                    // JSONObject coord = new JSONObject("coor");

                                    String lon = coord.getString("lon");
                                    String lat = coord.getString("lat");

                                    Log.i("LON", "LON "+lon);
                                    Log.i("LAT", "LAT "+lat);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("Error", "Something went wrong");
                            }
                        }
                );
                Log.i("Tap", "Tapped");
                MySingleton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);

            }
        });
    }
}
