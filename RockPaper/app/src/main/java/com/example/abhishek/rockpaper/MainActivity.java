package com.example.abhishek.rockpaper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference gameRef = rootRef.child("game");

    // Get UI Elements.

    TextView textView;
    Button rock, paper, scissor;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        rock = (Button) findViewById(R.id.rock);
        paper = (Button) findViewById(R.id.paper);
        scissor = (Button) findViewById(R.id.scissor);
        image = (ImageView) findViewById(R.id.image);

        // rootRef.setValue("Abhishek");
        // rootRef.child("Users").setValue("sam");
        // rootRef.child("Users").child("01").child("Email").setValue("some@cool.com");
        // rootRef.child("Users").child("01").child("Name").setValue("Sam");

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameRef.setValue("rock");
            }
        });
        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameRef.setValue("paper");
            }
        });
        scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameRef.setValue("scissor");
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        gameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue().toString();
                textView.setText(text);
                int identifier = getResources().getIdentifier(text, "drawable", getPackageName());
                Log.i("identifier", "identifier"+identifier );
                image.setImageResource(identifier);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("TAG", "Something is missing here");
            }
        });

    }
}
