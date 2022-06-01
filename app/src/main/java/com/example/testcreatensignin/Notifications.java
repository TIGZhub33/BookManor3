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

        etEmail = (EditText) findViewById(R.id.emailEditText);
        etPhone = (EditText) findViewById(R.id.phoneEditText);
        emailRadioGroup = (RadioGroup) findViewById(R.id.emailRadioGroup);
        smsRadioGroup = (RadioGroup) findViewById(R.id.smsRadioGroup);
        yesEmailRadioButton = (RadioButton) findViewById(R.id.yesEmailRadioButton);
        noEmailRadioButton = (RadioButton) findViewById(R.id.noEmailRadioButton);
        yesSmsRadioButton = (RadioButton) findViewById(R.id.yesSmsRadioButton);
        noSmsRadioButton = (RadioButton) findViewById(R.id.noSmsRadioButton);

        //etEmail.findViewById(R.id.emailEditText);
        //etPhone.findViewById(R.id.phoneEditText);
        //emailRadioGroup.findViewById(R.id.emailRadioGroup);
        //smsRadioGroup.findViewById(R.id.smsRadioGroup);
        //yesEmailRadioButton.findViewById(R.id.yesEmailRadioButton);
        //noEmailRadioButton.findViewById(R.id.noEmailRadioButton);
        //yesSmsRadioButton.findViewById(R.id.yesSmsRadioButton);
        //noSmsRadioButton.findViewById(R.id.noSmsRadioButton);

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