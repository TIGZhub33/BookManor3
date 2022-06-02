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

    private EditText folderName;

    private AutoCompleteTextView genreSelection;
    private String[] genres = {"Poetry", "Fiction", "Romance", "Comedy"};
    private Button addFolder;

    private ArrayAdapter<String> adapterItem1;

    private DatabaseReference dbReference, dbReferencePoetry, dbReferenceFiction, dbReferenceRomance, dbReferenceComedy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_folder);

        folderName = (EditText) findViewById(R.id.edtFolderName);

        genreSelection = (AutoCompleteTextView) findViewById(R.id.autoTxtGenreSelection);
        addFolder = (Button) findViewById(R.id.newFolderButton);

        adapterItem1 = new ArrayAdapter<String>(this, R.layout.genre_item, genres);

        genreSelection.setAdapter(adapterItem1);

        genreSelection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String genre = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(AddFolder.this, "Genre " + genre, Toast.LENGTH_SHORT).show();

                if(genre == "Poetry"){
                    dbReference = FirebaseDatabase.getInstance().getReference("Genres/Poetry/Folders");
                }
                if(genre == "Romance"){
                    dbReference = FirebaseDatabase.getInstance().getReference("Genres/Romance/Folders");
                }
                if(genre == "Fiction"){
                    dbReference = FirebaseDatabase.getInstance().getReference("Genres/Fiction/Folders");
                }
                if(genre == "Comedy"){
                    dbReference = FirebaseDatabase.getInstance().getReference("Genres/Comedy/Folders");
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

        //String cGenre = genreSelection.getText().toString();

        String cFolderName = folderName.getText().toString();


        //Folder folder = new Folder(cTitle, cAuthor, cIllustrator, cNoPages, cPageLastRead, cDateAdded);
        Folders folder = new Folders(cFolderName);

        dbReference.child(cFolderName).setValue(folder);

        Toast.makeText(AddFolder.this, "Data inserted!  :)", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(AddFolder.this, View_Folders_Test.class));
    }

}