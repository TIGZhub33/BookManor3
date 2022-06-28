package com.example.testcreatensignin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.net.URI;

public class Profile extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton floatingAdd;
    private Button manageAccountButton;

    //////////////////
    private ListView genreList, bookFavList;
    private TextView text24Name, text25Email;
    private ImageView profilePic;
    private String userId;
    private DatabaseReference dbReference;
    //////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = (Toolbar) findViewById(R.id.addToolbar3);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        floatingAdd = (FloatingActionButton) findViewById(R.id.floatingAddButton);

        floatingAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Profile.this, FloatingAdd.class));
            }
        });

        manageAccountButton = (Button) findViewById(R.id.manageButton);

        manageAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Profile.this, ManageAccount.class));
            }
        });

        ///////////////
        genreList = (ListView) findViewById(R.id.lstGenres);
        bookFavList = (ListView) findViewById(R.id.lstBooks);
        profilePic = (ImageView) findViewById(R.id.profilePhoto);
        text24Name = (TextView) findViewById(R.id.textView24);
        text25Email = (TextView) findViewById(R.id.textView25);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null)
        {
            userId = user.getUid();

            String userEmail = user.getEmail();
            text25Email.setText(userEmail);
        }

        FirebaseDatabase fire = FirebaseDatabase.getInstance();

        DatabaseReference db = fire.getReference();

        DatabaseReference getImage = db.child(userId + "/Images/ProfilePic");

        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String link = dataSnapshot.getValue(String.class);

                Picasso.get().load(link).into(profilePic);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });

    }
}