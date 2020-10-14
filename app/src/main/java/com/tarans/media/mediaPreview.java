package com.tarans.media;

import android.content.Intent;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class mediaPreview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_preview);
        final ImageView imageView = findViewById(R.id.imageView);
        final Button next = findViewById(R.id.button2);
        Intent intent = getIntent();
        String img = intent.getStringExtra("img");
        final Uri uri = Uri.parse(img);
        imageView.setImageURI(uri);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mediaPreview.this, com.tarans.media.Export.class);
                intent.putExtra("img", uri.toString());
                startActivity(intent);
            }
        });
    }
}