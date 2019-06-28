package com.example.itemdatabaseactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Vehicle_Database extends AppCompatActivity {

    Button mButton_addVehicle;
    Button mButton_viewVehicle;
    Button mButton_EraseDatabase;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle__database);

        db = new DatabaseHelper(this);

        mButton_addVehicle = (Button) findViewById(R.id.button_addvehicle);
        mButton_viewVehicle= (Button) findViewById(R.id.button_viewvehicle);
        mButton_EraseDatabase= (Button) findViewById(R.id.button_deletevehicle);

        mButton_addVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vehicle_Database.this,AddVehicle.class);
                startActivity(intent);
            }
        });

        mButton_EraseDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.EraseDatabase();
                Toast.makeText(Vehicle_Database.this,"Please wait.... \nErasing Database....",Toast.LENGTH_LONG).show();

            }
        });

        mButton_viewVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vehicle_Database.this,ViewVehicleDatabase.class);
                startActivity(intent);
            }
        });

    }
}
