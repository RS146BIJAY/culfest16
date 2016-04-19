package com.culfest.nit.jamshedpur;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Team extends Fragment {

    RecyclerView rcv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_team, container, false);

        rcv = (RecyclerView) v.findViewById(R.id.contact_rv);
        LinearLayoutManager manager = new LinearLayoutManager(container.getContext());
        rcv.setLayoutManager(manager);
        rcv.setHasFixedSize(false);

        ArrayList<Contact_item> list = new ArrayList<Contact_item>();
        list.add(new Contact_item("VAIBHAV DUBEY", "Cultural Secretary", "08936874946"));
        list.add(new Contact_item("ANAND RAJ", "General Secretary", "09905487755"));
        list.add(new Contact_item("VIKAS DIXIT", "Joint Secretary", "09472706225"));
        list.add(new Contact_item("SANDEEP KIRAN JHA", "Spokesperson", "07301492454"));
        list.add(new Contact_item("SOURAV SARAFF", "Treasurer", "09031705806"));
        list.add(new Contact_item("VISHAL ROY", "Event Management", "07277145853"));
        list.add(new Contact_item("SHUBHANSHU RAI", "Event Management", "07277145853"));
        list.add(new Contact_item("ROHIT SINGH", "Event Management", ""));
        list.add(new Contact_item("SONAL KUMAR", "Event Management", "09097353819"));
        list.add(new Contact_item("SHASHWAT KUMAR", "Event Management", "09472791275"));
        list.add(new Contact_item("SAURAV SHARMA", "Public Relation", "9939725284"));
        list.add(new Contact_item("PIYUSH RAJ", "Public Relation", "08986815565"));
        list.add(new Contact_item("YOGESH SHUKLA", "Hospitality", "09471591162"));
        list.add(new Contact_item("SANJEET SUMAN", "Hospitality", "09973055175"));
        list.add(new Contact_item("GAURAV SRIVASTAVA", "Hospitality", "07070450425"));
        list.add(new Contact_item("AKANKSHA SHARMA", "Hospitality", "09472603875"));
        list.add(new Contact_item("ANKIT DUBEY", "Planning", "09470918698"));
        list.add(new Contact_item("VENKATESH", "Security", "08409567565"));
        list.add(new Contact_item("ABHISHEK KUMAR", "Security", "07739880080"));
        list.add(new Contact_item("NIKHIL", "Logistics", "07563885585"));
        list.add(new Contact_item("RAVI CHANDRA PRAKASH", "Logistics", "08987525634"));
        list.add(new Contact_item("PRAVIN KUMAR", "Logistics", "08051478830"));
        list.add(new Contact_item("SUMIT RAJ", "Logistics", "7739217642"));

        list.add(new Contact_item("SHIVPUJAN SHARMA", "Senior Coordinator", "09470646632"));
        list.add(new Contact_item("ASHISH KUMAR BARNWAL", "Senior Coordinator", "9696006200"));
        list.add(new Contact_item("MANISH KUMAR", "Senior Coordinator", "8797057994"));
        list.add(new Contact_item("K. PRASHANT", "Senior Coordinator", "7549114328"));
        list.add(new Contact_item("ANJANI KUMAR", "Senior Coordinator", "9472234461"));
        list.add(new Contact_item("GAURAV KUMAR", "Senior Coordinator", "7549942461"));
        list.add(new Contact_item("SWATI SIROHI", "Senior Coordinator", "8797262877"));

        list.add(new Contact_item("AYUSH CHANDRA", "Creative Team", "09430702344"));
        list.add(new Contact_item("PRADYUT PRANAV", "Creative team", "07209908275"));
        list.add(new Contact_item("SWAYAM RAINA", "Creative team", "09431786203"));

        list.add(new Contact_item("AKASH SRIVASTAVA", "Web Team", "09430165105"));
        list.add(new Contact_item("SHUBHENDU SHISHIR", "Web Team", "8409558075"));
        list.add(new Contact_item("AMAN SINGH", "Web Team", "7277434340"));
        list.add(new Contact_item("SHUBHAM", "Web Team", "9431343047"));
        list.add(new Contact_item("SURAJ POTNURU", "Web Team", "8292980033"));
        list.add(new Contact_item("PUNJ KUMAR", "Web Team", "9263141989"));
        list.add(new Contact_item("SIDHANT RAJAN", "Web Team", "7070474521"));
        list.add(new Contact_item("AKSHAY PODDAR", "Web Team", "9031000893"));

        rcv.setAdapter(new Contact_Recycle_Adapter(list, getActivity().getApplicationContext()));
        return v;
    }

    class Contact_Recycle_Adapter extends RecyclerView.Adapter<Contact_Recycle_Adapter.view_holder> {

        int lastpos = 0;
        Context context;
        ArrayList<Contact_item> mess;

        public Contact_Recycle_Adapter(ArrayList<Contact_item> mess, Context context) {
            this.mess = mess;
            this.context = context;
        }

        CardView cv;

        @Override
        public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_indiv, parent, false);
            cv = (CardView) v.findViewById(R.id.contact_cv);
            view_holder vh = new view_holder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(view_holder holder, final int position) {
            holder.tv1.setText(mess.get(position).st1);
            holder.tv2.setText(mess.get(position).st2);
            holder.tv3.setText(mess.get(position).ph);
            holder.current = mess.get(position);

            View v = holder.itemView;
            v.startAnimation(AnimationUtils.loadAnimation(context, (position > lastpos) ? R.anim.up_from_bottom : R.anim.down_from_top));
            lastpos = position;

        }

        @Override
        public int getItemCount() {
            return mess.size();
        }

        public class view_holder extends RecyclerView.ViewHolder {
            public Contact_item current;
            CardView cardView;
            TextView tv1, tv2, tv3;

            public view_holder(View v) {
                super(v);
                cardView = (CardView) v.findViewById(R.id.contact_cv);
                tv1 = (TextView) v.findViewById(R.id.name_tv);
                tv2 = (TextView) v.findViewById(R.id.post_tv);
                tv3 = (TextView) v.findViewById(R.id.num_call);

            }

        }
    }

    class Contact_item {
        public String st1, st2, ph;

        public Contact_item(String st1, String st2, String ph) {
            this.st1 = st1;
            this.st2 = st2;
            this.ph = ph;
        }


    }
}