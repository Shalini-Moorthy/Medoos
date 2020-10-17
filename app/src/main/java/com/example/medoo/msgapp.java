package com.example.medoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class msgapp extends AppCompatActivity {
    private EditText message;
    Button send;
String s,s1,s2,s3,s4,s5,s6,severity,duration,typeofissue,displaycontent,finalmsg;
TextView automsg;
DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msgapp);
        s=getIntent().getStringExtra("number").toString();
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
        message=findViewById(R.id.message);
        send=findViewById(R.id.send);
        automsg=findViewById(R.id.automsg);
        GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        final String personName=account.getDisplayName();
        final String personEmail=account.getEmail();
        reff= FirebaseDatabase.getInstance().getReference().child("reportquestions");
        Query query=reff.orderByChild("mail").equalTo(personEmail);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    duration=snapshot1.getValue(reportquestions.class).getDuration();
                    severity=snapshot1.getValue(reportquestions.class).getSeverity();
                    typeofissue=snapshot1.getValue(reportquestions.class).getTypeofissue();

                }
                s1="Your name: "+ personName;
                s2="Email: "+personEmail;
                s3="Type of issue:"+typeofissue;
                s4="Duration of the issue: "+duration;
                s5="Severity: "+severity;

                displaycontent= String.format("%s\n\n%s\n\n%s\n\n%s\n\n%s",s1,s2,s3,s4,s5,s6);
                automsg.setText(displaycontent);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                        sendsms();
                    }
                    else{
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1
                        );
                    }
                }
            }
        });
    }
    private void sendsms(){
        finalmsg=displaycontent+"\n"+message.getText().toString().trim();
        String phoneno=s.toString();
        String sms=finalmsg.toString();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneno, null, sms, null, null);
            Toast.makeText(this, finalmsg, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show();
        }
    }
}