package com.udbhava;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Apply extends AppCompatActivity {
    FirebaseAuth mAuth;
    String TAG="Auth";
    DatabaseReference mRed;


    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_apply);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRed= FirebaseDatabase.getInstance().getReference().child("ui");


        final EditText meditetxt = (EditText) findViewById(R.id.passord);
        final Button button = (Button) findViewById(R.id.applyfor);
        final Button button3 = (Button) findViewById(R.id.submit);
        mRed.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressbarcamp);
                progressBar.setVisibility(View.GONE);
                meditetxt.setVisibility(View.VISIBLE);
                  final String pass =(String) dataSnapshot.child("campass").getValue();
                final String pass1 =(String) dataSnapshot.child("cmpapply").getValue();
                //Toast.makeText(getApplicationContext(),pass,Toast.LENGTH_LONG).show();

                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressbarcamp);
                        //progressBar.setVisibility(View.VISIBLE);
                        if(meditetxt.getText().toString().equals(pass)){
                            startActivity(new Intent(Apply.this,Campusamb.class));
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(),"Invlid",Toast.LENGTH_LONG).show();
                        }

                    }
                });
               button.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent intent=new Intent(Apply.this,Web.class);
                       intent.putExtra("facebook",pass1);
                       startActivity(intent);
                   }
               });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
               // updateUI(user);
                // [END_EXCLUDE]
            }
        };

    }
    // [START on_start_add_listener]
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(Apply.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if(task.isComplete()){
                            startActivity(new Intent(Apply.this,Campusamb.class));
                            finish();

                        }

                        // [START_EXCLUDE]

                        ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressbarcamp);
                        progressBar.setVisibility(View.GONE);  // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }
    private boolean validateForm() {
        boolean valid = true;

        EditText meditetxt = (EditText) findViewById(R.id.passord);

        String password = meditetxt.getText().toString();
        if (TextUtils.isEmpty(password)) {
            meditetxt.setError("Required.");
            valid = false;
        } else {
            meditetxt.setError(null);
        }

        return valid;
    }


}