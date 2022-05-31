package com.example.testcreatensignin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FloatingAdd extends AppCompatActivity {

    private Toolbar toolbar;
    private Button addGenre, addFolder, addBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_add);

        toolbar = (Toolbar) findViewById(R.id.nav_toolbar);

        addBook = (Button) findViewById(R.id.addBookButton);
        addGenre = (Button) findViewById(R.id.addGenreButton);
        addFolder = (Button) findViewById(R.id.addFolderButton);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(FloatingAdd.this, AddNewBook.class));
            }
        });

        addFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(FloatingAdd.this, AddFolder.class));
            }
        });

        addGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(FloatingAdd.this, AddGenres.class));
            }
        });

    }
}