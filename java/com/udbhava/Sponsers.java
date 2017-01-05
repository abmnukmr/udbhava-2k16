package com.udbhava;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.koushikdutta.ion.Ion;

public class Sponsers extends AppCompatActivity {
    private FirebaseRecyclerAdapter<Post, ChatHolder> mRecycleViewAdapter;

    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRef = FirebaseDatabase.getInstance().getReference().child("sponser");
        RecyclerView rcl =(RecyclerView)findViewById(R.id.sponser);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(false);
        // rcl.scrollToPosition(-1);
        rcl.setHasFixedSize(false);
        rcl.setLayoutManager(manager);


        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressbarcivillspon);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mRecycleViewAdapter = new FirebaseRecyclerAdapter<Post, ChatHolder>(Post.class, R.layout.ebt, ChatHolder.class, mRef) {
            @Override
            protected void populateViewHolder(ChatHolder chatHolder, Post post, int i) {
                chatHolder.setName(post.getName());
                chatHolder.setContact(post.getContact());
                chatHolder.setAbout(post.getAbout());
                chatHolder.setImage(post.getImage());

            }


        };
        rcl.setAdapter(mRecycleViewAdapter);















    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public static class Post {
        String name;
        String contact;
        String about;
        String image;




        public Post()
        {

        }

        public Post(String name, String contact, String about, String image) {
            this.name = name;
            this.contact =contact;
            this.about = about;
            this.image = image;

        }

        public String getAbout() {
            return about;
        }

        public String getContact() {
            return contact;
        }

        public String getImage() {
            return image;
        }

        public String getName() {
            return name;
        }
    }
    public static class ChatHolder extends RecyclerView.ViewHolder {
        View mView;

        public ChatHolder(View itemView) {
            super(itemView);
            mView = itemView;
            RelativeLayout messageContainer = (RelativeLayout) mView.findViewById(R.id.container);
            CardView card =(CardView)mView.findViewById(R.id.card);
            RelativeLayout linearLayout=(RelativeLayout)mView.findViewById(R.id.lnr);
            // ImageView imageView   =(ImageView)mView.findViewById(R.id.teamimage);

            View view             = (View)mView.findViewById(R.id.border);

        }
        public void setName(String name) {
            if (name.equals("")){
                TextView textView = (TextView) mView.findViewById(R.id.name);
                textView.setVisibility(View.GONE);
            }
            else {
                TextView textView = (TextView) mView.findViewById(R.id.name);
                textView.setText(name);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }

        public void setContact(String desig) {
            if(desig.equals("")) {
                TextView textView2 = (TextView) mView.findViewById(R.id.desig);
                textView2.setVisibility(View.GONE);
            }else {
                TextView textView2 = (TextView) mView.findViewById(R.id.desig);

                textView2.setMovementMethod(LinkMovementMethod.getInstance());
                textView2.setText(desig);}
        }
        public void setAbout(String about) {
            if(about.equals("")){
                TextView  textView3        =(TextView)mView.findViewById(R.id.about);
                textView3.setVisibility(View.GONE);
            }else {
                TextView  textView3        =(TextView)mView.findViewById(R.id.about);

                textView3.setMovementMethod(LinkMovementMethod.getInstance());
                textView3.setText(about);
            }
        }
        public void setImage(String url) {
            if(url.equals("")) {
                ImageView imageView = (ImageView) mView.findViewById(R.id.teamimage);
                imageView.setVisibility(View.GONE);
            }else {
                ImageView imageView = (ImageView) mView.findViewById(R.id.teamimage);

                Ion.with(imageView).placeholder(R.mipmap.udbhavalogo11).load(url);

            }
        }





    }


}
