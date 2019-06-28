package com.example.itemdatabaseactivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    ListView listView;
    String mTitle[] = {"Reports","Database Management","Settings"};
    int image[]={R.drawable.reports,R.drawable.database,R.drawable.setting};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        listView=findViewById(R.id.listview_menu);

        MenuCutomList adapter=new MenuCutomList(this,mTitle,image);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position==1)
                {
                    Intent movetodatabase = new Intent(Menu.this,Vehicle_Database.class);
                    startActivity(movetodatabase);
                }

            }
        });
    }

    class MenuCutomList extends ArrayAdapter<String>
    {
        Context context;
        String rTitle[];
        int rImage[];

        MenuCutomList(Context c, String title[],int img[]) {
            super(c,R.layout.custom_listview_menu,R.id.textview1_menu,title);
            this.context=c;
            this.rTitle=title;
            this.rImage=img;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowforlist = layoutInflater.inflate(R.layout.custom_listview_menu,parent,false);
            ImageView images= rowforlist.findViewById(R.id.image_menu);
            TextView mTitle = rowforlist.findViewById(R.id.textview1_menu);

            images.setImageResource(rImage[position]);
            mTitle.setText(rTitle[position]);
            return rowforlist;
        }
    }
}

