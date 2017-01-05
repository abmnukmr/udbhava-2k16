package com.udbhava;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);

        FirebaseUser auth=FirebaseAuth.getInstance().getCurrentUser();
        final FirebaseUser mRef= FirebaseAuth.getInstance().getCurrentUser();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SystemClock.sleep(100);
                final FirebaseUser mRef = FirebaseAuth.getInstance().getCurrentUser();
                if(mRef==null){
                    startActivity(new Intent(Welcome.this,Home.class));
                    finish();

                }
                else {

                    startActivity(new Intent(Welcome.this, Home.class));
                    finish();
                }

            }
        },3000);



          }
}
