package com.culfest.nit.jamshedpur;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nit on 27-Jan-16.
 */
public class Recycle_adapter extends RecyclerView.Adapter<Recycle_adapter.holder> {

    ArrayList<String> list;

    public Recycle_adapter(ArrayList<String> list){
        this.list=list;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_home,parent,false);
        holder h=new holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(holder holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class holder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView textView;

        public holder(View itemView) {
            super(itemView);
            cv= (CardView) itemView.findViewById(R.id.cv1);
            textView= (TextView) itemView.findViewById(R.id.tv1);
        }
    }
}
