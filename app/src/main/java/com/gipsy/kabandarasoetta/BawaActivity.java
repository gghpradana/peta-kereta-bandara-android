package com.gipsy.kabandarasoetta;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BawaActivity extends AppCompatActivity {
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    EditText tglterbang, jamterbang;
    Button go_btn;
    String staspilihan, tarif, stasdb, eta, tbl, map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bawa);

        final Spinner mySpinner = (Spinner) findViewById(R.id.spinnerstas);

        List<String> items = new ArrayList<>();
        items.add(0, "-Pilih-");
        items.add("Stasiun Bekasi");
        items.add("Stasiun BNI City");
        items.add("Stasiun Duri");
        items.add("Stasiun Batu Ceper");

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                switch (position) {
                    case 0: staspilihan = adapterView.getItemAtPosition(position).toString();
                        tarif = "null";
                        stasdb = "null";
                        eta = "null";
                        map = "null";
                        break;
                    case 1: staspilihan = adapterView.getItemAtPosition(position).toString();
                        tarif = "Rp 100.000";
                        stasdb = "BEKASI";
                        eta = "90 Menit";
                        tbl = "bekasi_keberangkatan_pergi";
                        map = "Stasiun+Bekasi";
                        break;
                    case 2: staspilihan = adapterView.getItemAtPosition(position).toString();
                        tarif = "Rp 70.000";
                        stasdb = "BNI_CITY";
                        eta = "46 Menit";
                        tbl = "jadwal_keberangkatan_pergi";
                        map = "Stasiun+BNI+City";
                        break;
                    case 3: staspilihan = adapterView.getItemAtPosition(position).toString();
                        tarif = "Rp 70.000";
                        stasdb = "DURI";
                        eta = "31 Menit";
                        tbl = "jadwal_keberangkatan_pergi";
                        map = "Stasiun+Duri";
                        break;
                    case 4: staspilihan = adapterView.getItemAtPosition(position).toString();
                        tarif = "Rp 35.000";
                        stasdb = "BATU_CEPER";
                        eta = "20 Menit";
                        tbl = "jadwal_keberangkatan_pergi";
                        map = "Stasiun+Batu+Ceper";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        go_btn = findViewById(R.id.go_btn);
        tglterbang = findViewById(R.id.tglterbang);
        jamterbang = findViewById(R.id.jamterbang);

        go_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(tglterbang.getText())) {
                    Toast.makeText(getApplicationContext(), "Tanggal tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    tglterbang.setError("Harap Masukkan Tanggal");
                } else if (TextUtils.isEmpty((jamterbang.getText()))){
                    Toast.makeText(getApplicationContext(), "Jam tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    jamterbang.setError("Harap Masukkan Tanggal");
                } else if (mySpinner.getSelectedItem().toString().matches("-Pilih-")){
                    Toast.makeText(getApplicationContext(), "Stasiun belum dipilih", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(BawaActivity.this, ResultBawaActivity.class);
                    intent.putExtra("tglterbang", tglterbang.getText().toString());
                    intent.putExtra("jamterbang", jamterbang.getText().toString());
                    intent.putExtra("stasiun", staspilihan);
                    intent.putExtra("tarif", tarif);
                    intent.putExtra("eta", eta);
                    intent.putExtra("stasdb", stasdb);
                    intent.putExtra("tabel", tbl);
                    intent.putExtra("map", map);
                    startActivity(intent);
                }
            }
        });

        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }
        };

        tglterbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                new DatePickerDialog(BawaActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        jamterbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(BawaActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        jamterbang.setText(String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinute));

                    }
                    }, hour, minute, true);//Yes 24 hour time

                mTimePicker.show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tglterbang.setText(sdf.format(myCalendar.getTime()));
    }
}
