package com.example.matil.LAMPApp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
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
    public void onBindViewHolder(final LampViewHolder holder, final int position) {
        holder.lampName.setText(lamps.get(position).getLampName());
        holder.lampPhoto.setImageResource(lamps.get(position).getLampImage());
        holder.lampSwitch.setChecked( lamps.get( position ).isOn() );

        holder.lampSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                lamps.get(position).setState(holder.lampSwitch.isChecked());
            }
        });
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
        Switch lampSwitch;

        LampViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.lamp_card_view);
            lampName = itemView.findViewById(R.id.lamp_name);
            lampPhoto = itemView.findViewById(R.id.lamp_photo);
            lampSwitch = itemView.findViewById(R.id.lamp_switch);

        }
    }
}
