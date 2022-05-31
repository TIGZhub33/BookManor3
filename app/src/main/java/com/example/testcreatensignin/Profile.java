package com.example.testcreatensignin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Profile extends AppCompatActivity {

    private FloatingActionButton floatingAdd;
    private Button manageAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        floatingAdd = (FloatingActionButton) findViewById(R.id.floatingAddButton);

        floatingAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Profile.this, FloatingAdd.class));
            }
        });

        manageAccountButton = (Button) findViewById(R.id.manageButton);

        manageAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Profile.this, ManageAccount.class));
            }
        });

    }
}