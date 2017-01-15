package com.example.nalini.testing;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnNext;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnNext = (Button) findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, options.class);
                startActivity(intent);
            }
        });
    }




        @Override
        public void onBackPressed() {

            alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Confirm Exit");
            alertDialog.setMessage("Do u want to exit?");

            alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();


                }
            });

            alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    alertDialog.dismiss();
                }
            });

            alertDialog.show();


        }
        }

