package com.example.itemdatabaseactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {
  Button mMenuButton;
  Button mInEntryButton;
  Button mOutEntrybutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mMenuButton = (Button)findViewById(R.id.button_menu);
        mInEntryButton = (Button)findViewById(R.id.inentry_button);
        mOutEntrybutton = (Button)findViewById(R.id.outentry_button);

        mMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this,Menu.class);
                startActivity(intent);

            }
        });

        mInEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MoveToInEntry = new Intent(HomeScreen.this,InEntry.class);
                startActivity(MoveToInEntry);
            }
        });
    }
}
