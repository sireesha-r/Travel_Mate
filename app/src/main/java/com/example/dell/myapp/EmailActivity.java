package com.example.dell.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmailActivity extends AppCompatActivity {
    private EditText emailto,emailsubject,emailmessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        emailto=findViewById(R.id.toemail);
        emailsubject=findViewById(R.id.subject);
        emailmessage=findViewById(R.id.message);
        Button btnsend=findViewById(R.id.send);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              sendMail();
            }
        });
    }
    private void sendMail() {
        String recipientList = emailto.getText().toString();
        String[] recipients = recipientList.split(",");

        String subject = emailsubject.getText().toString();
        String message = emailmessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }



}
