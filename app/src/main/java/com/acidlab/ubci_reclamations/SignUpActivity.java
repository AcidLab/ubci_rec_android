package com.acidlab.ubci_reclamations;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.acidlab.ubci_reclamations.Models.User;
import com.acidlab.ubci_reclamations.Utils.Utilities;
import com.acidlab.ubci_reclamations.networking.NetworkingAsyncResponse;
import com.acidlab.ubci_reclamations.networking.NetworkingHelper;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class SignUpActivity extends AppCompatActivity implements NetworkingAsyncResponse {


    Context context;
    ProgressBar loading;

    Button confirm;

    EditText fname, lname, email, password;
    int codePoste;
    List<EditText> editTexts = new ArrayList<>();

    EditText et1, et2, et3, et4, et5, et6, et7, et8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        confirm = findViewById(R.id.confirm);
        loading = findViewById(R.id.progresseBar);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        et1 = findViewById(R.id.number1);
        et2 = findViewById(R.id.number2);
        et3 = findViewById(R.id.number3);
        et4 = findViewById(R.id.number4);
        et5 = findViewById(R.id.number5);
        et6 = findViewById(R.id.number6);
        et7 = findViewById(R.id.number7);
        et8 = findViewById(R.id.number8);

        editTexts.add(et1);
        editTexts.add(et2);
        editTexts.add(et3);
        editTexts.add(et4);
        editTexts.add(et5);
        editTexts.add(et6);
        editTexts.add(et7);
        editTexts.add(et8);


        setupInputListeners();


        context = this;
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.inscription));
        mToolbar.setNavigationIcon(R.drawable.arrow_icon);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fname.getText().toString().equals("")) {
                    if (!lname.getText().toString().equals("")) {
                        if (!email.getText().toString().equals("")) {
                            if (!password.getText().toString().equals("")) {
                                if (!(et1.getText().toString().equals("") || et2.getText().toString().equals("") || et3.getText().toString().equals("") || et4.getText().toString().equals("") || et5.getText().toString().equals("") || et6.getText().toString().equals("") || et7.getText().toString().equals("") || et8.getText().toString().equals(""))) {
                                    codePoste = Integer.parseInt(et1.getText().toString() + et2.getText().toString() + et3.getText().toString() + et4.getText().toString() + et5.getText().toString() + et6.getText().toString() + et7.getText().toString() + et8.getText().toString());
                                    loading.setVisibility(View.VISIBLE);
                                    NetworkingHelper n = new NetworkingHelper(context);
                                    n.register(fname.getText().toString(), lname.getText().toString(), email.getText().toString(), password.getText().toString(), codePoste);

                                } else {
                                    Utilities.alert(SignUpActivity.this, SweetAlertDialog.WARNING_TYPE, "Avertissement", "Votre numero de poste doit avoir 8 chiffres");
                                }
                            } else {
                                Utilities.alert(SignUpActivity.this, SweetAlertDialog.WARNING_TYPE, "Avertissement", "Vous devez saisir votre mot de passe ");
                            }
                        } else {
                            Utilities.alert(SignUpActivity.this, SweetAlertDialog.WARNING_TYPE, "Avertissement", "Vous devez mentionner votre e-mail");
                        }
                    } else {
                        Utilities.alert(SignUpActivity.this, SweetAlertDialog.WARNING_TYPE, "Avertissement", "Vous devez mentionner votre prénom");
                    }
                } else {
                    Utilities.alert(SignUpActivity.this, SweetAlertDialog.WARNING_TYPE, "Avertissement", "Vous devez mentionner votre nom");
                }
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

    private void setupInputListeners() {
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    et2.requestFocus(View.FOCUS_DOWN);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count == 0) {
                    et1.requestFocus(View.FOCUS_DOWN);
                } else {
                    if (s.length() == 1) {
                        et3.requestFocus(View.FOCUS_DOWN);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count == 0) {
                    et2.requestFocus(View.FOCUS_DOWN);
                } else {
                    if (s.length() == 1) {
                        et4.requestFocus(View.FOCUS_DOWN);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count == 0) {
                    et3.requestFocus(View.FOCUS_DOWN);
                } else {
                    if (s.length() == 1) {
                        et5.requestFocus(View.FOCUS_DOWN);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count == 0) {
                    et4.requestFocus(View.FOCUS_DOWN);
                } else {
                    if (s.length() == 1) {
                        et6.requestFocus(View.FOCUS_DOWN);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        et6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count == 0) {
                    et5.requestFocus(View.FOCUS_DOWN);
                } else {
                    if (s.length() == 1) {
                        et7.requestFocus(View.FOCUS_DOWN);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        et7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count == 0) {
                    et6.requestFocus(View.FOCUS_DOWN);
                } else {
                    if (s.length() == 1) {
                        et8.requestFocus(View.FOCUS_DOWN);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        et8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count == 0) {
                    et7.requestFocus(View.FOCUS_DOWN);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
