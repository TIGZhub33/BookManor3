package com.example.testcreatensignin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Help_Menu extends AppCompatActivity {

    private CardView howWorks, faq, contactUs, termNPrivacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_menu);

        howWorks = (CardView) findViewById(R.id.cardHowWorks);
        faq = (CardView) findViewById(R.id.cardFAQ);
        contactUs = (CardView) findViewById(R.id.cardContact);
        termNPrivacy = (CardView) findViewById(R.id.cardTermsNPrivacy);

        howWorks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Help_Menu.this, How_Book_Manor_Works.class));
            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Help_Menu.this, FAQ.class));
            }
        });

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Help_Menu.this, Contact_Us.class));
            }
        });

        termNPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Help_Menu.this, TermsAndConditions.class));
            }
        });
    }
}