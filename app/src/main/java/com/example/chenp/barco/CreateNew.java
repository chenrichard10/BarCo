package com.example.chenp.barco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);

        Button createSpreadsheetButton = (Button)findViewById(R.id.createSpreadsheetButton);
        createSpreadsheetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),EditSpreadsheet.class);
                startActivity(startIntent);
            }
        });

        Button backCreateNewButton = (Button)findViewById(R.id.backCreateNewButton);
        backCreateNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),MainScreen.class);
                startActivity(startIntent);
            }
        });
    }
}