package com.example.dell.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonsignin;
    private EditText editTextEmail,editTextPassword;
    private TextView register;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        editTextEmail=(EditText) findViewById(R.id.Email_signin);
        editTextPassword=(EditText) findViewById(R.id.Password_signin);
        buttonsignin=(Button) findViewById(R.id.Signin);
        register=(TextView)findViewById(R.id.textviewregister);
        progressDialog=new ProgressDialog(this);
         firebaseAuth=FirebaseAuth.getInstance();
         //if(firebaseAuth.getCurrentUser()!=null){
           //  finish();
             //startActivity(new Intent(getApplicationContext(),HomeActivity.class));

         //}
        buttonsignin.setOnClickListener(this);
        register.setOnClickListener(this);
    }
     private  void UserLogin() {
         String Email = editTextEmail.getText().toString().trim();
         String Password = editTextPassword.getText().toString().trim();
         if (TextUtils.isEmpty(Email)) {
             Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
             return;
         }
         if (!Email.endsWith("@gmail.com")) {
             Toast.makeText(this, "Please enter a valid Email", Toast.LENGTH_SHORT).show();
             return;
         }
         if (TextUtils.isEmpty(Password)) {
             Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
             return;

         }
         progressDialog.setMessage("Please wait");
         progressDialog.show();

         firebaseAuth.signInWithEmailAndPassword(Email, Password)
                 .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()){
                          finish();
                          startActivity(new Intent(SigninActivity.this,HomeActivity.class));
                      }
                      else{
                          Toast.makeText(SigninActivity.this, "User credentials are invalid", Toast.LENGTH_SHORT).show();
                      }
                        progressDialog.dismiss();
                     }
                 });

     }
    @Override
    public void onClick(View v) {
        if(v==buttonsignin){
            UserLogin();
        }
        if(v==register){
            finish();
            startActivity(new Intent(SigninActivity.this,LoginActivity.class));
        }
    }
}
