package com.example.nalini.testing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class donner_profile extends AppCompatActivity {

    String group, key;
    private TextView dpname, dpdob, dpusername, dpemail, dpcontact, dplocation;
    private Button btndpedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donner_profile);


        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorRed));
        }

        getSupportActionBar().setTitle("Donate Blood");

        dpname = (TextView) findViewById(R.id.dpname);
        dpdob = (TextView) findViewById(R.id.dpdob);
        dpusername = (TextView) findViewById(R.id.dpusername);
        dpemail = (TextView) findViewById(R.id.dpemail);
        dpcontact = (TextView) findViewById(R.id.dpcontact);
        dplocation = (TextView) findViewById(R.id.dplocation);
        btndpedit = (Button) findViewById(R.id.btndpedit);

        Bundle extra = getIntent().getExtras();
        group = extra.getString("group");
        key = extra.getString("key");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference rootDBR = firebaseDatabase.getReference();

        DatabaseReference childDBR = rootDBR.child("BloodGroup").child(group).child(key);
        childDBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                dpname.setText(dataSnapshot.child("uname").getValue().toString());
                dpdob.setText(dataSnapshot.child("udob").getValue().toString());
                dpusername.setText(dataSnapshot.child("uusername").getValue().toString());
                dpemail.setText(dataSnapshot.child("uemail").getValue().toString());
                dpcontact.setText(dataSnapshot.child("ucontact").getValue().toString());
                dplocation.setText(dataSnapshot.child("ulocation").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btndpedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(donner_profile.this, donner_profile_edit.class);
                intent.putExtra("group", ""+group);
                intent.putExtra("key", ""+key);
                intent.putExtra("name", ""+dpname.getText().toString());
                intent.putExtra("dob", ""+dpdob.getText().toString());
                intent.putExtra("email", ""+dpemail.getText().toString());
                intent.putExtra("contact", ""+dpcontact.getText().toString());
                intent.putExtra("location", ""+dplocation.getText().toString());
                startActivity(intent);
            }
        });
    }
}



