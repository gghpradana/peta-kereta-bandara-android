package com.gipsy.kabandarasoetta;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.data.kml.KmlLayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BniBanActivity extends AppCompatActivity implements OnMapReadyCallback,RBniBanAdapter.OnNoteListener {

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<RBniBan> rBniBanList;
    private RecyclerView recyclerView;
    private RBniBanAdapter mAdapter;

    CollapsingToolbarLayout collapsingToolbarLayout;
    private GoogleMap mMap;
    private LatLngBounds wilayah;
    LatLng latLng;
    String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bni_ban);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapbniban);
        mapFragment.getMapAsync(this);

        final Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Rute KA Bandara");
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.Putih));
        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#00FFFFFF"));

        AppBarLayout mAppBarLayout = (AppBarLayout)findViewById(R.id.appbar);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = new AppBarLayout.Behavior();
        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
            @Override
            public boolean canDrag(AppBarLayout appBarLayout) {
                return false;
            }
        });
        params.setBehavior(behavior);

        recyclerView = (RecyclerView) findViewById(R.id.rv_bniban);

        rBniBanList = new ArrayList<>();
        mAdapter = new RBniBanAdapter(getApplicationContext(), rBniBanList, this);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new
                DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

        wilayah = new LatLngBounds(new LatLng(-6.201566, 106.652243), new LatLng(-6.127407, 106.819694));

        getData();
    }
    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String url = "https://gipsygis.000webhostapp.com/api/gipsy/getbniban.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject data = response.getJSONObject(i);

                        nama = data.getString("NAMA_STASIUN");
                        latLng = new LatLng(data.getDouble("LINTANG"),
                                data.getDouble("BUJUR"));

                        mMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title(nama)
                                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pinlocationpergi24px)));

                        RBniBan rBniBan = new RBniBan();
                        rBniBan.setStopanbniban(data.getString("NAMA_STASIUN"));

                        rBniBanList.add(rBniBan);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(BniBanActivity.this);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(wilayah.getCenter(), 11));

        try {
            KmlLayer kmlLayer = new KmlLayer(mMap, R.raw.bniban, getApplicationContext());
            kmlLayer.addLayerToMap();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent;
        switch (position) {
            //first item
            case 0:
                intent = new Intent(BniBanActivity.this, JadBni2Activity.class);
                intent.putExtra("url", "https://gipsygis.000webhostapp.com/api/gipsy/getjadbnipergi.php");
                startActivity(intent);
                break;
            //second item
            case 1:
                intent = new Intent(BniBanActivity.this, JadDurActivity.class);
                intent.putExtra("url", "https://gipsygis.000webhostapp.com/api/gipsy/getjaddurpergi.php");
                startActivity(intent);
                break;
            //third item
            case 2:
                intent = new Intent(BniBanActivity.this, JadBatActivity.class);
                intent.putExtra("url", "https://gipsygis.000webhostapp.com/api/gipsy/getjadbatpergi.php");
                startActivity(intent);
                break;
            //fourth item
            case 3:
                intent = new Intent(BniBanActivity.this, JadBanActivity.class);
                intent.putExtra("url", "https://gipsygis.000webhostapp.com/api/gipsy/getjadbanpergi.php");
                startActivity(intent);
                break;

        }
    }
}
