package com.gipsy.kabandarasoetta;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class DetailStasiun extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_stasiun);

        final Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Stasiun KA Bandara");
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.Putih));
        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#00FFFFFF"));
    }
}
