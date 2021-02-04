package com.example.testtest;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Zone1 extends AppCompatActivity {

    ImageView redpoint1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone1);

        redpoint1 = (ImageView) findViewById(R.id.redpoint1);
        redpoint1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"XXX번지의 XXXX",Toast.LENGTH_SHORT).show();
            }
        });
    }
    }