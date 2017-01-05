package com.udbhava;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

import de.hdodenhof.circleimageview.CircleImageView;

public class Campusamb extends AppCompatActivity {
    private DatabaseReference mReff;
    private FirebaseRecyclerAdapter<Posthomeanan, ChatHolderhomeanon> mRecycleViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campusamb);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final EditText etxe1=(EditText)findViewById(R.id.messege);
        ImageView bittonforcast=(ImageView)findViewById(R.id.sendbutton);
        FirebaseUser mRef= FirebaseAuth.getInstance().getCurrentUser();

        mReff = FirebaseDatabase.getInstance().getReference().child("zzcmo");



        bittonforcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseUser mRef= FirebaseAuth.getInstance().getCurrentUser();

                final FirebaseUser mRef= FirebaseAuth.getInstance().getCurrentUser();

                Uri imgurlgmail =mRef.getPhotoUrl();
                // final  String urut=imgurlgmail.toString().trim();
                String emailid=mRef.getEmail();
                String name=mRef.getDisplayName();
                String urut= mRef.getPhotoUrl() != null ? mRef.getPhotoUrl().toString() : null;
                //  Log.w("rt", "profileurl is"+urut);

                // Toast.makeText(getApplicationContext(),urut,Toast.LENGTH_LONG).show();


                //String emailid=mRef.getEmail();
                String texto = etxe1.getText().toString();

                if (urut==null &&!texto.equals("")&& !name.equals(""))
                {
                    long date1 = System.currentTimeMillis();
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE,dd MMM yyyy h:mm a");
                    String date = sdf.format(date1);
                    char U=name.charAt(0);
                    String n=String.valueOf(U);

                    mReff.push().setValue(new Posthomeanan(date,emailid,n,texto, name));
                    etxe1.setText("");
                } else {
                    if (texto.equals("")) {
                        Toast.makeText(getApplicationContext(), "empty textfield", Toast.LENGTH_SHORT).show();

                    } else if (!name.equals("") && !texto.equals("")&&!urut.equals("")) {

                        long date1 = System.currentTimeMillis();
                        SimpleDateFormat sdf = new SimpleDateFormat("EEE,dd MMM yyyy h:mm a");
                        String date = sdf.format(date1);

                        mReff.push().setValue(new Posthomeanan(date,emailid,urut,texto, name));
                        etxe1.setText("");

                    }
                }

            }

        });




        final RecyclerView rcl =(RecyclerView)findViewById(R.id.chattroom);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        // manager.setReverseLayout(true);
        // rcl.scrollToPosition(-1);
        manager.setStackFromEnd(true);

        //rcl.setHasFixedSize(false);
        rcl.setLayoutManager(manager);
        rcl.getScrollState();



        mReff.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Loder2();
                rcl.smoothScrollToPosition(rcl.getAdapter().getItemCount()+230);
                //rcl.scrollTo(0,0);
                ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressbarcamprtohhh);
                progressBar.setVisibility(View.GONE);
                rcl.scrollTo(rcl.getAdapter().getItemCount(),rcl.getAdapter().getItemCount());


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

        mRecycleViewAdapter = new FirebaseRecyclerAdapter<Posthomeanan,ChatHolderhomeanon>(Posthomeanan.class, R.layout.chatroom, ChatHolderhomeanon.class, mReff) {
            @Override
            protected void populateViewHolder(ChatHolderhomeanon chatHolder, Posthomeanan post, int i) {
                // chatHolder.setEm(post.getTitle());

                chatHolder.setDate(post.getDate());
                chatHolder.setImage(post.getImage());
                chatHolder.setMessege(post.getMessege());
                chatHolder.setName(post.getName());


            }


        };
        rcl.setAdapter(mRecycleViewAdapter);

















    }
    public static class Posthomeanan {


        // @JsonProperty("name")
        public  String name;
        //@JsonProperty("date")
        public String date;
        // @JsonProperty("image")
        public  String image;
        //@JsonProperty("messege")
        public String messege;
        //@JsonProperty("emailid")
        public String emailid;

        public Posthomeanan()
        {

        }

        public Posthomeanan(String date, String emailid, String image , String messege, String name) {
            this.name=name;
            this.date =date;
            this.image =image;
            this.emailid = emailid;
            this.messege=messege;

        }

        public String getImage() {
            return image;
        }


        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public String getMessege() {
            return messege;
        }

        public String getEmailid() {
            return emailid;
        }


    }
    public static class ChatHolderhomeanon extends RecyclerView.ViewHolder {
        View mView;

        public ChatHolderhomeanon(View itemView) {
            super(itemView);
            mView = itemView;

            LinearLayout messageContainer = (LinearLayout) mView.findViewById(R.id.newscontainer);
            CardView card = (CardView) mView.findViewById(R.id.newscard);
            //RelativeLayout linearLayout=(RelativeLayout)mView.findViewById(R.id.lnr);


            //View view             = (View)mView.findViewById(R.id.border);

        }


        public void setDate(String date) {
            TextView textView = (TextView) mView.findViewById(R.id.date);
            // textView.setMaxLines(2);
            textView.setText(date);

        }



        public void setName(final  String name) {

            //final TextView  textView2        =(TextView)mView.findViewById(R.id.contenthomepage);
            //  final TextView  textView23        =(TextView)mView.findViewById(R.id.show);
            // final TextView  textView24        =(TextView)mView.findViewById(R.id.hide);
            TextView expandableTextView = (TextView) mView.findViewById(R.id.textviewnamecos);
            /*
            final FirebaseUser mRef= FirebaseAuth.getInstance().getCurrentUser();
            Uri imgurlgmail =mRef.getPhotoUrl();
            String urt=imgurlgmail.toString();
           String tname=mRef.getDisplayName();*/
            final FirebaseUser mRef= FirebaseAuth.getInstance().getCurrentUser();

            Uri imgurlgmail =mRef.getPhotoUrl();
//                final  String urut=imgurlgmail.toString();
            String emailid=mRef.getEmail();
            String name1=mRef.getDisplayName();
            if(name1==name){

            }

            expandableTextView.setText(name);


            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color1 = generator.getRandomColor();

            expandableTextView.setTextColor(color1);

        }
        public void setMessege(String messege) {
            TextView mtx=(TextView)mView.findViewById(R.id.message12);
            mtx.setText(messege);
        }

        public void setImage(String image) {


            if(image.length()==1)
            {
                TextView expandableTextView = (TextView) mView.findViewById(R.id.textviewnamecos);

                expandableTextView.setMovementMethod(LinkMovementMethod.getInstance());
                final FirebaseUser mRef= FirebaseAuth.getInstance().getCurrentUser();

                //Uri imgurlgmail =mRef.getPhotoUrl();
//                final  String urut=imgurlgmail.toString();
                String emailid=mRef.getEmail();
                //String name=mRef.getDisplayName();

                ColorGenerator generator = ColorGenerator.MATERIAL;
                int color1 = generator.getRandomColor();
                char first=image.charAt(0);



                TextDrawable drawable = TextDrawable.builder()
                        .beginConfig()
                        .width(60)  // width in px
                        .height(60) // height in px
                        .endConfig()
                        .buildRound(String.valueOf(first).toUpperCase()+"", color1);
                CircleImageView image1 = (CircleImageView) mView.findViewById(R.id.imagenewsicon);
                image1.setImageDrawable(drawable);

            }
            else {
                CircleImageView image1 = (CircleImageView) mView.findViewById(R.id.imagenewsicon);
                Picasso.with(mView.getContext()).load(image)
                        .placeholder(R.mipmap.udbhavalogo11).error(R.drawable.bacfk)
                        .into(image1);
            }
        }



    }


}



