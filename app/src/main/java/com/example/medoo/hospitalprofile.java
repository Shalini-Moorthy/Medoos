package com.example.medoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.widget.ImageView;
import android.widget.ImageButton;
public class hospitalprofile extends AppCompatActivity {
TextView hname,hvillage,hbuildingnumber,hstreet,hspecification,htiming;
String s;
Button btn;
static Long number;
String villagename,buildingnumber,specification,timing,street;
DatabaseReference reff;
//for message,email,call,repport sending
ImageView message;
ImageView iv;
ImageView mail;
ImageView reportsendbtn;
ImageView locatiobtn;
private static final int REQUEST_CALL=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitalprofile);
        s=getIntent().getStringExtra("hospitalname").toString();
        hname=findViewById(R.id.hospitalname);
        hname.setText(s);
       hvillage=findViewById(R.id.villagename);
        hbuildingnumber=findViewById(R.id.hospitalbuildingnumber);
        hspecification=findViewById(R.id.hospitalspecification);
        hstreet=findViewById(R.id.hospitalstreet);
        htiming=findViewById(R.id.hospitaltiming);
      reff=FirebaseDatabase.getInstance().getReference().child("member");
        Query query=reff.orderByChild("hospitalname").equalTo(s);
        //for offline access
        reff.keepSynced(true);
        //
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    specification=snapshot1.getValue(member.class).getSpecification();
                    buildingnumber=snapshot1.getValue(member.class).getBuildingnumber();
                    street=snapshot1.getValue(member.class).getStreet();
                    villagename=snapshot1.getValue(member.class).getVillage();
                    timing=snapshot1.getValue(member.class).getTiming();
                    number=snapshot1.getValue(member.class).getNumber();

                }
                hspecification.setText(specification);
                hbuildingnumber.setText(buildingnumber);
                hstreet.setText(street);
                htiming.setText(timing);
                hvillage.setText(villagename);
                iv=findViewById(R.id.callbutton);
                reportsendbtn=findViewById(R.id.reportsendbutton);
                message=findViewById(R.id.msgbutton);
                mail=findViewById(R.id.emailbutton);
                locatiobtn=findViewById(R.id.loactionbutton);
                message.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                           String num=number.toString();
                           Intent intent=new Intent(hospitalprofile.this,msgapp.class);
                           intent.putExtra("number",num.toString());
                           startActivity(intent);

                    }
                });
                mail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String num=number.toString();
                        Intent intent=new Intent(hospitalprofile.this,mail.class);


                        startActivity(intent);

                    }
                });

                reportsendbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent=new Intent(hospitalprofile.this,sendreport.class);
                        startActivity(intent);

                    }
                });
                locatiobtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent=new Intent(hospitalprofile.this,confirmlocation.class);
                        intent.putExtra("hospitalname",s);
                        startActivity(intent);

                    }
                });

                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        opencall();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(hospitalprofile.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void opencall() {
         String num=number.toString();
        if(num.trim().length()>0){
            if(ContextCompat.checkSelfPermission(hospitalprofile.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(hospitalprofile.this,new String[] {Manifest.permission.CALL_PHONE},REQUEST_CALL);

            }else{
                String dial="tel:" +number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
        else{
            Toast.makeText(this,"enter phone number",Toast.LENGTH_LONG).show();


        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==REQUEST_CALL){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                opencall();
            }
            else{
                Toast.makeText(this,"PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}