package com.example.itemdatabaseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextConfPassword;
    Button mButtonSave;
    TextView mTextviewLogin;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextConfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        mButtonSave = (Button)findViewById(R.id.button_save);
        mTextviewLogin=(TextView)findViewById(R.id.textview_login);

        mTextviewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pass = mTextPassword.getText().toString().trim();
                String cnf_pass = mTextConfPassword.getText().toString().trim();

                if (user.equals("") || pass.equals("") || cnf_pass.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (pass.equals(cnf_pass)) {
                    long val = db.AddUserDetails(user, pass);
                    if (val > 0) {
                        Toast.makeText(RegisterActivity.this, "You have registered", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(moveToLogin);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                }
              }
            }
        });
    }
}
