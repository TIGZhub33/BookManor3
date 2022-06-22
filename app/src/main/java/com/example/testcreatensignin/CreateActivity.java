package com.example.testcreatensignin;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateActivity extends AppCompatActivity {

    private EditText name, email, password, confPassword;
    private Button createAcc;
    private CheckBox checkTermsCond;
    private TextView directToSignIn, directToTermCond;
    private FirebaseAuth mAuth;
    //private FirebaseDatabase database;
    //private DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        name = (EditText) findViewById(R.id.edtFullName);
        email = (EditText) findViewById(R.id.edtEmail);
        password = (EditText) findViewById(R.id.edtPassword);
        confPassword = (EditText) findViewById(R.id.edtConfPassword);
        createAcc = (Button) findViewById(R.id.btnCreateAcc);
        checkTermsCond = (CheckBox) findViewById(R.id.checkBox);
        mAuth = FirebaseAuth.getInstance();

        directToSignIn = (TextView) findViewById(R.id.txtDirectSignIn);
        directToTermCond = (TextView) findViewById(R.id.txtDirectTermCond);


        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createNewAcc();
            }
        });

        directToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(CreateActivity.this, SignInActivity.class));
            }
        });

        directToTermCond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateActivity.this, TermsAndConditions.class));
            }
        });
    }

    private void createNewAcc() {
        String cName = name.getText().toString();
        String cMail = email.getText().toString();
        String cPassword = password.getText().toString();
        String cConfPassword = confPassword.getText().toString();
        Boolean cCheckTermsCond = checkTermsCond.isChecked();

        if(cName.isEmpty())
        {
            name.setError("Full Name is required!");
            name.requestFocus();
            return;
        }

        if(cMail.isEmpty())
        {
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(cMail).matches())
        {
            email.setError("Invalid email, please try again!");
            email.requestFocus();
            return;
        }

        if(cPassword.isEmpty())
        {
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }

        if(cPassword.length() < 6)
        {
            password.setError("Min length of 6 characters for password!");
            password.requestFocus();
            return;
        }

        if(cConfPassword.isEmpty())
        {
            confPassword.setError("Confirm password is required!");
            confPassword.requestFocus();
            return;
        }

        if(cConfPassword.length() < 6)
        {
            confPassword.setError("Min length of 6 characters for password!");
            confPassword.requestFocus();
            return;
        }

        if(!cConfPassword.matches(cPassword))
        {
            confPassword.setError("Passwords do not match, Please try again!");
            confPassword.requestFocus();
            return;
        }

        if(cCheckTermsCond == false)
        {
            Toast.makeText(CreateActivity.this, "Please check Terms and Conditions box to proceed!", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(cMail, cPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {

                    FirebaseUser user = mAuth.getCurrentUser();

                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "Email sent.");
                                    }
                                }
                            });

                    Toast.makeText(CreateActivity.this, "Account created successfully!", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(CreateActivity.this, SignInActivity.class));
                }

                else
                {
                    Toast.makeText(CreateActivity.this, "Account creation has failed due to: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}