package com.example.nalini.testing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class donner_profile_edit extends AppCompatActivity {

    String group, key;
    private EditText edpname, edpdob, edpemail, edpcontact, edplocation;
    private Button btnedpsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donner_profile_edit);


        edpname = (EditText) findViewById(R.id.edpname);
        edpdob = (EditText) findViewById(R.id.edpdob);
        edpemail = (EditText) findViewById(R.id.edpemail);
        edpcontact = (EditText) findViewById(R.id.edpcontact);
        edplocation = (EditText) findViewById(R.id.edplocation);

        btnedpsave = (Button) findViewById(R.id.btnedpsave);

        Bundle extra = getIntent().getExtras();
        group = extra.getString("group");
        key = extra.getString("key");
        edpname.setText(extra.getString("name"));
        edpdob.setText(extra.getString("dob"));
        edpemail.setText(extra.getString("email"));
        edpcontact.setText(extra.getString("contact"));
        edplocation.setText(extra.getString("location"));

        btnedpsave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();

                DatabaseReference keyChildDBR = databaseReference.child("BloodGroup").child(group).child(key);
                keyChildDBR.child("uname").setValue(edpname.getText().toString());
                keyChildDBR.child("udob").setValue(edpdob.getText().toString());
                keyChildDBR.child("uemail").setValue(edpemail.getText().toString());
                keyChildDBR.child("ucontact").setValue(edpcontact.getText().toString());

                keyChildDBR.child("ulocation").setValue(edplocation.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid)
                    {
                        Toast.makeText(donner_profile_edit.this, "Information Changed Successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent = new Intent(donner_profile_edit.this, donner_profile.class);
                intent.putExtra("group", ""+group);
                intent.putExtra("key", ""+key);
                startActivity(intent);
            }
        });
    }
    }

