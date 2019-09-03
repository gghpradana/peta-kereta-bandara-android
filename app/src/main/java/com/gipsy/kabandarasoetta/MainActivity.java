package com.gipsy.kabandarasoetta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button aboutme;
    LinearLayout KA_btn;
    LinearLayout rute_btn;
    LinearLayout peta_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutme = (Button) findViewById(R.id.aboutme);
        KA_btn = (LinearLayout) findViewById(R.id.KA_btn);
        rute_btn = (LinearLayout) findViewById(R.id.rute_btn);
        peta_btn = (LinearLayout) findViewById(R.id.peta_btn);

        aboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AM = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(AM);
            }
        });

        KA_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent KA = new Intent(MainActivity.this, ListkaActivity.class);
                startActivity(KA);
            }
        });

        rute_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListruteActivity.class);
                startActivity(intent);
            }
        });
        peta_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }

}
