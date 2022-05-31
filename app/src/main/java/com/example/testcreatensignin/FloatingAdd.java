package com.example.testcreatensignin;

import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD

import android.os.Bundle;

<<<<<<< HEAD:app/src/main/java/com/example/testcreatensignin/FloatingAdd.java
public class FloatingAdd extends AppCompatActivity {
=======
public class ProfileManager extends AppCompatActivity {
>>>>>>> 179171be5464290f06b6fb696628ec987be3c970:app/src/main/java/com/example/testcreatensignin/ProfileManager.java
=======
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FloatingAdd extends AppCompatActivity {

    private Toolbar toolbar;
    private Button addGenre, addFolder, addBook;
>>>>>>> 179171be5464290f06b6fb696628ec987be3c970

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_profile_manager);
=======
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

>>>>>>> 179171be5464290f06b6fb696628ec987be3c970
    }
}