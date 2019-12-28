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


public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<ExampleItem> mExampleList;
    Context context;
    onClickInterface onClickInterface;
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        RelativeLayout relativeLayout;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageVi);
            mTextView1 = itemView.findViewById(R.id.textView1);
            mTextView2 = itemView.findViewById(R.id.textView2);
            relativeLayout=itemView.findViewById(R.id.recyclerelative);
        }
    }

    public ExampleAdapter(Context context, ArrayList<ExampleItem> exampleList, onClickInterface onClickInterface) {
        this.context = context;
        mExampleList = exampleList;
        this.onClickInterface = onClickInterface;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, final int position) {
        ExampleItem currentItem = mExampleList.get(position);


        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getSurname()+" "+currentItem.getFirstname()+" "+currentItem.getCash());
        holder.mTextView2.setText(currentItem.getPhone()+" "+currentItem.getPack());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickInterface.setClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}