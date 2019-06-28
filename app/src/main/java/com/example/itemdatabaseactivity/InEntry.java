package com.example.itemdatabaseactivity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class InEntry extends AppCompatActivity {

    EditText mEditTextInVechCode,mEditTextInVechNum;
    Button mButtonInVech;
    Spinner mSpinnerInVechType;
    ArrayList<String> names = new ArrayList<String>();
    ArrayAdapter<String> adaptertemp;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_entry);

        db = new DatabaseHelper(this);

        mEditTextInVechCode = (EditText)findViewById(R.id.editext_InvechCode);
        mSpinnerInVechType = (Spinner)findViewById(R.id.spinner_InvechTye);
        mEditTextInVechNum = (EditText)findViewById(R.id.editext_InvechNum);
        mButtonInVech = (Button)findViewById(R.id.button_inVech);


        Cursor data = db.GetListData();
        while(data.moveToNext())
        {
            names.add(data.getString(2));
        }
        adaptertemp = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        mSpinnerInVechType.setAdapter(adaptertemp);

        mButtonInVech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String InVechicleCode = mEditTextInVechCode.getText().toString();
               // String InVehicleType = mEditTextInVechType.getText().toString();
                String InVehicleNum = mEditTextInVechNum.getText().toString();
                String TempInVehicleType = mSpinnerInVechType.getSelectedItem().toString();
                Calendar cc = Calendar.getInstance();
                int year=cc.get(Calendar.YEAR);
                int month=cc.get(Calendar.MONTH);
                int Day = cc.get(Calendar.DAY_OF_MONTH);
                int mHour = cc.get(Calendar.HOUR_OF_DAY);
                int mMinute = cc.get(Calendar.MINUTE);
                month=month+1;
                if(InVechicleCode.equals("") || TempInVehicleType.equals("") || InVehicleNum.equals(""))
                {
                    Toast.makeText(InEntry.this, "Pls Enter All Details", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        long val = db.InVehicleEntry(InVechicleCode,TempInVehicleType,InVehicleNum,Day,month,year,mHour,mMinute);
                        if(val>0) {
                            Toast.makeText(InEntry.this, "Vehicle IN ", Toast.LENGTH_SHORT).show();
                            mEditTextInVechCode.setText("");
                           // mEditTextInVechType.setText("");
                            mEditTextInVechNum.setText("");
                        }
                        else
                            Toast.makeText(InEntry.this, "Vehicle Can't Parked", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
}
