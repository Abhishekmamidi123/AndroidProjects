package com.example.abhishek.volleythree;

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

        Button myButton = (Button) findViewById(R.id.buttonapi);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myURL, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {

                                    // Get description.
                                    String weather = response.getString("weather");
                                    JSONArray weatherArray = new JSONArray(weather);

                                    String description = weatherArray.getJSONObject(0).getString("description");
                                    Log.i("DESCRIPTION", "DESCRIPTION: "+description);

                                    // Get temperature.
                                    JSONObject main = response.getJSONObject("main");
                                    String temperature = main.getString("temp");
                                    Log.i("temperature", "temperature"+temperature);

                                    float temp = Float.parseFloat(temperature);
                                    temp = temp - 273;

                                    Log.i("Temp in Centigrade", "Temp"+temp);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("ERROR", "ERROR: "+error);
                            }
                        }
                );

                MySingelton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);

            }
        });

    }
}
