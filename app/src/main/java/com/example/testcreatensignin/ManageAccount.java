package com.example.testcreatensignin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManageAccount extends AppCompatActivity
{
    //Declarations
    private Button addProfilePictureButton, updateDetailsButton, deleteAccountButton;
    private ImageView profilePictureImage;
    private EditText fullNameEt, emailEt, phoneEt;

    //////////////
    private StorageReference stReference;
    private DatabaseReference dbReference;
    private static final int GALLERY_REQUEST_CODE=105;
    private String userId;
    //////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        addProfilePictureButton = (Button) findViewById(R.id.addProfilePictureBtn);
        updateDetailsButton = (Button) findViewById(R.id.updateDetailsBtn);
        deleteAccountButton = (Button) findViewById(R.id.deleteAccountBtn);
        profilePictureImage = (ImageView) findViewById(R.id.profilePictureImageView);
        fullNameEt = (EditText) findViewById(R.id.fullNameEt);
        emailEt = (EditText) findViewById(R.id.emailEt);
        phoneEt = (EditText) findViewById(R.id.phoneEt);

        stReference = FirebaseStorage.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null)
        {
            userId = user.getUid();
        }

        addProfilePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gall = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gall, GALLERY_REQUEST_CODE);
            }
        });

        updateDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ManageAccount.this, "Details Updated successfully.", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(ManageAccount.this, Profile.class));

            }
        });

        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ManageAccount.this, "Account deleted successfully.", Toast.LENGTH_SHORT).show();

                finish();
                System.exit(0);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_REQUEST_CODE)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                Uri contentUri = data.getData();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "." + getFileExt(contentUri);
                Log.d("tag", "onActivityResult: Gallery image Uri: " + imageFileName);
                profilePictureImage.setImageURI(contentUri);

                uploadImgToFirebase(imageFileName, contentUri);

            }
        }

    }

    private void uploadImgToFirebase(String imageFileName, Uri contentUri) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null)
        {
            userId = user.getUid();
        }


        StorageReference image = stReference.child("Images/" + userId + "/ProfilePic/" + imageFileName);


        image.putFile(contentUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d("tag", "onSuccess: Uploaded Image URL is " + uri.toString());

                        dbReference = FirebaseDatabase.getInstance().getReference(userId + "/Images/ProfilePic");

                        String pathUri = uri.toString();

                        dbReference.setValue(pathUri);
                    }
                });

                Toast.makeText(ManageAccount.this, "Image upload successful.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ManageAccount.this, "Image upload failed!", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private String getFileExt(Uri contentUri) {

        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(contentUri));
    }
}