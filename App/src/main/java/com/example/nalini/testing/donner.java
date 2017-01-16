package com.example.nalini.testing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class donner extends AppCompatActivity {

    String group;
    private EditText etxtname, etxtdob, etxtusername, etxtcontact, etxtlocation, etxtemail, etxtpassword, etxtrepassword;
    private Button btnsubmit;
    private Spinner spinner;
    ArrayAdapter<CharSequence> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donner);

        getSupportActionBar().setTitle("Donate Blood");

        etxtname = (EditText) findViewById(R.id.etxtname);
        etxtdob = (EditText) findViewById(R.id.etxtdob);
        etxtemail = (EditText) findViewById(R.id.etxtemail);
        etxtusername = (EditText) findViewById(R.id.etxtusername);
        etxtpassword =(EditText) findViewById(R.id.etxtpassword);
        etxtrepassword =(EditText) findViewById(R.id.etxtrepassword);
        etxtcontact = (EditText) findViewById(R.id.etxtcontact);
        etxtlocation = (EditText) findViewById(R.id.etxtlocation);

        btnsubmit =(Button) findViewById(R.id.btnsubmit);
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
                // Toast.makeText(donner.this, parent.getItemAtPosition(position)+"selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uname = etxtname.getText().toString();

                if (uname.length() == 0) {
                    etxtname.setError("Name not entered");
                    etxtname.requestFocus();
                    return;
                }
                String udob = etxtdob.getText().toString();

                if (udob.length() == 0) {
                    etxtdob.setError("DOB not entered");
                    etxtdob.requestFocus();
                    return;
                }
                String uemail = etxtemail.getText().toString();

                if (uemail.length() == 0) {
                    etxtemail.setError("Email not entered");
                    etxtemail.requestFocus();
                    return;
                }

                String uusername = etxtusername.getText().toString();
                if (uusername.length() == 0) {
                    etxtusername.setError("UserName not entered");
                    etxtusername.requestFocus();
                    return;
                }
                String upassword = etxtpassword.getText().toString();
                if (upassword.length() == 0) {
                    etxtpassword.setError("Password not entered");
                    etxtpassword.requestFocus();
                    return;
                }
                String urepassword = etxtrepassword.getText().toString();

                if (urepassword.length() == 0) {
                    etxtrepassword.setError("Re-Password not entered");
                    etxtrepassword.requestFocus();
                    return;
                }
                if (!etxtpassword.getText().toString().equals(etxtrepassword.getText().toString())) {
                    etxtpassword.setError("Password Not matched");
                    etxtrepassword.requestFocus();
                    return;
                }
                String ucontact = etxtcontact.getText().toString();

                if (ucontact.length() == 0) {
                    etxtcontact.setError("Contact not entered");
                    etxtcontact.requestFocus();
                    return;
                }


                String ulocation = etxtlocation.getText().toString();

                if (ulocation.length() == 0) {
                    etxtlocation.setError("Location not entered");
                    etxtlocation.requestFocus();
                    return;
                }


                String key = uusername;


                if (!upassword.equals(urepassword)) {
                    etxtpassword.setError("Password does not match");
                    etxtrepassword.setError("Password does not match");
                    return;
                }

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();

                DatabaseReference keyChildDBR = databaseReference.child("BloodGroup").child(group).child(key);
                keyChildDBR.child("uname").setValue(uname);
                keyChildDBR.child("udob").setValue(udob);
                keyChildDBR.child("uemail").setValue(uemail);
                keyChildDBR.child("uusername").setValue(uusername);
                keyChildDBR.child("upassword").setValue(upassword);
                keyChildDBR.child("ucontact").setValue(ucontact);
                keyChildDBR.child("ulocation").setValue(ulocation)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(donner.this, "Registration Completed Successfully!", Toast.LENGTH_SHORT).show();
                                etxtname.setText("");
                                etxtdob.setText("");
                                etxtemail.setText("");
                                etxtusername.setText("");
                                etxtpassword.setText("");
                                etxtrepassword.setText("");
                                etxtcontact.setText("");
                                etxtlocation.setText("");

                            }
                        });

            }
        });

    }
}







