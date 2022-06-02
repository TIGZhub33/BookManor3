package com.example.testcreatensignin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GenreAdapter extends ArrayAdapter {

    private Activity mContext;
    private List<GenreInfo> genresList;
    private TextView genreName, goalSet;

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


        return listItemView;

        /*
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.book_item, null, true);

        aTitle = listItemView.findViewById(R.id.txtTitle1);
        aAuthor = listItemView.findViewById(R.id.txtAuthor1);
        aIllustrator = listItemView.findViewById(R.id.txtIllustrator1);
        aPages = listItemView.findViewById(R.id.txtPages1);
        aPagesRead = listItemView.findViewById(R.id.txtPagesRead1);
        aDate = listItemView.findViewById(R.id.txtDate1);

        Books books = booksList.get(position);

        aTitle.setText(books.getTitle());
        aAuthor.setText(books.getAuthor());
        aIllustrator.setText(books.getIllustrator());
        aPages.setText(books.getPages());
        aPagesRead.setText(books.getPagesRead());
        aDate.setText(books.getDateBookAdded());

        return listItemView;
         */


        /*
                testList.setClickable(true);
                testList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        if(i == 0){
                            //view folders
                            startActivity(new Intent(ViewPoetryGenre.this, ViewAllFolders.class));
                        }
                        else{

                            Toast.makeText(ViewPoetryGenre.this, "No folders currently in this genre.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                 */
    }
}
