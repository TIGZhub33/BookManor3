package com.example.testcreatensignin;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

public class AddGenres extends AppCompatActivity {
    //Declarations
    private EditText etGenreName, etNoBooksGoal;
    private Button insertIconButton, createGenreButton, addFolderInGenreButton;
    private ImageView ivGenreIcon;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    GenreInfo genreInfo;
    String customGenreName, booksGoalS;
    int booksGoal;

    //private String[] genres = {"Poetry", "Fiction", "Romance", "Comedy"};

    private DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_genres);

        etGenreName = (EditText) findViewById(R.id.editTextGenreName);
        etNoBooksGoal = (EditText) findViewById(R.id.editTextNumBooks);

        insertIconButton = (Button) findViewById(R.id.genreIconButton);
        createGenreButton = (Button) findViewById(R.id.createGenreButton);

        dbReference = FirebaseDatabase.getInstance().getReference("GenreInfo");

        //sets on click listener for the genre icon button
        insertIconButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //invokes imageChooser method when the genre icon button is clicked
                imageChooser();

            }
        });

        createGenreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //createNewGenre();
                customGenreName = etGenreName.getText().toString();
                booksGoalS = etNoBooksGoal.getText().toString();
                // below line is for checking whether edittext fields are empty or not.
                if (customGenreName.isEmpty() && booksGoalS.isEmpty())
                {
                    // if the text fields are empty, this message is shown
                    Toast.makeText(AddGenres.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    booksGoal = Integer.parseInt(booksGoalS);
                    addDataToFirebase(customGenreName, booksGoal);
                }
            }
        });

    }

    public void createNewGenre(){

        String cGenreName = etGenreName.getText().toString();

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

    private void addDataToFirebase(String customGenreName, int goal) {
        genreInfo.setCustomGenreName(customGenreName);
        genreInfo.setBooksGoal(goal);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // database reference will sends data to firebase.
                databaseReference.setValue(genreInfo);

                // after adding this data we are showing toast message.
                Toast.makeText(AddGenres.this, "Genre Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(AddGenres.this, "Failed to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void imageChooser()
    {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }

    ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->
    {
        if (result.getResultCode() == Activity.RESULT_OK)
        {
            Intent data = result.getData();
            if (data != null && data.getData() != null)
            {
                Uri selectedImageUri = data.getData();
                Bitmap selectedImageBitmap = null;
                try
                {
                    selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImageUri);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                ivGenreIcon.setImageBitmap(selectedImageBitmap);
            }
        }
    });
}