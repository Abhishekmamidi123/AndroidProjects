package com.example.abhishek.firebaselogin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    EditText email;
    EditText password;
    Button button;
    private FirebaseAuth mAuth;
    // Make sure to check email and password are not empty and null
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Toast.makeText(this, "Already In", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
    }

    public void OnRegister(View view){
        // .isEmpty()
        final String myEmail = email.getText().toString();
        final String myPass = password.getText().toString();

        if(TextUtils.isEmpty(myEmail) || myEmail.length()==0 || myEmail.equals("") || myEmail == null) {
            email.setError("This field cannot be empty");
            return;
        }
        if(TextUtils.isEmpty(myPass) || myPass.length()==0 || myPass.equals("") || myPass == null) {
            password.setError("Please fill this field");
            return;
        }
        mAuth.createUserWithEmailAndPassword(myEmail, myPass)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("TAG", "signInWithEmail:success");
                            // FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.i("TAG", "signInWithEmail:failure", task.getException());
//                            Toast.makeText(MainActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
//                            email.setError("Please enter a valid user email ID");
//                            return;
                        }

                        // ...
                    }
                });
    }

    public void OnLogin(View view){
        final String myEmail = email.getText().toString();
        final String myPass = password.getText().toString();

        if(TextUtils.isEmpty(myEmail) || myEmail.length()==0 || myEmail.equals("") || myEmail == null) {
            email.setError("This field cannot be empty");
            return;
        }
        if(TextUtils.isEmpty(myPass) || myPass.length()==0 || myPass.equals("") || myPass == null) {
            password.setError("Please fill this field");
            return;
        }

        mAuth.signInWithEmailAndPassword(myEmail, myPass)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userID = user.getUid().toString();
                            Toast.makeText(MainActivity.this, "Auth Success", Toast.LENGTH_SHORT).show();
                            Log.i("USER", "USER"+user.toString());
                            Log.i("UserID", "UseID"+userID);
                        } else {
                            Toast.makeText(MainActivity.this, "Auth Failure", Toast.LENGTH_SHORT).show();
//                            email.setError("Please enter a valid user email ID");
//                            return;
                        }
                    }
                });
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "SIGNOUT", Toast.LENGTH_SHORT).show();
    }

}
