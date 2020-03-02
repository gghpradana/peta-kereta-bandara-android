package com.gipsy.kabandarasoetta;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AboutActivity extends AppCompatActivity {
    ImageButton gmail, twitter;
    String twitter_user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        gmail = (ImageButton) findViewById(R.id.iconemail);
        twitter = (ImageButton) findViewById(R.id.icontwitter);
        twitter_user_name = "gipsywander";

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","gigih.alamsyah@gmail.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Aplikasi KA Bandara");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + twitter_user_name)));
                }catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/" + twitter_user_name)));
                }
            }
        });

    }
}
