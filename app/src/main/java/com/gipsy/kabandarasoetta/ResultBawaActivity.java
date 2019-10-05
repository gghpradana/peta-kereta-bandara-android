package com.gipsy.kabandarasoetta;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResultBawaActivity extends AppCompatActivity {
    Button btn_ptnjk;
    TextView staspilihan, tarif, estimasi;
    String stasiun, jam;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<PilihanJadwal> pilihanJadwalList;
    private RecyclerView recyclerView;
    private PilihanJadwalAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_bawa);

        staspilihan = findViewById(R.id.viastasiun);
        tarif = findViewById(R.id.texttarif);
        estimasi = findViewById(R.id.texteta);

        staspilihan.setText(getIntent().getStringExtra("stasiun"));
        tarif.setText(getIntent().getStringExtra("tarif"));
        estimasi.setText(getIntent().getStringExtra("eta"));
        stasiun = getIntent().getExtras().getString("stasdb");
        Log.d(stasiun, "stasdb");
        jam = getIntent().getExtras().getString("jamterbang");
        Log.d(jam, "jamterbang");


        recyclerView = findViewById(R.id.rv_hasilbw);
        pilihanJadwalList = new ArrayList<>();
        mAdapter = new PilihanJadwalAdapter(getApplicationContext(), pilihanJadwalList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new
                DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

        getData();
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String url = "https://gipsygis.000webhostapp.com/api/gipsy/getjadwalkebpergi.php?stasiun=" + stasiun + "&jam=" + jam;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject data = response.getJSONObject(i);

                        PilihanJadwal pilihanJadwal = new PilihanJadwal();
                        pilihanJadwal.setPilihjadwal(data.getString(stasiun));

                        pilihanJadwalList.add(pilihanJadwal);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
                mAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ResultBawaActivity.this);
                builder.setTitle("Error!");
                builder.setMessage("No Internet Connection");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Refresh", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getData();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }
}
