package com.example.testcreatensignin;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    private Bitmap icon;

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

        ivGenreIcon = (ImageView) findViewById(R.id.genreIconImageView);

        //sets on click listener for the genre icon button
        insertIconButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                imageChooser();

            }
        });


        createGenreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //createNewGenre();
                customGenreName = etGenreName.getText().toString();
                booksGoalS = etNoBooksGoal.getText().toString();
                icon = ivGenreIcon.getDrawingCache();
                // below line is for checking whether edittext fields are empty or not.
                if (customGenreName.isEmpty() && booksGoalS.isEmpty())
                {
                    // if the text fields are empty, this message is shown
                    Toast.makeText(AddGenres.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    booksGoal = Integer.parseInt(booksGoalS);
                    createNewGenre();
                    startActivity(new Intent(AddGenres.this, ViewGenres.class));
                }
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
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        ivGenreIcon.setScaleType(ImageView.ScaleType.FIT_XY);
                        ivGenreIcon.setImageBitmap(selectedImageBitmap);
                    }
                }
            });



    public void createNewGenre(){

        customGenreName = etGenreName.getText().toString();
        booksGoal = Integer.parseInt(etNoBooksGoal.getText().toString());
        GenreInfo genre = new GenreInfo(customGenreName, booksGoal, ivGenreIcon);
        dbReference.push().setValue(genre);

        Toast.makeText(AddGenres.this, "Genre created successfully!", Toast.LENGTH_SHORT).show();

    }
}