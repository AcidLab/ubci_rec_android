package com.acidlab.ubci_reclamations.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acidlab.ubci_reclamations.R;


public class EnAttente extends Fragment {


    public EnAttente() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_en_attente, container, false);
    }

}
