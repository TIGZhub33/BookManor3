package com.example.testcreatensignin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {
    //Declarations
    CardView manageAccountCv, inviteFriendsCv, notificationsCv, helpAndSupportCv, appInfoCv, signOutCv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        manageAccountCv = (CardView) findViewById(R.id.manageAccountCardView);
        inviteFriendsCv = (CardView) findViewById(R.id.inviteFriednsCardView);
        notificationsCv = (CardView) findViewById(R.id.notificationsCardView);
        helpAndSupportCv = (CardView) findViewById(R.id.helpandSupportCardView);
        appInfoCv = (CardView) findViewById(R.id.appInfoCardView);
        signOutCv = (CardView) findViewById(R.id.signOutCardView);

        //manageAccountCv.findViewById(R.id.manageAccountCardView);
        //inviteFriendsCv.findViewById(R.id.inviteFriednsCardView);
        //notificationsCv.findViewById(R.id.notificationsCardView);
        //helpAndSupportCv.findViewById(R.id.helpandSupportCardView);
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