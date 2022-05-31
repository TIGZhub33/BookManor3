package com.example.testcreatensignin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewGenres extends AppCompatActivity {

    private FloatingActionButton fabGenres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_genres);

        fabGenres = (FloatingActionButton) findViewById(R.id.fabAddGenres);

        fabGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ViewGenres.this, AddGenres.class));
            }
        });
    }
}