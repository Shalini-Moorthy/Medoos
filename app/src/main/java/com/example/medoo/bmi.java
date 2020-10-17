package com.example.medoo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

public class bmi extends AppCompatActivity {
EditText height,weight;
TextView result1,result2;
Button calculate;
String h,w,rnumberstring;
Float hnumber,wnumber;
Float rnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);


    }
}