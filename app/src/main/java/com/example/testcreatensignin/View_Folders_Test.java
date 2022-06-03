package com.example.testcreatensignin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class View_Folders_Test extends AppCompatActivity {

    private FloatingActionButton addFolder;

    private ListView testList;
    private List<Folders> folderList;
    private Toolbar toolbar;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_folders_test);

        toolbar = (Toolbar) findViewById(R.id.toolbar3);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        testList = (ListView) findViewById(R.id.lstFolders);
        folderList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Genres/Poetry/Folders");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                folderList.clear();

                for (DataSnapshot folderSnapshot : snapshot.getChildren()){

                    Folders folders = folderSnapshot.getValue(Folders.class);
                    folderList.add(folders);
                }

                FolderAdapter adapter = new FolderAdapter(View_Folders_Test.this, folderList);
                testList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        addFolder = (FloatingActionButton) findViewById(R.id.floatingAddButton);

        addFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(View_Folders_Test.this, AddFolder.class));
            }
        });
    }
}