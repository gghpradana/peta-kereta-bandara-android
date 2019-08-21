package com.gipsy.kabandarasoetta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListkaActivity extends AppCompatActivity implements StasiunAdapter.OnNoteListener{
    private List<Stasiun> stasiunList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StasiunAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listka);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new StasiunAdapter(stasiunList, this);

        // vertical RecyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        // divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareStasiunData();
    }

    private void  prepareStasiunData() {
        Stasiun stasiun = new Stasiun("Stasiun Bekasi");
        stasiunList.add(stasiun);

        stasiun = new Stasiun("Stasiun BNI City");
        stasiunList.add(stasiun);

        stasiun = new Stasiun("Stasiun Duri");
        stasiunList.add(stasiun);

        stasiun = new Stasiun("Stasiun Batu Ceper");
        stasiunList.add(stasiun);

        stasiun = new Stasiun("Stasiun Bandara Internasional Soekarno-Hatta");
        stasiunList.add(stasiun);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, DetailStasiun.class);
        startActivity(intent);
    }
}
