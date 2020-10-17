package com.example.medoo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class hospitalsreg extends AppCompatActivity {
EditText hospitalname,buildingnumber,street,city,village,pincode,state,number,specification,timing;
Button submit;
DatabaseReference reff;
member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitalsreg);

        hospitalname=(EditText) findViewById(R.id.hospitalname);
        buildingnumber=(EditText) findViewById(R.id.buildingnumber);
        street=(EditText) findViewById(R.id.street);
        city=(EditText) findViewById(R.id.city);
        state=(EditText) findViewById(R.id.state);
        number=(EditText) findViewById(R.id.number);
        village=(EditText) findViewById(R.id.village);
        pincode=(EditText) findViewById(R.id.pincode);
        specification=(EditText) findViewById(R.id.specification);
        timing =(EditText) findViewById(R.id.timing);
        submit=(Button) findViewById(R.id.submit);
        member =new member();
        reff= FirebaseDatabase.getInstance().getReference().child("member");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long num=Long.parseLong(number.getText().toString().trim());
                int pin=Integer.parseInt(pincode.getText().toString().trim());
                member.setHospitalname(hospitalname.getText().toString().trim());
                member.setBuildingnumber(buildingnumber.getText().toString().trim());
                member.setStreet(street.getText().toString().trim());
                member.setVillage(village.getText().toString().trim());
                member.setCity(city.getText().toString().trim());
                member.setState(state.getText().toString());
                member.setPincode(pin);
                member.setNumber(num);
                member.setTiming(timing.getText().toString().trim());
                member.setSpecification(specification.getText().toString().trim());

                reff.push().setValue(member);
                Toast.makeText(hospitalsreg.this,"Submitted successfully",Toast.LENGTH_LONG).show();
         finish();


            }
        });
    }
}