package com.example.testcreatensignin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FoldersInGenre extends AppCompatActivity {

    //Declarations
    private TextView goalTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folders_in_genre);
        goalTextView.findViewById(R.id.goalTextView);

    }
}