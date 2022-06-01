package com.example.testcreatensignin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ManageAccount extends AppCompatActivity
{
    //Declarations
    private Button addProfilePictureButton, updateDetailsButton, deleteAccountButton;
    private ImageView profilePictureImageView;
    private EditText fullNameEt, emailEt, phoneEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        addProfilePictureButton = (Button) findViewById(R.id.addProfilePictureBtn);
        updateDetailsButton = (Button) findViewById(R.id.updateDetailsBtn);
        deleteAccountButton = (Button) findViewById(R.id.deleteAccountBtn);
        profilePictureImageView = (ImageView) findViewById(R.id.profilePictureImageView);
        fullNameEt = (EditText) findViewById(R.id.fullNameEt);
        emailEt = (EditText) findViewById(R.id.emailEt);
        phoneEt = (EditText) findViewById(R.id.phoneEt);


        //addProfilePictureButton.findViewById(R.id.addProfilePictureButton);
        //updateDetailsButton.findViewById(R.id.updateDetailsButton);
        //deleteAccountButton.findViewById(R.id.deleteAccountButton);
        //profilePictureImageView.findViewById(R.id.profilePictureImageView);
        //fullNameEt.findViewById(R.id.fullNameEt);
        //emailEt.findViewById(R.id.emailEt);
        //addProfilePictureButton.findViewById(R.id.phoneEt);
    }
}