package com.example.dell.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;


import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    DatabaseReference databaseusers;
    ListView listviewusers;
    List<Users> userlist;
    Button btnmail;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FloatingActionButton fab=findViewById(R.id.fab);
        BottomNavigationView bottomNavigationView=(BottomNavigationView) findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.profile:
                        break;
                    case R.id.update:
                        Intent i = new Intent(HomeActivity.this, EnterDetails.class);
                        startActivity(i);
                        break;
                }
            }
        });


      // Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(toolbar);
        databaseusers = FirebaseDatabase.getInstance().getReference("Users");
        listviewusers=(ListView) findViewById(R.id.listviewusers);
        userlist=new ArrayList<>();
        logout=(Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(HomeActivity.this,SigninActivity.class);
                startActivity(in);
            }
        });
    }
    protected void onStart() {
        super.onStart();
        databaseusers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userlist.clear();
                for(DataSnapshot userSnapshot:dataSnapshot.getChildren()){
                    Users user=userSnapshot.getValue(Users.class);
                    userlist.add(user);
                }
                Userlist adapter=new Userlist(HomeActivity.this,userlist);
                listviewusers.setAdapter(adapter);
                listviewusers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override

                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i=new Intent(HomeActivity.this,EmailActivity.class);
                        startActivity(i);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
