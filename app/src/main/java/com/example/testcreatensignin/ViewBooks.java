package com.example.testcreatensignin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
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

import java.util.ArrayList;
import java.util.List;

public class ViewBooks extends AppCompatActivity {

    private ListView testList;
    private List<Books> bookList;
    private Toolbar toolbar;
    private FloatingActionButton addBook;

    private DatabaseReference reference;

    private TextView test;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);

        toolbar = (Toolbar) findViewById(R.id.toolbar3);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        testList = (ListView) findViewById(R.id.lstBooks);
        bookList = new ArrayList<>();

        addBook = (FloatingActionButton) findViewById(R.id.floatingAddButton);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null)
        {
            userId = user.getUid();
        }

        reference = FirebaseDatabase.getInstance().getReference(userId + "/Genres/Poetry/Folders/2022/Books");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                bookList.clear();

                for (DataSnapshot bookSnapshot : snapshot.getChildren()){

                    Books books = bookSnapshot.getValue(Books.class);
                    bookList.add(books);
                }

                BookAdapter adapter = new BookAdapter(ViewBooks.this, bookList);
                testList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ViewBooks.this, AddNewBook.class));
            }
        });
    }
}