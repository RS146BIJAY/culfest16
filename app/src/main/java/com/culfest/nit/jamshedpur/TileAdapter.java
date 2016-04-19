package com.culfest.nit.jamshedpur;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nit on 17-Jan-16.
 */
public class TileAdapter extends RecyclerView.Adapter<TileAdapter.Holder>{

    ArrayList<Item> mess;

    public TileAdapter(ArrayList<Item> mess){
        this.mess=mess;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.indiv_tile,parent,false);
        Holder h=new Holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.img.setImageResource(mess.get(position).id);
        holder.txt.setText(mess.get(position).text);
    }

    @Override
    public int getItemCount() {
        return mess.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        ImageButton img;
        TextView txt;

        public Holder(View itemView) {
            super(itemView);
            img= (ImageButton) itemView.findViewById(R.id.indimg);
            txt= (TextView) itemView.findViewById(R.id.indtv);
        }

    }
}
