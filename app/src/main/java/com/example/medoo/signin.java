package com.example.medoo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import  android.content.Intent;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import android.net.Uri;

public class signin extends AppCompatActivity {
    private SignInButton signin;
    private GoogleSignInClient mGoogleSignInClient;
    private  String TAG ="signin";
    private FirebaseAuth mAuth;

    private  int RC_SIGN_IN=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        signin=findViewById(R.id.signin);
        mAuth = FirebaseAuth.getInstance();


        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinmethod();
            }
        });

    }
    private  void signinmethod(){
        Intent intent=mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task =GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private  void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try{
            GoogleSignInAccount acc= completedTask.getResult(ApiException.class);
            Toast.makeText(signin.this,"SIGNED IN SUCESSFULLY",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);

        }catch (ApiException e){
            Toast.makeText(signin.this,"SIGN IN FAILED",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }
    private  void FirebaseGoogleAuth(GoogleSignInAccount acct){
        AuthCredential authCredential= GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser user=mAuth.getCurrentUser();
                    updateUI(user);
                }
                else{
                    Toast.makeText(signin.this,"Failed",Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }}
        });
    }
    private  void updateUI(FirebaseUser fuser){

        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account!=null){
            String personName=account.getDisplayName();
            String personGivenName= account.getGivenName();
            String personFamilyName=account.getFamilyName();
            String personEmail=account.getEmail();
            String personId=account.getId();
            Uri personPhoto=account.getPhotoUrl();
            Intent intent=new Intent(signin.this,MainActivity.class);
            startActivity(intent);
        }
    }
}



