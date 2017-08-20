package com.example.abhishek.starcolour;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class secondActivity extends AppCompatActivity {

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        // Getting information from the other screen.
        Intent intent = getIntent();
        Toast.makeText(this, intent.getStringExtra("name"), Toast.LENGTH_SHORT).show();

        String colour = intent.getStringExtra("name");
        //imageView.setImageResource(R.drawable.red);
        imageView.setImageResource(getImageId(this, colour));

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(colour+" star");

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
