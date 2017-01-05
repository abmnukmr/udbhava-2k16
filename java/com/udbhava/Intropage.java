package com.udbhava;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.redbooth.WelcomeCoordinatorLayout;

public class Intropage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intropage);
        final WelcomeCoordinatorLayout coordinatorLayout
                = (WelcomeCoordinatorLayout)findViewById(R.id.coordinator);
        coordinatorLayout.addPage(R.layout.intro1,R.layout.intro2,R.layout.intro3,R.layout.intro4);
        TextView textView=(TextView)findViewById(R.id.done);
        TextView textView1=(TextView)findViewById(R.id.skip);
        textView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            startActivity(new Intent(Intropage.this, Loginpage.class));
                                            finish();
                                        }
                                    });


        }
}

