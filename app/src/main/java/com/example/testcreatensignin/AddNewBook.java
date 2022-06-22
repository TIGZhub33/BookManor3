package com.example.testcreatensignin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.text.DateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddNewBook extends AppCompatActivity {

    private EditText bookTitle, bookAuthor, bookIllustrator, bookPages, bookPageRead;
    private AutoCompleteTextView genreSelection;
    private AutoCompleteTextView folderSelection;
    private Button cameraImage, galleryImage, addBook;
    private ImageView imageBook;
    private Toolbar toolbar;
    private String[] genres = {"Poetry", "Fiction", "Romance", "Comedy"};
    private String[] folders = {"2022", "Folder 2", "Folder 3", "Folder 4"};

    private ArrayAdapter<String> adapterItem1;
    private ArrayAdapter<String> adapterItem2;

    private static final int REQUEST_IMAGE_CAPTURE = 0;
    private static final int REQUEST_IMAGE_CAPTURE_PERMISSION=100;
    private static final int GALLERY_REQUEST_CODE=105;

    private DatabaseReference dbReference, dbReferencePoetry, dbReferenceFiction, dbReferenceRomance, dbReferenceComedy;
    private StorageReference stReference;

    String currentPhotoPath;

    private String userId;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);

        toolbar = (Toolbar) findViewById(R.id.toolbar3);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        bookTitle = (EditText) findViewById(R.id.edtBookTitle);
        bookAuthor = (EditText) findViewById(R.id.edtAuthor);
        bookIllustrator = (EditText) findViewById(R.id.edtIllustrator);
        bookPages = (EditText) findViewById(R.id.edtNumberOfPages);
        bookPageRead = (EditText) findViewById(R.id.edtPageLastRead);
        genreSelection = (AutoCompleteTextView) findViewById(R.id.autoTxtGenreSelection);
        folderSelection = (AutoCompleteTextView) findViewById(R.id.autoTxtFolderSelection);
        cameraImage = (Button) findViewById(R.id.btnCameraImage);
        galleryImage = (Button) findViewById(R.id.btnGalleryImage);
        addBook = (Button) findViewById(R.id.btnAddBook);
        imageBook = (ImageView) findViewById(R.id.imgPlaceholder);

        adapterItem1 = new ArrayAdapter<String>(this, R.layout.genre_item, genres);
        adapterItem2 = new ArrayAdapter<String>(this, R.layout.folder_item, folders);

        genreSelection.setAdapter(adapterItem1);
        folderSelection.setAdapter(adapterItem2);

        stReference = FirebaseStorage.getInstance().getReference();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null)
        {
            userId = user.getUid();
        }

        genreSelection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String genre = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(AddNewBook.this, "Genre " + genre, Toast.LENGTH_SHORT).show();


                if(genre == "Poetry"){
                    dbReference = FirebaseDatabase.getInstance().getReference(userId + "/Genres/Poetry/Books");
                }
                if(genre == "Romance"){
                    dbReference = FirebaseDatabase.getInstance().getReference("Genres/Romance/Books");
                }
                if(genre == "Fiction"){
                    dbReference = FirebaseDatabase.getInstance().getReference("Genres/Fiction/Books");
                }
                if(genre == "Comedy"){
                    dbReference = FirebaseDatabase.getInstance().getReference("Genres/Comedy/Books");
                }

            }
        });

        folderSelection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String folder = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(AddNewBook.this, "Folder " + folder, Toast.LENGTH_SHORT).show();


                if(folder == "2022"){
                    dbReference = FirebaseDatabase.getInstance().getReference(userId + "/Genres/Poetry/Folders/2022/Books");
                }
                if(folder == "Romance"){
                    dbReference = FirebaseDatabase.getInstance().getReference("Genres/Romance/Books");
                }
                if(folder == "Fiction"){
                    dbReference = FirebaseDatabase.getInstance().getReference("Genres/Fiction/Books");
                }
                if(folder == "Comedy"){
                    dbReference = FirebaseDatabase.getInstance().getReference("Genres/Comedy/Books");
                }

            }
        });

        cameraImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ActivityCompat.checkSelfPermission(AddNewBook.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    final String[] permissions = {Manifest.permission.CAMERA};
                    ActivityCompat.requestPermissions(AddNewBook.this, permissions, REQUEST_IMAGE_CAPTURE_PERMISSION);
                }
                else
                {
                    //takePhoto();
                    dispatchTakePictureIntent();
                }
            }
        });

        galleryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gall = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gall, GALLERY_REQUEST_CODE);
            }
        });

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addNewBook();

            }
        });

    }

    public void takePhoto(){

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_CAPTURE)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                File f = new File(currentPhotoPath);
                imageBook.setImageURI(Uri.fromFile(f));
                Log.d("tag", "Absolute Url of image is " + Uri.fromFile(f));

                //Code retribution
                //https://developer.android.com/training/camera/photobasics


                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

                uploadImgToFirebase(f.getName(), contentUri);
            }
        }

        if(requestCode == GALLERY_REQUEST_CODE)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                Uri contentUri = data.getData();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "." + getFileExt(contentUri);
                Log.d("tag", "onActivityResult: Gallery image Uri: " + imageFileName);
                imageBook.setImageURI(contentUri);

                uploadImgToFirebase(imageFileName, contentUri);

            }
        }

    }

    private void uploadImgToFirebase(String imageFileName, Uri contentUri) {

        StorageReference image = stReference.child("Images/Books/" + imageFileName);

        image.putFile(contentUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d("tag", "onSuccess: Uploaded Image URL is " + uri.toString());
                    }
                });

                Toast.makeText(AddNewBook.this, "Image upload successful.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddNewBook.this, "Image upload failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExt(Uri contentUri) {

        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(contentUri));
    }


    //Code retribution
    //https://developer.android.com/training/camera/photobasics

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    //Code retribution
    //https://developer.android.com/training/camera/photobasics

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_IMAGE_CAPTURE_PERMISSION &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA) ==
                        PackageManager.PERMISSION_GRANTED) {

            //call method to take a photo

            //takePhoto();
            dispatchTakePictureIntent();
        }
        else
        {
            Toast.makeText(this, "Camera permission needed to use camera.", Toast.LENGTH_SHORT).show();
        }
    }


    public void addNewBook(){

        String cTitle = bookTitle.getText().toString();
        String cAuthor = bookAuthor.getText().toString();
        String cIllustrator = bookIllustrator.getText().toString();
        String cGenre = genreSelection.getText().toString();
        String cFolder = genreSelection.getText().toString();
        String cNoPages = bookPages.getText().toString();
        String cPageLastRead = bookPageRead.getText().toString();
        String cImageName = imageBook.toString();

        Calendar calender = Calendar.getInstance();
        @SuppressLint({"NewApi", "LocalSuppress"}) String cDateAdded = DateFormat.getDateInstance().format(calender.getTime());

        if(cTitle.isEmpty())
        {
            bookTitle.setError("Title is required!");
            bookTitle.requestFocus();
            return;
        }

        if(cAuthor.isEmpty())
        {
            bookAuthor.setError("Author is required!");
            bookAuthor.requestFocus();
            return;
        }

        if(cIllustrator.isEmpty())
        {
            bookIllustrator.setError("Illustrator is required!");
            bookIllustrator.requestFocus();
            return;
        }

        if(cGenre.isEmpty())
        {
            genreSelection.setError("Genre selection is required!");
            genreSelection.requestFocus();
            return;
        }

        if(cFolder.isEmpty())
        {
            folderSelection.setError("Genre selection is required!");
            folderSelection.requestFocus();
            return;
        }

        if(cNoPages.isEmpty())
        {
            bookPages.setError("Number of pages is required!");
            bookPages.requestFocus();
            return;
        }

        if(cPageLastRead.isEmpty())
        {
            bookPageRead.setError("Page number last read is required!");
            bookPageRead.requestFocus();
            return;
        }

        Books book = new Books(cTitle, cAuthor, cIllustrator, cNoPages, cPageLastRead, cDateAdded);

        dbReference.child(cTitle).setValue(book);

        Toast.makeText(AddNewBook.this, "Data inserted!  :)", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(AddNewBook.this, ViewBooks.class));

    }
}