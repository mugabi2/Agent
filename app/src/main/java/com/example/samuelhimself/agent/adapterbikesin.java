package com.example.samuelhimself.agent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class adapterbikesin extends RecyclerView.Adapter<adapterbikesin.bikesinViewHolder> {
    private ArrayList<bikesinclass> bikeList;
    Context context;
    onClickInterface onClickInterface;
    public static class bikesinViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewer;

        public bikesinViewHolder(View itemView) {
            super(itemView);
            textViewer = itemView.findViewById(R.id.textbikesin);
        }
    }

    public adapterbikesin(Context context, ArrayList<bikesinclass> BikesList) {
        this.context = context;
        bikeList = BikesList;
    }

    @Override
    public adapterbikesin.bikesinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bike_in_template, parent, false);
        adapterbikesin.bikesinViewHolder evh = new adapterbikesin.bikesinViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(adapterbikesin.bikesinViewHolder holder, final int position) {
        bikesinclass currentnowItem = bikeList.get(position);

        holder.textViewer.setText(currentnowItem.getBikenumber());

//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onClickInterface.setClick(position);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return bikeList.size();
    }
}