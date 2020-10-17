package com.example.medoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.net.Uri;
public class mail extends AppCompatActivity {
EditText content,subject;
Button sendemail;
String to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        content=findViewById(R.id.content);
        sendemail=findViewById(R.id.sendemail);
        subject=findViewById(R.id.subject);

      to=("i8it050@sairamtap.edu.in");

        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] recepients=to.split(",");
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL,recepients);
                intent.putExtra(Intent.EXTRA_SUBJECT,subject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,content.getText().toString());
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent,"Choose an email client"));
            }
        });
    }
}