package com.tarans.media;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Export extends AppCompatActivity {
    String fname;
    FileOutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
        final Button Export = findViewById(R.id.button3);
        final Button imposter = findViewById(R.id.button4);
        final ImageView iv = findViewById(R.id.imageView2);
        Intent intent = getIntent();
        final String img = intent.getStringExtra("img");
        final Uri uri = Uri.parse(img);
        iv.setImageURI(uri);

        Export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Export.setVisibility(View.INVISIBLE);
                imposter.setVisibility(View.VISIBLE);

                AlertDialog.Builder builder = new AlertDialog.Builder(com.tarans.media.Export.this);
                builder.setTitle("File Name");

// Set up the input
                final EditText input = new EditText(com.tarans.media.Export.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setHint("Enter the name of your file here.");
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fname = input.getText().toString();
                    }
                });

                builder.show();
            }
        });
        imposter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable drawable = (BitmapDrawable) iv.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                File fpath = Environment.getExternalStorageDirectory();
                File dir = new File(fpath.getAbsolutePath()+"/Media of mine/");
                dir.mkdir();
                File file = new File(dir, System.currentTimeMillis()+".jpg");
                try{
                    outputStream = getApplicationContext().openFileOutput(fname, Context.MODE_PRIVATE);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Saved?", Toast.LENGTH_SHORT).show();
                try {
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}