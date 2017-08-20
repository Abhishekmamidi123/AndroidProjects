package com.example.abhishek.myweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText city;   // getCity
    TextView result;
    //http://api.openweathermap.org/data/2.5/weather?q=Amalapuram,uk&appid=783cc4f32993ed68d093db20a949510b
    String baseURL = "http://api.openweathermap.org/data/2.5/weather?q=";
    String API = ",uk&appid=783cc4f32993ed68d093db20a949510b";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        city = (EditText) findViewById(R.id.getCity);
        result = (TextView) findViewById(R.id.result);
        final ImageView weatherCondition = (ImageView) findViewById(R.id.condition);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myURL = baseURL + city.getText().toString() + API;
                Log.i("URL", "URL: "+myURL);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myURL, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Log.i("JSON", "JSON: " + jsonObject);

                                try {
                                    String info = jsonObject.getString("weather");
                                    JSONArray ar = new JSONArray(info);
                                    for(int i=0; i<ar.length(); i++){
                                        JSONObject parObj = ar.getJSONObject(i);
                                        String myWeather = parObj.getString("main");
                                        result.setText(myWeather);

                                        if(myWeather.equals("Clouds")){
                                            weatherCondition.setImageResource(R.drawable.clouds);
                                        }
                                        else if(myWeather.equals("Clear")){
                                            weatherCondition.setImageResource(R.drawable.clear);
                                        }
                                        else if(myWeather.equals("Haze")){
                                            weatherCondition.setImageResource(R.drawable.haze);
                                        }
                                        else if(myWeather.equals("Rain")){
                                            weatherCondition.setImageResource(R.drawable.rain);
                                        }
                                        else if(myWeather.equals("Mist")){
                                            weatherCondition.setImageResource(R.drawable.mist);
                                        }
                                        else{
                                            weatherCondition.setImageResource(R.drawable.weather);
                                        }
                                    }
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
                MySingelton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);
            }
        });
    }
}
