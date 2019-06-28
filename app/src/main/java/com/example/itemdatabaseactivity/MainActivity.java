package com.example.itemdatabaseactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLoggin;
    TextView mTextviewRgister;


    DatabaseHelper db;

    @ButterKnife(R.id.button_addID)
    Button btnAddId;
    @ButterKfine(R.id.button_addID)
    TextView btnAddId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db= new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mButtonLoggin = (Button)findViewById(R.id.button_login);
        mTextviewRgister=(TextView)findViewById(R.id.textview_register);


        mTextviewRgister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        mButtonLoggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pass = mTextPassword.getText().toString().trim();
                if (user.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Pleas e Enter All Details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean res = db.checkUser(user, pass);
                    if (res == true) {
                        Toast.makeText(MainActivity.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                        mTextUsername.setText("");
                        mTextPassword.setText("");
                        Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Please Enter Correct Details", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
