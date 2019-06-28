package com.example.itemdatabaseactivity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditdatabaseActivity extends AppCompatActivity {

    private Button mButtonSave,mButtonDelete;
    private EditText mEditText_Database;
    DatabaseHelper db;

    private  String selectedCode;
    private String selectedType;
    private String selectedPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdatabase);

        mEditText_Database = (EditText)findViewById(R.id.edittext_editDatabase);
        mButtonDelete =(Button) findViewById(R.id.button_DeleteDatabase);
        mButtonSave = (Button)findViewById(R.id.button_saveEditeddatabase);
        db = new DatabaseHelper(this);

        Intent receiveIntent = getIntent(); //get the intent extra from the viewVehicledatabase activity
        final int id = receiveIntent.getIntExtra("id",0);
        final String Data = receiveIntent.getStringExtra("data");

        mEditText_Database.setText(Data);

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Vechname = mEditText_Database.getText().toString();

                if (!Vechname.equals(""))
                {
                    String [] newVehData = Vechname.split("\n");
                    selectedCode = newVehData[1];
                    selectedType = newVehData[0];
                    selectedPrice = newVehData[2];
                    db.UpdateVechname(id,selectedCode,selectedType,selectedPrice);
                    Intent intent = new Intent(EditdatabaseActivity.this,ViewVehicleDatabase.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(EditdatabaseActivity.this,"Enter Details",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Vechname = mEditText_Database.getText().toString();
                String [] newVehData = Vechname.split("\n");
                selectedType = newVehData[0];
             db.DeleteVehicleDetails(id,selectedType);
             Intent intent = new Intent(EditdatabaseActivity.this,ViewVehicleDatabase.class);
                mEditText_Database.setText("");
             startActivity(intent);
            }
        });
    }
}
