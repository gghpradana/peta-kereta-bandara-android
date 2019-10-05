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

public class JadBnir2Activity extends AppCompatActivity {
    private LinearLayoutManager linearLayoutManager1;
    private DividerItemDecoration dividerItemDecoration1;
    private List<JadKed> jadKedList;
    private RecyclerView recyclerView1;
    private JadKedAdapter mAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jad_bnir2);

        recyclerView1 = findViewById(R.id.rv_jadbni2ked);
        jadKedList = new ArrayList<>();
        mAdapter1 = new JadKedAdapter(getApplicationContext(), jadKedList);

        linearLayoutManager1 = new LinearLayoutManager(recyclerView1.getContext());
        linearLayoutManager1.setOrientation(linearLayoutManager1.VERTICAL);
        dividerItemDecoration1 = new
                DividerItemDecoration(recyclerView1.getContext(),linearLayoutManager1.getOrientation());

        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        recyclerView1.addItemDecoration(dividerItemDecoration1);
        recyclerView1.setAdapter(mAdapter1);

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

                        JadKed jadKed = new JadKed();
                        jadKed.setJadwalKed(data.getString("KEDATANGAN").replace(":00"," Tiba dari ST. Duri"));

                        jadKedList.add(jadKed);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
                mAdapter1.notifyDataSetChanged();
                progressDialog.dismiss();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(JadBnir2Activity.this);
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
