package com.example.nalini.testing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class donner_login extends AppCompatActivity {

    private Button btnlogin;
    private EditText etxtemail, etxtpassword;
    private TextView txtmsg;
    String group;
    String key;
    private Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donner_login);

       // getSupportActionBar().setTitle("");


        etxtemail = (EditText) findViewById(R.id.etxtemail);
        etxtpassword = (EditText) findViewById(R.id.etxtpassword);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        txtmsg = (TextView) findViewById(R.id.txtmsg);

        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.Blood_Groups,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                group = (String) parent.getItemAtPosition(position);
                Toast.makeText(donner_login.this, parent.getItemAtPosition(position)+"selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });



        btnlogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String lusername = etxtemail.getText().toString();
                final String lpassword = etxtpassword.getText().toString();

                key = lusername;

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference rootDBR = firebaseDatabase.getReference();

                DatabaseReference childDBR = rootDBR.child("BloodGroup").child(group).child(key);
                childDBR.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        String serverusername = dataSnapshot.child("uusername").getValue().toString();
                        String serverpassword = dataSnapshot.child("upassword").getValue().toString();

                        if(serverusername.equals(lusername) && serverpassword.equals(lpassword))
                        {
                            Toast.makeText(donner_login.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(donner_login.this, donner_profile.class);
                            intent.putExtra("group", ""+group);
                            intent.putExtra("key", ""+key);
                            startActivity(intent);
                        }

                        else
                        {
                            txtmsg.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError)
                    {

                        Toast.makeText(donner_login.this, "Connection Failed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });



    }
}
