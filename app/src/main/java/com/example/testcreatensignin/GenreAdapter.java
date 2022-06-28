package com.example.testcreatensignin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GenreAdapter extends ArrayAdapter {

    private Activity mContext;
    private List<GenreInfo> genresList;
    private TextView genreName, goalSet;

    ///////////////////////////////
    private ProgressBar horProgBar;
    private TextView count;

    private DatabaseReference referenceP;

    private String userId;

    private float percentageComp;

    private int booksLeft;
    ///////////////////////////////

    public GenreAdapter(Activity mContext, List<GenreInfo> genresList){
        super(mContext, R.layout.item_genre, genresList);

        this.mContext = mContext;
        this.genresList = genresList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.item_genre, null, true);

        genreName = listItemView.findViewById(R.id.txtGenreName);
        goalSet = listItemView.findViewById(R.id.txtGoalSet);

        //GenreInfo genres = genresList.get(position);
        GenreInfo genres = genresList.get(position);

        genreName.setText(genres.getCustomGenreName());
        //goalSet.setText(genres.getBooksGoal());
        goalSet.setText(Integer.toString(genres.getBooksGoal()));


        ///////////////////////////////
        horProgBar = listItemView.findViewById(R.id.horProgressBarBooks);
        count = listItemView.findViewById(R.id.txtBooksCount);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null)
        {
            userId = user.getUid();
        }

        referenceP = FirebaseDatabase.getInstance().getReference(userId + "/Genres/" + genres.getCustomGenreName() + "/Folders/2022");


        //Code retribution
        //https://stackoverflow.com/questions/52641178/how-to-count-children-in-firebase-database-android

        referenceP.child("Books").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Long bookCount = new Long(snapshot.getChildrenCount());
                int intBookCount = bookCount.intValue();

                horProgBar.setMax(genres.getBooksGoal());
                horProgBar.setProgress(intBookCount);

                booksLeft = genres.getBooksGoal() - intBookCount;

                if(booksLeft == 0)
                {
                    count.setText("Goal Achieved");
                }
                else
                {
                    count.setText(booksLeft + " More books needed.");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ///////////////////////////////


        return listItemView;

    }
}
