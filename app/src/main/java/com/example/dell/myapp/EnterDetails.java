package com.example.dell.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnterDetails extends AppCompatActivity {
    Button b;
    EditText Name;
    EditText From;
    EditText To,Password;
    EditText Description,email;
    DatabaseReference databaseusers;
    DatabaseReference user;
    FirebaseAuth mauth;
    Button cancel;
    FirebaseUser userdata=FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databaseusers = FirebaseDatabase.getInstance().getReference("Users");
        user=FirebaseDatabase.getInstance().getReference().child("Users").child("");
        Name = (EditText) findViewById(R.id.editText2);
        From = (EditText) findViewById(R.id.editText3);
        To = (EditText) findViewById(R.id.editText4);
        Description = (EditText) findViewById(R.id.editText5);
        email=(EditText)findViewById(R.id.editmail);
        cancel=(Button)findViewById(R.id.button3);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(EnterDetails.this,HomeActivity.class);
                startActivity(i);
            }
        });
        b = (Button) findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDetails();

            }
        });

    }

    private void userDetails() {
        String name=Name.getText().toString().trim();
        String from = From.getText().toString().trim();
        String to = To.getText().toString().trim();
        String mail=email.getText().toString().trim();
        String uid=mauth.getInstance().getCurrentUser().getUid();
        DatabaseReference myref=FirebaseDatabase.getInstance().getReference("Users");
        String description = Description.getText().toString().trim();
        if (  TextUtils.isEmpty(name)||TextUtils.isEmpty(mail)|| TextUtils.isEmpty(from) || TextUtils.isEmpty(to) || TextUtils.isEmpty(description)) {
            Toast.makeText(this, "Please fill all the required fields", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            Users user=new Users(name,from,to,mail,description);
            databaseusers.child(uid).setValue(user);
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
            Intent i1 = new Intent(EnterDetails.this, HomeActivity.class);
            startActivity(i1);

        }

    }


}


