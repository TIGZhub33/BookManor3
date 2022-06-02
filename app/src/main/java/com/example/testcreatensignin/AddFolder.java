package com.example.testcreatensignin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddFolder extends AppCompatActivity {

    private AutoCompleteTextView genreSelection;
    private String[] genres = {"Poetry", "Fiction", "Romance", "Comedy"};
    private Button addFolder;
    private EditText folderName;

    private ArrayAdapter<String> adapterItem1;

    private DatabaseReference dbReference, dbReferencePoetry, dbReferenceFiction, dbReferenceRomance, dbReferenceComedy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_folder);

        genreSelection = (AutoCompleteTextView) findViewById(R.id.autoTxtGenreSelection);
        folderName = (EditText) findViewById(R.id.customFolderName);
        addFolder = (Button) findViewById(R.id.newFolderButton);

        adapterItem1 = new ArrayAdapter<String>(this, R.layout.genre_item, genres);

        genreSelection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String genre = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(AddFolder.this, "Genre " + genre, Toast.LENGTH_SHORT).show();

                if(genre == "Poetry"){
                    dbReference = FirebaseDatabase.getInstance().getReference("Genres/Poetry");
                }
                if(genre == "Romance"){
                    dbReference = FirebaseDatabase.getInstance().getReference("Genres/Romance");
                }
                if(genre == "Fiction"){
                    dbReference = FirebaseDatabase.getInstance().getReference("Genres/Fiction");
                }
                if(genre == "Comedy"){
                    dbReference = FirebaseDatabase.getInstance().getReference("Genres/Comedy");
                }
            }
        });

        addFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addFolder();

            }
        });
    }

    public void addFolder() {

        String cGenre = genreSelection.getText().toString();
        String cFolderName = folderName.getText().toString();

        if (cFolderName.isEmpty()) {
            folderName.setError("Title is required!");
            folderName.requestFocus();
            return;
        }

        if (cGenre.isEmpty()) {
            genreSelection.setError("Genre selection is required!");
            genreSelection.requestFocus();
            return;
        }

        //Folder folder = new Folder(cTitle, cAuthor, cIllustrator, cNoPages, cPageLastRead, cDateAdded);

        //dbReference.push().setValue(folder);

        Toast.makeText(AddFolder.this, "Data inserted!  :)", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(AddFolder.this, ViewAllFolders.class));
    }

}