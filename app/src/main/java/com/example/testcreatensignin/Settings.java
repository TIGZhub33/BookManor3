package com.example.testcreatensignin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {
    //Declarations
    private Toolbar toolbar;
    CardView manageAccountCv, inviteFriendsCv, notificationsCv, helpAndSupportCv, appInfoCv, signOutCv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbar = (Toolbar) findViewById(R.id.toolbar4);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        manageAccountCv = (CardView) findViewById(R.id.manageAccountCardView);
        inviteFriendsCv = (CardView) findViewById(R.id.inviteFriendsCardView);
        notificationsCv = (CardView) findViewById(R.id.notificationsCardView);
        helpAndSupportCv = (CardView) findViewById(R.id.helpAndSupportCardView);
        appInfoCv = (CardView) findViewById(R.id.appInfoCardView);
        signOutCv = (CardView) findViewById(R.id.signOutCardView);

        //manageAccountCv.findViewById(R.id.manageAccountCardView);
        //inviteFriendsCv.findViewById(R.id.inviteFriendsCardView);
        //notificationsCv.findViewById(R.id.notificationsCardView);
        //helpAndSupportCv.findViewById(R.id.helpAndSupportCardView);
        //appInfoCv.findViewById(R.id.appInfoCardView);
        //signOutCv.findViewById(R.id.signOutCardView);

        manageAccountCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Settings.this, ManageAccount.class));
            }
        });

        inviteFriendsCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            }
        });

        notificationsCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Settings.this, Notifications.class));
            }
        });

        helpAndSupportCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                startActivity(new Intent(Settings.this, Help_Menu.class));
            }
        });

        appInfoCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                startActivity(new Intent(Settings.this, AppInfo.class));
            }
        });

        signOutCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                startActivity(new Intent(Settings.this, SignInActivity.class));
            }
        });
    }
}