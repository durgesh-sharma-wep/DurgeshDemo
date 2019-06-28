package com.example.itemdatabaseactivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewVehicleDatabase extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vehicle_database);

        db= new DatabaseHelper(this);

        ListView listView = (ListView) findViewById(R.id.listview_vehdatabase);

        ArrayList<String> thislist = new ArrayList<>();
        final Cursor data = db.GetListData();

        if(data.getCount()==0)
        {
            Toast.makeText(ViewVehicleDatabase.this, "Vehicle Database is Empty", Toast.LENGTH_LONG).show();
        }
        else
        {
            while (data.moveToNext())
            {  //get the value from database in column 1 and then add it to the Arraylist
                thislist.add(data.getString(2) + "\n" + data.getString(1) +"\n" + data.getString(3));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,thislist);
                listView.setAdapter(listAdapter);

                // set an OnItemClickListener to the Listview
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> AdapterView, View view, int position, long id) {
                     String VehData = AdapterView.getItemAtPosition(position).toString();
                     String [] Data = VehData.split("\n");

                      String  selectedType = Data[0];
                      int vechID = -1;
                      Cursor data = db.GetListID(selectedType);//get id associated with name
                        data.moveToFirst();
                        if (data.getCount() > 0)
                        {
                            vechID = data.getInt(0);
                        }
                        Intent editScreenIntent = new Intent(ViewVehicleDatabase.this,EditdatabaseActivity.class);
                        editScreenIntent.putExtra("id", vechID);
                        editScreenIntent.putExtra("data",VehData);
                        startActivity(editScreenIntent);

                    }
                });
            }
        }
    }
}
