package com.acidlab.ubci_reclamations;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.acidlab.ubci_reclamations.Models.User;
import com.acidlab.ubci_reclamations.networking.NetworkingAsyncResponse;
import com.acidlab.ubci_reclamations.networking.NetworkingHelper;
import com.goodiebag.pinview.Pinview;


public class SignUpActivity extends AppCompatActivity implements NetworkingAsyncResponse{

    ImageButton back;
    Pinview pinView;

    Context context;
    ProgressBar loading;

    Button confirm;

    EditText fname,lname,email,password;
    int codePoste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        back = findViewById(R.id.backBtn);
        confirm = findViewById(R.id.confirm);
        pinView =  findViewById(R.id.pinview);
        loading = findViewById(R.id.progresseBar);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        context = this;


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
                codePoste = Integer.parseInt(pinview.getValue());
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                NetworkingHelper n = new NetworkingHelper(context);
                n.register(fname.getText().toString(),lname.getText().toString(),email.getText().toString(),password.getText().toString(),codePoste);
            }
        });


    }

    @Override
    public void onUserRegister(User user) {
        if (user == null) {
            Log.e("MSG", "Failed no user");
            loading.setVisibility(View.GONE);
        } else {
            Log.e("MSG", user.toString());
            Intent intent = new Intent(context, MainReclamationsActivity.class);
            context.startActivity(intent);
            finish();
        }
    }
}
