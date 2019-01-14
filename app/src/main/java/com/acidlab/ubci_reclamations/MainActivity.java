package com.acidlab.ubci_reclamations;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.acidlab.ubci_reclamations.Models.User;
import com.acidlab.ubci_reclamations.networking.NetworkingAsyncResponse;
import com.acidlab.ubci_reclamations.networking.NetworkingHelper;


public class MainActivity extends AppCompatActivity implements NetworkingAsyncResponse {

    EditText email, password;
    Button login,signup;


    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.onUserLogin(null);


        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        context = this;

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkingHelper n = new NetworkingHelper(context);
                n.login(email.getText().toString(),password.getText().toString());
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SignUpActivity.class);
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public void onUserLogin(User user) {
        if (user == null){
            Log.e("MSG", "Failed no user");
        }else {
            Log.e("MSG", user.toString());
            Intent intent = new Intent(context, MainReclamationsActivity.class);
            context.startActivity(intent);
        }
    }

}
