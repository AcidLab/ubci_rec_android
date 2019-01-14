package com.acidlab.ubci_reclamations.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acidlab.ubci_reclamations.Adapters.ReclamationAdapter;
import com.acidlab.ubci_reclamations.Models.Reclamation;
import com.acidlab.ubci_reclamations.Models.User;
import com.acidlab.ubci_reclamations.R;
import com.acidlab.ubci_reclamations.networking.NetworkingAsyncResponse;
import com.acidlab.ubci_reclamations.networking.NetworkingHelper;

import java.util.List;


public class EnAttente extends Fragment implements NetworkingAsyncResponse{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public EnAttente() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_en_attente, container, false);
        NetworkingHelper n = new NetworkingHelper(this);
       // n.getReaclamationEnAttente(1);
        n.getReaclamationEnAttente(User.getCurrentUser(getContext()).getId());

        mRecyclerView = view.findViewById(R.id.en_attente_reclamations);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        return view;
    }

    @Override
    public void onReclamationEnAttenteGetter(List<Reclamation> reclamations) {

        if (reclamations == null){
            Log.e("MSG","RECLAMATIONS VIDE");
        }else {
            Log.e("MSG",reclamations.size()+"RECLAMATIONS Trouvées");

            mAdapter = new ReclamationAdapter(reclamations);
            mRecyclerView.setAdapter(mAdapter);

        }
    }
}
