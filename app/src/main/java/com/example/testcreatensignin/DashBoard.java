package com.example.testcreatensignin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DashBoard extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggleOnOff;
    private NavigationView navigationView;
    private FloatingActionButton floatingAdd;
    private ImageButton profileButton;

    private Button buttonGenre, buttonFolder, buttonBooks;

    //////////////////
    private ListView graphList;
    private List<GenreInfo> genreList;

    private DatabaseReference reference;

    private String userId;
    //////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = (Toolbar) findViewById(R.id.nav_toolbar);

        buttonBooks = (Button) findViewById(R.id.button5);
        buttonGenre = (Button) findViewById(R.id.button3);
        buttonFolder = (Button) findViewById(R.id.button4);

        profileButton = (ImageButton) findViewById(R.id.imgbtnProfile);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.linear_layout);
        toggleOnOff = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggleOnOff);
        toggleOnOff.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        buttonBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashBoard.this, ViewBooks.class));
            }
        });

        buttonGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashBoard.this, ViewPoetryGenre.class));
            }
        });

        buttonFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashBoard.this, View_Folders_Test.class));

            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashBoard.this, Profile.class));

            }
        });

        floatingAdd = (FloatingActionButton) findViewById(R.id.floatingAddButton);

        floatingAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashBoard.this, FloatingAdd.class));
            }
        });

        /////////////////////////
        graphList = (ListView) findViewById(R.id.graphListView);

        genreList = new ArrayList<>();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null)
        {
            userId = user.getUid();
        }

        reference = FirebaseDatabase.getInstance().getReference(userId + "/Genres");


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

                GraphAdapter adapter = new GraphAdapter(DashBoard.this, genreList);
                graphList.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /////////////////////////

    }

    @Override
    public void onBackPressed(){

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{

            super.onBackPressed();
        }
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_profile:
                startActivity(new Intent(this, Profile.class));
                break;

            case R.id.nav_books:
                startActivity(new Intent(this, ViewBooks.class));
                break;

                case R.id.nav_settings:
                startActivity(new Intent(this, Settings.class));
                break;

            case R.id.nav_help:
                startActivity(new Intent(this, Help_Menu.class));
                break;

            case R.id.nav_exit:
                finishAffinity();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}