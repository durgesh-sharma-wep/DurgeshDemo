package com.example.itemdatabaseactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddVehicle extends AppCompatActivity {

    EditText mVehicleCode;
    EditText mVehicleType;
    EditText mParkingCharge;
    Button mButtonAdd;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        mVehicleCode = (EditText)findViewById(R.id.edittext_vechcode);
        mVehicleType = (EditText)findViewById(R.id.edittext_vechtype);
        mParkingCharge=(EditText)findViewById(R.id.edittext_vechPrice);
        mButtonAdd= (Button)findViewById(R.id.button_addID);

        db = new DatabaseHelper(this);

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String VehicleCode = mVehicleCode.getText().toString();
                String VehicleType = mVehicleType.getText().toString();
                String ParkingCharge = mParkingCharge.getText().toString();

                if(VehicleCode.isEmpty() || VehicleType.isEmpty() || ParkingCharge.isEmpty())
                {
                    Toast.makeText(AddVehicle.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                }
                else {
                    long val = db.AddVehicleDetails(VehicleCode, VehicleType, ParkingCharge);
                    if (val > 0)
                    {
                        Toast.makeText(AddVehicle.this, "Details Added", Toast.LENGTH_SHORT).show();
                        mVehicleCode.setText("");
                        mVehicleType.setText("");
                        mParkingCharge.setText("");

                    }
                    else
                        Toast.makeText(AddVehicle.this, "Can't Add", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
