package com.example.nalini.testing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class options extends AppCompatActivity {

    private Button btndonner, btnneeder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);


        btndonner = (Button) findViewById(R.id.btndonner);
        btnneeder = (Button) findViewById(R.id.btnneeder);

        btndonner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(options.this,donner_option.class);
                startActivity(intent);
            }
        });
        btnneeder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(options.this,needer.class);
                startActivity(intent);
            }
        });
    }
    }

