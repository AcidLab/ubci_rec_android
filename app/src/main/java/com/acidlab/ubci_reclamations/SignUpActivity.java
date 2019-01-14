package com.acidlab.ubci_reclamations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;


public class SignUpActivity extends AppCompatActivity {

    ImageButton back;
    Pinview pinView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        back = findViewById(R.id.backBtn);
        pinView =  findViewById(R.id.pinview);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pinView.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser) {
                //Make api calls here or what not
                Toast.makeText(SignUpActivity.this, pinview.getValue(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
