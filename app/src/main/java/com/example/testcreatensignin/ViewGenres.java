package com.example.testcreatensignin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewGenres extends AppCompatActivity {

    //Declarations
    private FloatingActionButton fabGenres;
    private CardView poetryCardView;
    private CardView fictionCardView;
    private CardView romanceCardView;
    private CardView comedyCardView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_genres);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fabGenres = (FloatingActionButton) findViewById(R.id.fabAddGenres);
        poetryCardView = (CardView) findViewById(R.id.poetryCardView);
        fictionCardView = (CardView) findViewById(R.id.fictionCardView);
        romanceCardView = (CardView) findViewById(R.id.romanceCardView);
        comedyCardView = (CardView) findViewById(R.id.comedyCardView);

        fabGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ViewGenres.this, AddGenres.class));
            }
        });

        poetryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        fictionCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        romanceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        comedyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }
}