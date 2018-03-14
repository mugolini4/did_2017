package com.example.matil.LAMPApp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by matil on 24/11/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.LampViewHolder> {

    List<Lamp> lamps;

    RVAdapter(List<Lamp> lamps){
        this.lamps = lamps;
    }

    @Override
    public LampViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card, viewGroup, false);
        LampViewHolder lvh = new LampViewHolder(v);
        return lvh;
    }

    @Override
    public void onBindViewHolder(LampViewHolder holder, int position) {
        holder.lampName.setText(lamps.get(position).getLamp_name());
        holder.lampPhoto.setImageResource(lamps.get(position).getLamp_image());
    }

    @Override
    public int getItemCount() {
        return lamps.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class LampViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView lampName;
        ImageView lampPhoto;

        LampViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.lamp_card_view);
            lampName = (TextView)itemView.findViewById(R.id.lamp_name);
            lampPhoto = (ImageView)itemView.findViewById(R.id.lamp_photo);
        }

    }

}
