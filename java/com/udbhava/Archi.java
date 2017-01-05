package com.udbhava;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.koushikdutta.ion.Ion;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.listeners.ActionClickListener;
import com.ramotion.foldingcell.FoldingCell;
import com.truizlop.fabreveallayout.FABRevealLayout;
import com.truizlop.fabreveallayout.OnRevealChangeListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class Archi extends Fragment {
    private FirebaseRecyclerAdapter<Post, ChatHolder> mRecycleViewAdapter;

    private DatabaseReference mRef;

    public static Archi newInstance() {
        Archi fragment5 = new Archi();
        return fragment5;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mview=inflater.inflate(R.layout.fragment_archi, container, false);
         mRef = FirebaseDatabase.getInstance().getReference().child("Events").child("archi").child("events");



        RecyclerView rcl =(RecyclerView)mview.findViewById(R.id.eventsrecycelview);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setReverseLayout(false);
        // rcl.scrollToPosition(-1);
        rcl.setHasFixedSize(false);
        rcl.setLayoutManager(manager);

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

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

        mRecycleViewAdapter = new FirebaseRecyclerAdapter<Post, ChatHolder>(Post.class, R.layout.eventelement, ChatHolder.class, mRef) {
            @Override
            protected void populateViewHolder(ChatHolder chatHolder, Post post, int i) {
                chatHolder.setAbout(post.getAbout());
                chatHolder.setContact(post.getContact());


                chatHolder.setImage1(post.getImage1());
                chatHolder.setImage2(post.getImage2());
                chatHolder.setImage3(post.getImage3());
                chatHolder.setTitle(post.getTitle());
                chatHolder.setTitleimg(post.getTitleimg());



            }


        };
        rcl.setAdapter(mRecycleViewAdapter);











        return mview;
    }


    public static class Post {
        String about;
        String contact;
        String image1;
        String image2;
        String image3;
        String title;
        String titleimg;
        //registeration
       /* String college;
        String email;
        String eventname;
        String members;
        String phone;
        String teamname;
          */



        public Post()
        {

        }

        public Post(String about, String contact, String image1, String image2,String image3,String title,String titleimg/*String college,String email,String eventname, String members,String phone, String teamname*/) {
            this.about =about;
            this.contact =contact;
            this.image1 =image1;
            this.image2 = image2;
            this.image3 = image3;
            this.title=title;
            this.titleimg=titleimg;
            /*this.college=college;
            this.email=email;
            this.eventname=eventname;
            this.members=members;
            this.phone=phone;
            this.teamname=teamname;*/

        }

        public String getAbout() {
            return about;
        }

        public String getContact() {
            return contact;
        }

        public String getImage1() {
            return image1;
        }

        public String getImage2() {
            return image2;
        }

        public String getImage3() {
            return image3;
        }

        public String getTitle() {
            return title;
        }

        public String getTitleimg() {
            return titleimg;
        }
    }
    public static class ChatHolder extends RecyclerView.ViewHolder {
        View mView;
        private DatabaseReference mReef;

        public ChatHolder(View itemView) {
            super(itemView);
            mView = itemView;
           // RelativeLayout messageContainer = (RelativeLayout) mView.findViewById(R.id.container);
            TextView innertextheadinner = (TextView) mView.findViewById(R.id.titleinner);
            TextView innertextheadouter = (TextView) mView.findViewById(R.id.upertext);
            TextView conatct = (TextView) mView.findViewById(R.id.contact);
            TextView about = (TextView) mView.findViewById(R.id.about);
            ImageView titleimg = (ImageView) mView.findViewById(R.id.titleimage);
            ImageView image1 = (ImageView) mView.findViewById(R.id.image1);
            ImageView image2 = (ImageView) mView.findViewById(R.id.image2);
            ImageView image3 = (ImageView) mView.findViewById(R.id.image3);
           RelativeLayout rel=(RelativeLayout)mView.findViewById(R.id.relet);
            // form fillup
            Button register =(Button)mView.findViewById(R.id.register);
            final FoldingCell fc =(FoldingCell)mView.findViewById(R.id.folding_cell);
            ImageButton fab =(ImageButton)mView.findViewById(R.id.back);
            RelativeLayout re =(RelativeLayout)mView.findViewById(R.id.secondary_view);
            final FABRevealLayout fabRevealLayout = (FABRevealLayout)mView. findViewById(R.id.fab_reveal_layout);
            fc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fc.toggle(false);
                }
            });
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //configureFABReveal(fabRevealLayout);
                    fabRevealLayout.revealMainView();
                }
            });




            mReef=FirebaseDatabase.getInstance().getReference().child("Events").child("archi").child("Reg");

            register.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    EditText eventname = (EditText) mView.findViewById(R.id.eventname);
                    EditText teamname = (EditText) mView.findViewById(R.id.teamname);
                    EditText college = (EditText) mView.findViewById(R.id.college);
                    EditText email = (EditText) mView.findViewById(R.id.email);
                    EditText phone = (EditText) mView.findViewById(R.id.phoneno);
                    EditText members = (EditText) mView.findViewById(R.id.membersname);
                    String cg=college.getText().toString();
                    String em=email.getText().toString();
                     String eve=eventname.getText().toString();
                    String mem=members.getText().toString();
                    String ph=phone.getText().toString();
                   String tem= teamname.getText().toString();
                    if (cg.equals("")||em.equals("")||eve.equals("")||mem.equals("")||ph.equals("")||tem.equals("")){

                        android.support.design.widget.Snackbar.make(v, "fill all textfield" ,android.support.design.widget.Snackbar.LENGTH_LONG)
                                .setAction("Action",null)

                                .setActionTextColor(Color.GREEN)
                                .show();
                    }
                   else {
                        mReef.push().setValue(new Push(cg,em,eve,mem,ph,tem));
                        eventname.setText("");
                        teamname.setText("");
                        college.setText("");
                        email.setText("");
                        phone.setText("");
                        members.setText("");

                        android.support.design.widget.Snackbar.make(v, "Registeration Successfull", android.support.design.widget.Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }
                }
            });
               }
        private void configureFABReveal(FABRevealLayout fabRevealLayout) {
            fabRevealLayout.setOnRevealChangeListener(new OnRevealChangeListener() {
                @Override
                public void onMainViewAppeared(FABRevealLayout fabRevealLayout, View mainView) {
                    ;
                    fabRevealLayout.revealMainView();
                }

                @Override
                public void onSecondaryViewAppeared(final FABRevealLayout fabRevealLayout, View secondaryView) {

                    fabRevealLayout.revealSecondaryView();
                }
            });
        }

        public void setAbout(String about) {
            TextView about1 = (TextView) mView.findViewById(R.id.about);
               about1.setText(about);

        }

        public void setContact(String contact) {
            TextView conatct = (TextView) mView.findViewById(R.id.contact);
            conatct.setText(contact);

        }

        public void setImage1(String image1) {
            ImageView image11 = (ImageView) mView.findViewById(R.id.image1);
            Ion.with(image11).placeholder(R.mipmap.udbhavalogo11).load(image1);

        }

        public void setImage2(String image2) {
            ImageView image22 = (ImageView) mView.findViewById(R.id.image2);
            Ion.with(image22).placeholder(R.mipmap.udbhavalogo11).load(image2);


        }

        public void setImage3(String image3) {
            ImageView image33 = (ImageView) mView.findViewById(R.id.image3);
            Ion.with(image33).placeholder(R.mipmap.udbhavalogo11).load(image3);

        }

        public void setTitle(String title) {
            TextView tr1 = (TextView)mView.findViewById(R.id.titleinner);
            TextView tr2 = (TextView) mView.findViewById(R.id.upertext);
            tr1.setText(title);
            tr2.setText(title);
        }

        public void setTitleimg(String titleimg) {
            ImageView titleimg1 = (ImageView) mView.findViewById(R.id.titleimage);
           Ion.with(titleimg1).placeholder(R.mipmap.udbhavalogo11).load(titleimg);


        }
        public static class Push{
            String college;
            String email;
            String eventname;
            String members;
            String phone;
            String teamname;

            public  Push()
            {

            }
            public Push(String college,String email,String eventname, String members,String phone, String teamname){
                this.college=college;
                this.email=email;
                this.eventname=eventname;
                this.members=members;
                this.phone=phone;
                this.teamname=teamname;

            }

            public String getCollege() {
                return college;
            }

            public String getEmail() {
                return email;
            }

            public String getEventname() {
                return eventname;
            }

            public String getMembers() {
                return members;
            }

            public String getPhone() {
                return phone;
            }

            public String getTeamname() {
                return teamname;
            }
        }




    }

}