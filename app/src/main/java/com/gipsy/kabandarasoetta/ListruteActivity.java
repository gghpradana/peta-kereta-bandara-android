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
        Rute rute = new Rute("Bekasi - Bandara");
        ruteList.add(rute);

        rute = new Rute("Bandara - Bekasi");
        ruteList.add(rute);

        rute = new Rute("BNI City - Bandara");
        ruteList.add(rute);

        rute = new Rute("Bandara - BNI City");
        ruteList.add(rute);

      /*  rute = new Rute("BNI City - Bandara");
        ruteList.add(rute);

        rute = new Rute("BNI CIty - Duri");
        ruteList.add(rute);

        rute = new Rute("BNI City - Batu Ceper");
        ruteList.add(rute);

        rute = new Rute("Duri - Bandara");
        ruteList.add(rute);

        rute = new Rute("Duri - Batu Ceper");
        ruteList.add(rute);

        rute = new Rute("Batu Ceper - Bandara");
        ruteList.add(rute);
        */
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent;
        switch (position) {
            //first item
            case 0:
                intent = new Intent(ListruteActivity.this, BksBanActivity.class);
                startActivity(intent);
                break;
            //second item
            case 1:
                intent = new Intent(ListruteActivity.this, BanBksActivity.class);
                startActivity(intent);
                break;
            //third item
     /*       case 2:
                intent = new Intent(ListruteActivity.this, BksDurActivity.class);
                startActivity(intent);
                break;
            //fourth item
            case 3:
                intent = new Intent(ListruteActivity.this, BksBatActivity.class);
                startActivity(intent);
                break;
            //fifth item
            case 4:
                intent = new Intent(ListruteActivity.this, BniBanActivity.class);
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(ListruteActivity.this, BniDurActivity.class);
                startActivity(intent);
                break;
            case 6:
                intent = new Intent(ListruteActivity.this, BniBatActivity.class);
                startActivity(intent);
                break;
            case 7:
                intent = new Intent(ListruteActivity.this, DurBanActivity.class);
                startActivity(intent);
                break;
            case 8:
                intent = new Intent(ListruteActivity.this, DurBatActivity.class);
                startActivity(intent);
                break;
            case 9:
                intent = new Intent(ListruteActivity.this, BatBanActivity.class);
                startActivity(intent);
                break;
      */  }
    }
}
