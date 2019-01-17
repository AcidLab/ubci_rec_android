package com.acidlab.ubci_reclamations;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acidlab.ubci_reclamations.Models.Local;
import com.acidlab.ubci_reclamations.Models.User;
import com.acidlab.ubci_reclamations.Utils.Utilities;
import com.acidlab.ubci_reclamations.networking.NetworkingAsyncResponse;
import com.acidlab.ubci_reclamations.networking.NetworkingHelper;
import com.android.volley.Request;
import com.fxn.pix.Pix;
import com.fxn.utility.PermUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AjoutReclamationActivity extends AppCompatActivity implements NetworkingAsyncResponse {

    TextView sujetReclamationTV, chosenLocalTV;
    Button choisirPhotoBtn, choisirLocalBtn, confimBtn;
    ImageView chosenImg;

    static int CODE_CAMERA = 100;
    int local_id = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_reclamation);
        sujetReclamationTV = findViewById(R.id.sujet);
        chosenLocalTV = findViewById(R.id.chosenLocal);
        choisirLocalBtn = findViewById(R.id.choisir_local);
        choisirPhotoBtn = findViewById(R.id.choisir_photo);
        chosenImg = findViewById(R.id.img);
        confimBtn = findViewById(R.id.confirm);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.ajout_reclamation));
        mToolbar.setNavigationIcon(R.drawable.arrow_icon);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        choisirPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pix.start(AjoutReclamationActivity.this, CODE_CAMERA, 1);
            }
        });

        choisirLocalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builderSingle = new AlertDialog.Builder(v.getContext());
                builderSingle.setIcon(R.drawable.local_icon);
                builderSingle.setTitle("Faites un choix de la liste :");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(v.getContext(), R.layout.choice_list);
                for (int i = 0; i < Local.getLocals().size(); i++) {
                    arrayAdapter.add(Local.getLocals().get(i).getLabel());
                }


                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        chosenLocalTV.setText(Local.getLocals().get(which).getLabel());
                        local_id = Local.getLocals().get(which).getId();
                    }
                });
                builderSingle.show();
            }
        });

        confimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Utilities.alert(AjoutReclamationActivity.this, SweetAlertDialog.SUCCESS_TYPE, "Succès", "Reclamation ajoutée");

                Log.e("LocalID", "" + local_id);
                if (local_id != -1 && !sujetReclamationTV.getText().toString().equals("")) {
                    NetworkingHelper n = new NetworkingHelper(AjoutReclamationActivity.this);
                    n.creationReclamation(sujetReclamationTV.getText().toString(), local_id);
                } else {
                    if (sujetReclamationTV.getText().toString().equals("")) {
                        Utilities.alert(AjoutReclamationActivity.this, SweetAlertDialog.WARNING_TYPE, "Avertissement", "Vous devez ajoute le sujet de la réclamation");
                    } else {
                        Utilities.alert(AjoutReclamationActivity.this, SweetAlertDialog.WARNING_TYPE, "Avertissement", "Vous devez sélectionner un local");
                    }
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case PermUtil.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Pix.start(AjoutReclamationActivity.this, CODE_CAMERA, 1);
                } else {
                    Toast.makeText(AjoutReclamationActivity.this, "Approve permissions to open Pix ImagePicker", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == CODE_CAMERA) {
            ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            File imgFile = new File(returnValue.get(0));

            if (imgFile.exists()) {
                Bitmap myBitmap;
                myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                chosenImg.setImageBitmap(myBitmap);
                chosenImg.setPadding(0, 0, 0, 10);

            }
        }
    }


    @Override
    public void onReclamationCreated(int successCode) {
        if (successCode == 1) {
            //Succes
            SweetAlertDialog alertDialog = new SweetAlertDialog(AjoutReclamationActivity.this, SweetAlertDialog.SUCCESS_TYPE);
            alertDialog.setTitleText(getResources().getString(R.string.succes));
            alertDialog.setContentText(getResources().getString(R.string.reclamation_succes));
            alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    finish();
                }
            })
                    .show();
        } else {
            //Erreur
            SweetAlertDialog alertDialog = new SweetAlertDialog(AjoutReclamationActivity.this, SweetAlertDialog.ERROR_TYPE);
            alertDialog.setTitleText(getResources().getString(R.string.erreur));
            alertDialog.setContentText(getResources().getString(R.string.reclamation_erreur));
            alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismiss();
                }
            })
                    .show();
        }
    }
}
