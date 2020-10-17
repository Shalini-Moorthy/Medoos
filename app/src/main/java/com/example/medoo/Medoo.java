package com.example.medoo;
import android.app.Application;
import com.google.firebase.database.FirebaseDatabase;
public class Medoo extends Application {
    public void onCreate(){
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
