package com.acidlab.ubci_reclamations;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.acidlab.ubci_reclamations.Adapters.PageAdapter;
import com.acidlab.ubci_reclamations.Models.User;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainReclamationsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    TabItem tabEnCours;
    TabItem tabEnAttente;
    Context context;




    Button ajouterReclamation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reclamations);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        context = this;
        tabLayout = findViewById(R.id.tablayout);
        tabEnCours = findViewById(R.id.encours);
        tabEnAttente = findViewById(R.id.attente);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.setupWithViewPager(viewPager);


        ajouterReclamation = findViewById(R.id.ajouter_reclamation);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(pageAdapter);


        ajouterReclamation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainReclamationsActivity.this, AjoutReclamationActivity.class);
                MainReclamationsActivity.this.startActivity(intent);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                toolbar.setBackgroundColor(ContextCompat.getColor(MainReclamationsActivity.this,
                        R.color.ubcdi_nuance_1));
                tabLayout.setBackgroundColor(ContextCompat.getColor(MainReclamationsActivity.this,
                        R.color.ubcdi_nuance_1));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(MainReclamationsActivity.this,
                            R.color.ubcdi_nuance_1));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            SweetAlertDialog alertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
            alertDialog.setTitleText("Avertissement");
            alertDialog.setContentText("\"êtes-vous sûrs de vouloir vous déconnecter\"");
            alertDialog.setConfirmText("Oui");
            alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    Intent intent = new Intent(sweetAlertDialog.getContext(), MainActivity.class);
                    sweetAlertDialog.getContext().startActivity(intent);
                    User.deleteUser(sweetAlertDialog.getContext());
                    finish();
                }
            });
            alertDialog.setCancelText("Non");
            alertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.cancel();
                }
            });
            alertDialog.show();



            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
