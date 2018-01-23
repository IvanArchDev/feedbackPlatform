package com.kuroptev.feedback.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kuroptev.feedback.R;
import com.kuroptev.feedback.pojo.Manager;
import com.kuroptev.feedback.screens.FeedbackCreator;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerManager extends RecyclerView.Adapter<RecyclerManager.MangerHolder>{
    private List<Manager> listManagers;
    private Context context;

    public RecyclerManager(List<Manager> list, Context context){
        this.listManagers = list;
        this.context = context;
    }


    @Override
    public MangerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_manager, parent, false);
        return new MangerHolder(view);
    }

    @Override
    public void onBindViewHolder(MangerHolder holder, int position) {
        holder.fullName.setText(listManagers.get(position).getFullName());
        Picasso.with(context).load(listManagers.get(position).getUrlPhoto()).into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return listManagers.size();
    }


    public static class MangerHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        CardView cardView;
        TextView fullName;
        ImageView photo;
        public MangerHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_manager);
            cardView.setOnClickListener(this);
            fullName = (TextView) itemView.findViewById(R.id.full_name_manager);
            photo = (ImageView) itemView.findViewById(R.id.photo_manager);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), FeedbackCreator.class);
            intent.putExtra("Manager", fullName.getText().toString());
            intent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
            view.getContext().startActivity(intent);
        }
    }
}

