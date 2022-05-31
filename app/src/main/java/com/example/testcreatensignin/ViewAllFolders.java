package com.example.testcreatensignin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewAllFolders extends AppCompatActivity {

    private FloatingActionButton addFolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_folders);

        addFolder = (FloatingActionButton) findViewById(R.id.floatingAddButton);

        addFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ViewAllFolders.this, AddFolder.class));
            }
        });
    }
}