package com.example.testcreatensignin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Notifications extends AppCompatActivity {
    //Declarations
    EditText etEmail, etPhone;
    RadioGroup emailRadioGroup, smsRadioGroup;
    RadioButton yesEmailRadioButton, noEmailRadioButton, yesSmsRadioButton, noSmsRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        etEmail.findViewById(R.id.emailEditText);
        etPhone.findViewById(R.id.phoneEditText);
        emailRadioGroup.findViewById(R.id.emailRadioGroup);
        smsRadioGroup.findViewById(R.id.smsRadioGroup);
        yesEmailRadioButton.findViewById(R.id.yesEmailRadioButton);
        noEmailRadioButton.findViewById(R.id.noEmailRadioButton);
        yesSmsRadioButton.findViewById(R.id.yesSmsRadioButton);
        noSmsRadioButton.findViewById(R.id.noSmsRadioButton);

        if(yesEmailRadioButton.isChecked() == true)
        {

        }
        else
        {
            if(noEmailRadioButton.isChecked() == true)
            {

            }
        }

        if(yesSmsRadioButton.isChecked() == true)
        {

        }
        else
        {
            if(noSmsRadioButton.isChecked() == true)
            {

            }
        }
    }



}