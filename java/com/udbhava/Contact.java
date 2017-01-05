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
import android.widget.LinearLayout;
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
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Contact extends AppCompatActivity {
    private FirebaseRecyclerAdapter<Post, ChatHolder> mRecycleViewAdapter;

    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRef = FirebaseDatabase.getInstance().getReference().child("zcontact");
        RecyclerView rcl =(RecyclerView)findViewById(R.id.contactmapping);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(false);
        // rcl.scrollToPosition(-1);
        rcl.setHasFixedSize(false);
        rcl.setLayoutManager(manager);


        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressbarcivill_contact);
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
        mRecycleViewAdapter = new FirebaseRecyclerAdapter<Post, ChatHolder>(Post.class, R.layout.contacteliment, ChatHolder.class, mRef) {
            @Override
            protected void populateViewHolder(ChatHolder chatHolder, Post post, int i) {
                chatHolder.setName(post.getName());
                chatHolder.setDesig(post.getDesig());
                chatHolder.setEmail(post.getEmail());
                chatHolder.setPhone(post.getPhone());
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
        String phone;
        String email;
        String image;
        String desig;




        public Post()
        {

        }

        public Post(String name, String email, String phone,String desig, String image) {
            this.name = name;
            this.desig=desig;
            this.phone =phone;
            this.email =email;
            this.image = image;

        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getImage() {

            return image;
        }

        public String getDesig() {
            return desig;
        }

       }
    public static class ChatHolder extends RecyclerView.ViewHolder {
        View mView;

        public ChatHolder(View itemView) {
            super(itemView);
            mView = itemView;
            RelativeLayout messageContainer = (RelativeLayout) mView.findViewById(R.id.container);
            CardView card =(CardView)mView.findViewById(R.id.card);
           // RelativeLayout linearLayout=(RelativeLayout)mView.findViewById(R.id.lnr);
            // ImageView imageView   =(ImageView)mView.findViewById(R.id.teamimage);

            View view             = (View)mView.findViewById(R.id.border);

        }

        public void setName(String name) {
            if (name.equals("")){
                TextView textView = (TextView) mView.findViewById(R.id.name_contact);
                textView.setVisibility(View.GONE);
            }
            else {
                TextView textView = (TextView) mView.findViewById(R.id.name_contact);
                textView.setText(name);
                //textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }

        public void setEmail(String email) {
            if (email.equals("")){
                TextView textView = (TextView) mView.findViewById(R.id.email_contact);
                textView.setVisibility(View.GONE);
            }
            else {
                TextView textView = (TextView) mView.findViewById(R.id.email_contact);
                textView.setText(email);
                //textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }


        public void setDesig(String desig) {
            if(desig.equals("")) {
                TextView textView2 = (TextView) mView.findViewById(R.id.desig);
                textView2.setVisibility(View.GONE);
            }else {
                TextView textView2 = (TextView) mView.findViewById(R.id.desig);

                //textView2.setMovementMethod(LinkMovementMethod.getInstance());
                textView2.setText(desig);}
        }
        public void setPhone(String phone) {
            if(phone.equals("")){
                TextView  textView3        =(TextView)mView.findViewById(R.id.phone_contact);
                textView3.setVisibility(View.GONE);
            }else {
                TextView  textView3        =(TextView)mView.findViewById(R.id.phone_contact);
                String tpr=phone.toString();
                //textView3.setMovementMethod(LinkMovementMethod.getInstance());
                textView3.setText(phone);
            }
        }
        public void setImage(String url) {
            if(url.equals("")) {
                de.hdodenhof.circleimageview.CircleImageView imageView=(de.hdodenhof.circleimageview.CircleImageView)mView.findViewById(R.id.photo_contact);
                imageView.setVisibility(View.GONE);
            }else {
                de.hdodenhof.circleimageview.CircleImageView imageView=(de.hdodenhof.circleimageview.CircleImageView)mView.findViewById(R.id.photo_contact);
                Picasso.with(mView.getContext()).load(url)
                        .placeholder(R.mipmap.udbhavalogo11).error(R.drawable.mem)
                        .into(imageView);
            }
        }




    }


}
