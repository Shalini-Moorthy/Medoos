package com.example.medoo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
public class questions extends AppCompatActivity {
RadioGroup Typeofissue,Durationofissue,Severityofissue;
RadioButton radioButton1,radioButton2,radioButton3;
DatabaseReference reff;
reportquestions ques;
String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Typeofissue=findViewById(R.id.typeofissueradio);
        Durationofissue=findViewById(R.id.durationofissueradio);
        Severityofissue=findViewById(R.id.severityofissueradio);
        Button finishbtn=findViewById(R.id.finishbutton);
        //creating object for reportquestions class
        ques=new reportquestions();
        //getting db reference
        reff= FirebaseDatabase.getInstance().getReference().child("reportquestions");
        //for offline access
        reff.keepSynced(true);
        //for getting email of user
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        mail=account.getEmail();
        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioIdfortype=Typeofissue.getCheckedRadioButtonId();
                radioButton1=findViewById(radioIdfortype);
                int radioIdforduration=Durationofissue.getCheckedRadioButtonId();
                radioButton2=findViewById(radioIdforduration);
                int radioIdforseverity=Severityofissue.getCheckedRadioButtonId();
                radioButton3=findViewById(radioIdforseverity);

                Toast.makeText(questions.this,radioButton1.getText()+" "+radioButton2.getText(),Toast.LENGTH_SHORT).show();
                ques.setTypeofissue(radioButton1.getText().toString());
                ques.setSeverity(radioButton3.getText().toString());
                ques.setDuration(radioButton2.getText().toString());
                ques.setMail(mail.toString());
                reff.push().setValue(ques);

                finish();
            }
        });

    }

}