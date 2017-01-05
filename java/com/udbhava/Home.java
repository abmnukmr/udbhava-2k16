package com.udbhava;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pushbots.push.Pushbots;
import com.q42.android.scrollingimageview.ScrollingImageView;
import com.skyfishjy.library.RippleBackground;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,View.OnClickListener{
    Button btn1,btn2,btn3,btn4,btn5;

    private FirebaseAuth mAuth;
    public DatabaseReference mData;
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    // Button btn12=(Button)findViewById(R.id.pay);
        //btn12.setVisibility(View.GONE);
       // ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressbarhome);
       // FirebaseAuth mAuth=FirebaseAuth.getInstance().getCurrentUser();

        mAuth = FirebaseAuth.getInstance();


        setContentView(R.layout.activity_home);
        Pushbots.sharedInstance().init(this);
        Pushbots.sharedInstance().setAlias("Name= "+mAuth.getCurrentUser().getDisplayName()+"  " +"email=  "+mAuth.getCurrentUser().getEmail());

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("438979550648-ut9a27lc260o3feinnsof6rq1f8qjqcb.apps.googleusercontent.com")
                .requestEmail()
                .build();
        // [END config_signin]

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,  this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
         mData=FirebaseDatabase.getInstance().getReference().child("ui").child("payment");
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressbar_home);
                progressBar.setVisibility(View.GONE);
                final String payurl= (String) dataSnapshot.getValue();
                Button btn12=(Button)findViewById(R.id.pay);

                //ImageButton btn12=(ImageButton) findViewById(R.id.pay);
                btn12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(Home.this,Web.class);
                        intent.putExtra("facebook",payurl);
                        startActivity(intent);

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       /* Button btn12=(Button)findViewById(R.id.pay);

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Web.class);
                 intent.putExtra("facebook","https://google.com");
                startActivity(intent);
            }});*/

                // [START initialize_auth]
        // [END initialize_auth]

        // [START auth_state_listener]
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
                // [END_EXCLUDE]
            }
        };

        final FirebaseUser mRef= FirebaseAuth.getInstance().getCurrentUser();

        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
        rippleBackground.startRippleAnimation();
        TextView textView=(TextView)findViewById(R.id.profilename);
        ScrollingImageView scrollingBackground = (ScrollingImageView)findViewById(R.id.scrolling_background);
        CircleImageView image11 = (CircleImageView)findViewById(R.id.imageprof);
        ImageButton img1=(ImageButton)findViewById(R.id.btnq0);
        ImageButton img2=(ImageButton)findViewById(R.id.btnq1);
        ImageButton img3=(ImageButton)findViewById(R.id.btnq2);
        ImageButton img4=(ImageButton)findViewById(R.id.btnq3);

        ImageButton img5=(ImageButton)findViewById(R.id.btnq4);
         ImageButton img55=(ImageButton)findViewById(R.id.btnq5);
        ImageButton img6=(ImageButton)findViewById(R.id.logout);
        Button vgy=(Button)findViewById(R.id.injobutton);
        vgy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Int.class));
                finish();
            }
        });

        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                startActivity(new Intent(Home.this,Loginpage.class));
                finish();
            }
        });


        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,Web.class);
                String url="https://www.facebook.com/Udbhava2016/";
                intent.putExtra("facebook",url);
                startActivity(intent);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,Web.class);
                String url="https://twitter.com/Udbhava_2016";
                intent.putExtra("facebook",url);
                startActivity(intent);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,Web.class);
                String url="https://www.youtube.com/channel/UCIC-jhIbxXkcDxyQtJ3OKkQ";
                intent.putExtra("facebook",url);
                startActivity(intent);
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,Apply.class);

                //String url="https://www.facebook.com/Udbhava2016/";
                //intent.putExtra("facebook",url);
                startActivity(intent);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,Map.class);
               // String url="https://www.facebook.com/Udbhava2016/";
                //intent.putExtra("facebook",url);
                startActivity(intent);
            }
        });

        img55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,Contact.class);
               // String url="https://www.facebook.com/Udbhava2016/";
                //intent.putExtra("facebook",url);
                startActivity(intent);
            }
        });



       // final FirebaseUser mRef= FirebaseAuth.getInstance().getCurrentUser();
        String emailid=mRef.getEmail();
        String name=mRef.getDisplayName();
       //char u = name!=null ? name.charAt(0) : null;
        textView.setText("Welcome!  "+name);
        String urut= mRef.getPhotoUrl() != null ? mRef.getPhotoUrl().toString() : null;
        if (urut==null )
        {

            char u=name.charAt(0);
            //String n=String.valueOf(U);
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color1 = generator.getRandomColor();
           // char first=image.charAt(0);



            TextDrawable drawable = TextDrawable.builder()
                    .beginConfig()
                    .width(60)  // width in px
                    .height(60) // height in px
                    .endConfig()
                    .buildRound(String.valueOf(u).toUpperCase()+"", color1);
            CircleImageView image1 = (CircleImageView)findViewById(R.id.imageprof);
            image1.setImageDrawable(drawable);


        }
        else {
        Picasso.with(getApplicationContext()).load(urut)
                .placeholder(R.mipmap.udbhavalogo11).error(R.drawable.bacfk)
                .into(image11);
        }


        scrollingBackground.start();
        Button btn1=(Button)findViewById(R.id.eventsbutton);
        Button btn2=(Button)findViewById(R.id.sponsorbutton);
        Button btn3=(Button)findViewById(R.id.workshopbutton);
        Button btn4=(Button)findViewById(R.id.talksbutton);
        Button btn5=(Button)findViewById(R.id.highlightsbutton);
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Chatroom.class));
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Event.class));

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Sponsers.class));

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Workshop.class));

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Techtalks.class));

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Highmights.class));

            }
        });

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        

       myRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {

               String img1 = (String) dataSnapshot.child("image1").getValue();
               // scrollingBackground.stop();

               String img2 = (String) dataSnapshot.child("image2").getValue();
               //Ion.with(scrlimg2).load(img2);
               String img3 = (String) dataSnapshot.child("image3").getValue();
               // Ion.with(scrlimg3).load(img3);
               String img4 = (String) dataSnapshot.child("image4").getValue();
               //Ion.with(scrlimg4).load(img4);
               String img5 = (String) dataSnapshot.child("image5").getValue();
               //Ion.with(scrlimg5).load(img5);


           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }


       });
    }
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
    // [END on_stop_remove_listener]

    // [START onactivityresult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // [START_EXCLUDE]
                // [END_EXCLUDE]
            }
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
      //  showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                       // startActivity(new Intent(Loginpage.this,Home.class));
                        finish();
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                          //  Toast.makeText(Loginpage.this, "Authentication failed.",
                                   // Toast.LENGTH_SHORT).show();
                        }
                        // [START_EXCLUDE]
                        // [END_EXCLUDE]
                    }
                });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.eventsbutton :
                startActivity(new Intent(Home.this,Event.class));
            case R.id.workshopbutton:

                startActivity(new Intent(Home.this,Workshop.class));
            case R.id.talksbutton:
                startActivity(new Intent(Home.this,Techtalks.class));
            case R.id.sponsorbutton:
                startActivity(new Intent(Home.this,Sponsers.class));
            case R.id.highlightsbutton:
                startActivity(new Intent(Home.this,Highmights.class));




        }

    }
    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
       Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                    }
                });
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
