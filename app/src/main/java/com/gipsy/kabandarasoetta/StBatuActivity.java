package com.gipsy.kabandarasoetta;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

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
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.relex.circleindicator.CircleIndicator;

public class StBatuActivity extends AppCompatActivity implements OnMapReadyCallback {

    CollapsingToolbarLayout collapsingToolbarLayout;
    private GoogleMap mMap;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private GamBatAdapter gamBatAdapter;
    int currentPage = 0, NUM_PAGES = 8;
    private TextView stasiunnama, stasiunalamat, stasiunkoordinat, stasiunfasilitas;
    String id, nama, alamat, koordinat, fasilitas, lintang, bujur;
    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st_batu);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapbat);
        mapFragment.getMapAsync(this);

        final Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Stasiun KA Bandara");
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

        gamBatAdapter = new GamBatAdapter(this);
        viewPager = findViewById(R.id.vpgambar);
        viewPager.setAdapter(gamBatAdapter);
        circleIndicator = findViewById(R.id.bulet);
        circleIndicator.setViewPager(viewPager);

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
                handler.postDelayed(this, 5000);
            }
        };

        handler.postDelayed(update, 500);

        stasiunnama = (TextView) findViewById(R.id.textnamastasiun);
        stasiunalamat = (TextView) findViewById(R.id.textalamatstasiun);
        stasiunkoordinat = (TextView) findViewById(R.id.textKoordinat);
        stasiunfasilitas = (TextView) findViewById(R.id.textfasilitas);

        getBatu();

    }

    private void getBatu() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String url = "https://gipsygis.000webhostapp.com/api/gipsy/getbatu.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject data = response.getJSONObject(i);
                                id = data.getString("ID_STASIUN");

                                latLng = new LatLng(data.getDouble("LINTANG"),
                                        data.getDouble("BUJUR"));
                                nama = data.getString("NAMA_STASIUN");
                                alamat = data.getString("ALAMAT");
                                lintang = data.getString("LINTANG");
                                bujur = data.getString("BUJUR");
                                fasilitas = data.getString("FASILITAS");

                                stasiunnama.setText(nama);
                                stasiunalamat.setText(alamat);
                                stasiunkoordinat.setText("Lat: "+lintang+"\n"+"Long: "+bujur);
                                stasiunfasilitas.setText(fasilitas.replace("\\n ","\n"));

                                mMap.addMarker(new MarkerOptions()
                                        .position(latLng)
                                        .title(nama)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pinlocationpergi24px)));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(StBatuActivity.this);
                        builder.setTitle("Error!");
                        builder.setMessage("No Internet Connection");
                        builder.setIcon(android.R.drawable.ic_dialog_alert);
                        builder.setPositiveButton("Refresh", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getBatu();
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

    }
}
