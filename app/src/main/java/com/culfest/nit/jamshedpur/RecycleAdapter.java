package com.culfest.nit.jamshedpur;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nit on 05-Jan-16.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.VH> {

    int lastpos = 0;
    Context context;
    ArrayList<Notification_item> mess;

    public RecycleAdapter(ArrayList<Notification_item> mess, Context context) {
        this.mess = mess;
        this.context = context;
    }


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_indv, parent, false);
        VH obj = new VH(v);
        return obj;

    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.pic1.setImageBitmap(mess.get(position).image);
        holder.tv1.setText(mess.get(position).message);
        holder.currentItem = mess.get(position);

        View v = holder.itemView;
        v.startAnimation(AnimationUtils.loadAnimation(context, (position > lastpos) ? R.anim.up_from_bottom : R.anim.down_from_top));
        lastpos = position;
    }

    @Override
    public int getItemCount() {
        return mess.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class VH extends RecyclerView.ViewHolder {
        CardView cardView, cardView1;
        ImageView pic1, pic2;
        TextView tv1, tv2;
        public Notification_item currentItem;

        public VH(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv1);
            pic1 = (ImageView) itemView.findViewById(R.id.photot);
            tv1 = (TextView) itemView.findViewById(R.id.tvt1);

            cardView1 = (CardView) itemView.findViewById(R.id.cv);
            pic2 = (ImageView) itemView.findViewById(R.id.photo);
            tv2 = (TextView) itemView.findViewById(R.id.desc);

            itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (cardView.getVisibility() == View.VISIBLE) {
                                Drawable dr = pic1.getDrawable();
                                String text = tv1.getText().toString();

                                pic1.setImageDrawable(null);
                                tv1.setText(null);
                                cardView.setVisibility(View.INVISIBLE);

                                pic2.setImageDrawable(dr);
                                tv2.setText(text);
                                cardView1.setVisibility(View.VISIBLE);
                            } else if (cardView1.getVisibility() == View.VISIBLE) {
                                Drawable dr = pic2.getDrawable();
                                String text = tv2.getText().toString();

                                pic2.setImageDrawable(null);
                                tv2.setText(null);
                                cardView1.setVisibility(View.INVISIBLE);

                                pic1.setImageDrawable(dr);
                                tv1.setText(text);
                                cardView.setVisibility(View.VISIBLE);
                            }
                        }
                    }
            );
        }
    }
}