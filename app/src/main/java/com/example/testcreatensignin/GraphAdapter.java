package com.example.testcreatensignin;

import android.app.Activity;
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

import java.util.List;

public class GraphAdapter extends ArrayAdapter {

    private Activity mContext;
    private List<GenreInfo> genresList;
    private TextView genreTextName, percentage;
    private ProgressBar graphProgbar;

    private DatabaseReference referenceP;

    private String userId;

    private float percentageComp;

    private int booksGoal;

    public GraphAdapter(Activity mContext, List<GenreInfo> genresList){
        super(mContext, R.layout.graph_item, genresList);

        this.mContext = mContext;
        this.genresList = genresList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.graph_item, null, true);

        graphProgbar = (ProgressBar) listItemView.findViewById(R.id.graphProgressBar);
        genreTextName = listItemView.findViewById(R.id.genreTxT);
        percentage = listItemView.findViewById(R.id.progressPercentage);

        GenreInfo genres = genresList.get(position);

        genreTextName.setText(genres.getCustomGenreName());

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

                graphProgbar.setMax(genres.getBooksGoal());
                graphProgbar.setProgress(intBookCount);

                percentageComp =((float)intBookCount / (float)genres.getBooksGoal()) * 100;
                //percentageComp =((float)intBookCount / (float)booksGoal) * 100;

                int displayPer = (int) percentageComp;

                //percentage.setText(percentageComp + "%");
                percentage.setText(displayPer + "%");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return listItemView;
    }
}
