package com.gipsy.kabandarasoetta;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class JadDurrActivity extends AppCompatActivity {
    private LinearLayoutManager linearLayoutManager, linearLayoutManager1;
    private DividerItemDecoration dividerItemDecoration, dividerItemDecoration1;
    private List<JadKeb> jadKebList;
    private List<JadKed> jadKedList;
    private RecyclerView recyclerView, recyclerView1;
    private JadKebAdapter mAdapter;
    private JadKedAdapter mAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jad_durr);

        recyclerView = findViewById(R.id.rv_jaddur2keb);
        recyclerView1 = findViewById(R.id.rv_jaddur2ked);
        jadKebList = new ArrayList<>();
        jadKedList = new ArrayList<>();
        mAdapter = new JadKebAdapter(getApplicationContext(), jadKebList);
        mAdapter1 = new JadKedAdapter(getApplicationContext(), jadKedList);

        linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager1 = new LinearLayoutManager(recyclerView1.getContext());
        linearLayoutManager1.setOrientation(linearLayoutManager.VERTICAL);
        dividerItemDecoration = new
                DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
        dividerItemDecoration1 = new
                DividerItemDecoration(recyclerView1.getContext(),linearLayoutManager1.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        recyclerView1.addItemDecoration(dividerItemDecoration1);
        recyclerView1.setAdapter(mAdapter1);

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView1.setNestedScrollingEnabled(false);

        getData();
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String url = getIntent().getExtras().getString("url");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject data = response.getJSONObject(i);

                        JadKeb jadKeb = new JadKeb();
                        jadKeb.setJadwalKeb(data.getString("KEBERANGKATAN").replace(":00", " Menuju ST. Bekasi"));

                        JadKed jadKed = new JadKed();
                        jadKed.setJadwalKed(data.getString("KEDATANGAN").replace(":00"," Tiba dari ST. Batu Ceper"));

                        jadKebList.add(jadKeb);
                        jadKedList.add(jadKed);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
                mAdapter.notifyDataSetChanged();
                mAdapter1.notifyDataSetChanged();
                progressDialog.dismiss();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(JadDurrActivity.this);
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
