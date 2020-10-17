package com.example.medoo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;
public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        TextView name=findViewById(R.id.name);
        TextView email=findViewById(R.id.email);

        String personName=account.getDisplayName();
        String personEmail=account.getEmail();
        Uri personPhoto=account.getPhotoUrl();
        name.setText(personName);
        email.setText(personEmail);



    }
}