package com.acidlab.ubci_reclamations.Adapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acidlab.ubci_reclamations.Models.Reclamation;
import com.acidlab.ubci_reclamations.R;

import java.util.ArrayList;
import java.util.List;


public class ReclamationAdapter extends RecyclerView.Adapter<ReclamationAdapter.MyViewHolder> {

    private List<Reclamation> reclamations = new ArrayList<>();

    public ReclamationAdapter(List<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }

    @NonNull
    @Override
    public ReclamationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.reclamation_cell, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReclamationAdapter.MyViewHolder myViewHolder, int position) {
        final Reclamation reclamation = reclamations.get(position);
        myViewHolder.reclamation = reclamation;
        myViewHolder.refreshUI(reclamation);
    }

    @Override
    public int getItemCount() {
        return reclamations.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dateReclamation,sujetReclamation,interlocuteurReclamation,priorityReclamation;
        Reclamation reclamation;
        ImageView statusImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            dateReclamation = itemView.findViewById(R.id.date_reclamation);
            sujetReclamation = itemView.findViewById(R.id.sujet_reclamation);
            interlocuteurReclamation = itemView.findViewById(R.id.interlocuteur_reclamation);
            priorityReclamation = itemView.findViewById(R.id.priority_reclamation);
            statusImg = itemView.findViewById(R.id.statusImg);

        }

        @SuppressLint("SetTextI18n")
        public void refreshUI(Reclamation reclamation) {
            dateReclamation.setText("Date : " + reclamation.getDate());
            sujetReclamation.setText("Sujet : " + reclamation.getSujet());
            interlocuteurReclamation.setText("Interlocuteur : " + reclamation.getInterlocuteur());
            priorityReclamation.setText("Priorité : " + reclamation.getPriority_id());
            if (reclamation.getStatus_id() == 1){
                statusImg.setImageDrawable(itemView.getResources().getDrawable(R.drawable.red_circle));
            }else if (reclamation.getStatus_id() == 2){
                statusImg.setImageDrawable(itemView.getResources().getDrawable(R.drawable.yellow_circle));
            }else{
                statusImg.setImageDrawable(itemView.getResources().getDrawable(R.drawable.green_circle));

            }

        }
    }
}

