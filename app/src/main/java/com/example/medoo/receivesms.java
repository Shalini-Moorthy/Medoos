package com.example.medoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class receivesms extends AppCompatActivity {
    private static final int permission=0;
    private  static final String SMS_RECEIVED="android.provider.Telephony.SMS_RECEIVED";
    TextView messagetv,numbertv;

    MyReceiver receiver=new MyReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context,intent);
            messagetv.setText(msg);
            numbertv.setText(phoneNo);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver,new IntentFilter(SMS_RECEIVED));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receivesms);
        //check if permission not granted
        messagetv=findViewById(R.id.msg);
        numbertv=findViewById(R.id.num);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.RECEIVE_SMS)){
                //doo nothing as user denied8-+
            }
            else{
                //ask permission
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},permission);
            }
        }

    }//oncreate
    //after getting the result of permission requests will be passed through method

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch(requestCode){
            case permission:
            {
                //check whether the length of grantresults is greater than 0 and is equal equal to PERMISSION_GRANTED
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //now broadcast receiver will work in background
                    Toast.makeText(this,"Thank you for permitting",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this,"We need permission to receive msg",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}