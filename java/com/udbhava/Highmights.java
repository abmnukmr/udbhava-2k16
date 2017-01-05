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

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.koushikdutta.ion.Ion;

import java.util.HashMap;

public class Highmights extends AppCompatActivity{

        private FirebaseRecyclerAdapter<Highmights.Post, Highmights.ChatHolder> mRecycleViewAdapter;
    DatabaseReference mReff;

        private DatabaseReference mRef;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highmights);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mReff=FirebaseDatabase.getInstance().getReference().child("highlights");
            mReff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    SliderLayout mDemoSlider = (SliderLayout)findViewById(R.id.slider);
               /* ImageView scrlimg1 = (ImageView) findViewById(R.id.scrollimage1);
                //ImageView scrlimg2 = (ImageView) findViewById(R.id.scrollimage2);
                //ImageView scrlimg3 = (ImageView) findViewById(R.id.scrollimage3);
                ImageView scrlimg4 = (ImageView) findViewById(R.id.scrollimage4);
                ImageView scrlimg5 = (ImageView) findViewById(R.id.scrollimage5);*/

                    String img1 = (String) dataSnapshot.child("image1").getValue();
                    String txt1 = (String) dataSnapshot.child("text1").getValue();


                    // Ion.with(scrlimg1).load(img1);
                    String img2 = (String) dataSnapshot.child("image2").getValue();
                    String txt2 = (String) dataSnapshot.child("text2").getValue();
                    //Ion.with(scrlimg2).load(img2);
                    String img3 = (String) dataSnapshot.child("image3").getValue();
                    String txt3 = (String) dataSnapshot.child("text3").getValue();
                    // Ion.with(scrlimg3).load(img3);
                    String img4 = (String) dataSnapshot.child("image4").getValue();
                    String txt4 = (String) dataSnapshot.child("text4").getValue();
                    //Ion.with(scrlimg4).load(img4);
                    String img5 = (String) dataSnapshot.child("image5").getValue();
                    String txt5 = (String) dataSnapshot.child("text5").getValue();
                    String img6 = (String) dataSnapshot.child("image6").getValue();
                    String txt6 = (String) dataSnapshot.child("text6").getValue();
                    String img7 = (String) dataSnapshot.child("image7").getValue();
                    String txt7 = (String) dataSnapshot.child("text7").getValue();
                    //Ion.with(scrlimg5).load(img5);

                    HashMap<String,String> url_maps = new HashMap<String, String>();
                    url_maps.put("", img1);
                    url_maps.put("", img2);
                    url_maps.put("", img3);
                    url_maps.put("",img4);

                    url_maps.put("",img5);
                    url_maps.put("",img6);
                    url_maps.put("",img7);
                    HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
                    file_maps.put("",R.mipmap.udbhavalogo11);
                    file_maps.put("",R.mipmap.udbhavalogo11);
                    file_maps.put("",R.mipmap.udbhavalogo11);
                    file_maps.put("",R.mipmap.udbhavalogo11);
                    file_maps.put("",R.mipmap.udbhavalogo11);
                    file_maps.put("",R.mipmap.udbhavalogo11);
                    file_maps.put("",R.mipmap.udbhavalogo11);
                    String images []={img1,img2,img3,img4,img5,img6,img7};

                    //// first image
                    DefaultSliderView textSliderView = new DefaultSliderView(Highmights.this);
                    textSliderView
                            .description(txt1)
                            .image(img1);



                    mDemoSlider.addSlider(textSliderView);

                    //second image


                    DefaultSliderView textSliderView2 = new DefaultSliderView(Highmights.this);
                    textSliderView2
                            .description(txt2)
                            .image(img2);

                    mDemoSlider.addSlider(textSliderView2);
                    /// third image

                    DefaultSliderView textSliderView3 = new DefaultSliderView(Highmights.this); textSliderView3
                            .description(txt3)
                            .image(img3);

                    mDemoSlider.addSlider(textSliderView3);
                    // 4th

                    DefaultSliderView textSliderView4 = new DefaultSliderView(Highmights.this);
                    textSliderView4
                            .description(txt4)
                            .image(img4);

                    mDemoSlider.addSlider(textSliderView4);
                    // 5th
                    DefaultSliderView textSliderView5 = new DefaultSliderView(Highmights.this);
                    textSliderView5
                            .description(txt5)
                            .image(img5);

                    mDemoSlider.addSlider(textSliderView5);
                    DefaultSliderView textSliderView6 = new DefaultSliderView(Highmights.this);
                    textSliderView5
                            .description(txt6)
                            .image(img6);

                    mDemoSlider.addSlider(textSliderView6);
                    DefaultSliderView textSliderView7 = new DefaultSliderView(Highmights.this);
                    textSliderView7
                            .description(txt7)
                            .image(img7);

                    mDemoSlider.addSlider(textSliderView7);



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mRef = FirebaseDatabase.getInstance().getReference().child("highlights").child("scroll");
        RecyclerView rcl =(RecyclerView)findViewById(R.id.chathighlights);

            
            LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(false);
        // rcl.scrollToPosition(-1);
        rcl.setHasFixedSize(false);
        rcl.setLayoutManager(manager);


        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressbarhighlights);
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
        mRecycleViewAdapter = new FirebaseRecyclerAdapter<Highmights.Post, Highmights.ChatHolder>(Highmights.Post.class, R.layout.highlightsel, Highmights.ChatHolder.class, mRef) {
            @Override
            protected void populateViewHolder(ChatHolder chatHolder, Post post, int i) {
                chatHolder.setName(post.getName());
                chatHolder.setImage(post.getImage());

            }


        };
        rcl.setAdapter(mRecycleViewAdapter);















    }



    public static class Post {
        String name;
        String contact;
        String about;
        String image;




        public Post()
        {

        }

        public Post(String name, String image) {
            this.name = name;

            this.image = image;

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
            LinearLayout messageContainer = (LinearLayout) mView.findViewById(R.id.container);
            CardView card =(CardView)mView.findViewById(R.id.card);
            //RelativeLayout linearLayout=(RelativeLayout)mView.findViewById(R.id.lnr);
            // ImageView imageView   =(ImageView)mView.findViewById(R.id.teamimage);

            View view             = (View)mView.findViewById(R.id.border);

        }
        public void setName(String name) {
            if (name.equals("")){
                TextView textView = (TextView) mView.findViewById(R.id.about);
                textView.setVisibility(View.GONE);
            }
            else {
                TextView textView = (TextView) mView.findViewById(R.id.about);
                textView.setText(name);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
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

