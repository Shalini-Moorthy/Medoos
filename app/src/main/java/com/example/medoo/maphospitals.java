package com.example.medoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.content.ActivityNotFoundException;
public class maphospitals extends AppCompatActivity {
Button openmap;
DatabaseReference reff;
String ssource,sdestination;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maphospitals);
        openmap=findViewById(R.id.openmap);
        GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        final String personName=account.getDisplayName();
        final String personEmail=account.getEmail();
        reff=FirebaseDatabase.getInstance().getReference().child("localitonfire");
        Query query=reff.orderByChild("mail").equalTo(personEmail);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    ssource=snapshot1.getValue(locationfire.class).getLocality().toString().trim();


                }
                sdestination="mumbai";
                openmap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if(ssource.equals("") && sdestination.equals("")){
                            Toast.makeText(getApplicationContext(),"Enter location and destination",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            DisplayTack(ssource,sdestination);
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(maphospitals.this, "something went wrong", Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void DisplayTack(String ssource,String sdestination){
        try{
            Uri uri=Uri.parse("http://www.google.co.in/maps/dir/"+ssource + "/" + sdestination);

            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Uri uri=Uri.parse("http://play.google.com/store/apps/details?id=com.google.android.apps,maps");
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }
}