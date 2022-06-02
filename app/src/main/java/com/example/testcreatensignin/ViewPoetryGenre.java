package com.example.testcreatensignin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewPoetryGenre extends AppCompatActivity {

    private FloatingActionButton fabGenres;

    private ListView testList;
    private List<GenreInfo> genreList;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_poetry_genre);

        testList = (ListView) findViewById(R.id.lstGenres);
        genreList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Genres");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                genreList.clear();

                for (DataSnapshot genreSnapshot : snapshot.getChildren()){

                    GenreInfo genre = genreSnapshot.getValue(GenreInfo.class);
                    genreList.add(genre);

                    //GenreAdapter adapter = new GenreAdapter(ViewPoetryGenre.this, R.layout.item_genre, genreList);
                    //testList.setAdapter(adapter);
                }

                GenreAdapter adapter = new GenreAdapter(ViewPoetryGenre.this, genreList);
                testList.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //testList.setOnItemClickListener(clickHandler);

        fabGenres = (FloatingActionButton) findViewById(R.id.fabAddGenres);

        fabGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ViewPoetryGenre.this, AddGenres.class));
            }
        });

        /*
                testList.setClickable(true);
                testList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        if(i == 0){
                            //view folders
                            startActivity(new Intent(ViewPoetryGenre.this, ViewAllFolders.class));
                        }
                        else{

                            Toast.makeText(ViewPoetryGenre.this, "No folders currently in this genre.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                 */

    }

    /*
    private AdapterView.OnItemClickListener clickHandler = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            startActivity(new Intent(ViewPoetryGenre.this, ViewAllFolders.class));
        }
    };
     */
}