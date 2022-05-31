package com.example.testcreatensignin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddGenres extends AppCompatActivity {

    private EditText genreName, noBooksGoal;
    private Button insertIcon, createGenre;

    //private String[] genres = {"Poetry", "Fiction", "Romance", "Comedy"};

    private DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_genres);

        genreName = (EditText) findViewById(R.id.editTextGenreName);
        noBooksGoal = (EditText) findViewById(R.id.editTextNumBooks);

        insertIcon = (Button) findViewById(R.id.genreIconButton);
        createGenre = (Button) findViewById(R.id.createGenreButton);

        dbReference = FirebaseDatabase.getInstance().getReference("Genres");

        createGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createNewGenre();
            }
        });

    }

    public void createNewGenre(){

        String cGenreName = genreName.getText().toString();

        /*
        Books book = new Books(cTitle, cAuthor, cIllustrator, cNoPages, cPageLastRead, cDateAdded);

        dbReference.push().setValue(book);

        Toast.makeText(AddNewBook.this, "Data inserted!  :)", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(AddNewBook.this, ViewBooks.class));
         */

        Genres genre = new Genres(cGenreName);

        dbReference.push().setValue(genre);

        Toast.makeText(AddGenres.this, "Genre created successfully!", Toast.LENGTH_SHORT).show();

    }
}