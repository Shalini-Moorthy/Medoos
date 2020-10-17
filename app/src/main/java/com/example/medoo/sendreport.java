package com.example.medoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import android.widget.ImageView;
public class sendreport extends AppCompatActivity {
    String duration,mail,severity,typeofissue;
DatabaseReference ref;
ImageView sendreportbtn;
EditText userphonenumber;
String to,subject,content,s1,s2,s3,s4,s5,s6;
TextView uname,uemail,useverity,uduration,utype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendreport);

        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        final String personName=account.getDisplayName();
        final String personEmail=account.getEmail();
        ref= FirebaseDatabase.getInstance().getReference().child("reportquestions");
        Query query=ref.orderByChild("mail").equalTo(personEmail);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                   duration=snapshot1.getValue(reportquestions.class).getDuration();
                   severity=snapshot1.getValue(reportquestions.class).getSeverity();
                   typeofissue=snapshot1.getValue(reportquestions.class).getTypeofissue();

                }

                uname=findViewById(R.id.uname);
                uemail=findViewById(R.id.uemail);
                utype=findViewById(R.id.utype);
                useverity=findViewById(R.id.useverity);
                uduration=findViewById(R.id.uduration);
                uname.setText(personName);
                uemail.setText(personEmail);
                utype.setText(typeofissue);
                useverity.setText(severity);
                uduration.setText(duration);



                sendreportbtn=findViewById(R.id.reportsendbutton);

                to="i8it050@sairamtap.edu.in";
               subject="report";
               s1="Your name: "+ personName;
               s2="Email: "+personEmail;
               s3="Type of issue:"+typeofissue;
               s4="Duration of the issue: "+duration;
               s5="Severity: "+severity;
               userphonenumber=findViewById(R.id.usernumber);

                sendreportbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s6="Contact number: "+(userphonenumber.getText().toString());
                        content= String.format("%s\n\n%s\n\n%s\n%s\n%s\n%s",s1,s2,s3,s4,s5,s6);
                        String[] recepients=to.split(",");
                        Intent intent=new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_EMAIL,recepients);
                        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
                        intent.putExtra(Intent.EXTRA_TEXT,content);
                        intent.setType("message/rfc822");
                        startActivity(Intent.createChooser(intent,"Choose an email client"));
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(sendreport.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}