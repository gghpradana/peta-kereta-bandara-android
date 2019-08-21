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

public class ListruteActivity extends AppCompatActivity implements RuteAdapter.OnNoteListener {
    private List<Rute> ruteList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RuteAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listrute);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        mAdapter = new RuteAdapter(ruteList, this);

        // vertical RecyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        // divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareRuteData();
    }

    private void prepareRuteData() {
        Rute rute = new Rute("Bandara - Bekasi");
        ruteList.add(rute);

        rute = new Rute("Bandara - BNI City");
        ruteList.add(rute);

        rute = new Rute("Bandara - Duri");
        ruteList.add(rute);

        rute = new Rute("Bandara - Batu Ceper");
        ruteList.add(rute);

        rute = new Rute("Batu Ceper - Bekasi");
        ruteList.add(rute);

        rute = new Rute("Batu Ceper - BNI City");
        ruteList.add(rute);

        rute = new Rute("Batu Ceper - Duri");
        ruteList.add(rute);

        rute = new Rute("Duri - Bekasi");
        ruteList.add(rute);

        rute = new Rute("Duri - BNI City");
        ruteList.add(rute);

        rute = new Rute("BNI City - Bekasi");
        ruteList.add(rute);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this,DetailRuteActivity.class);
        startActivity(intent);
    }
}
