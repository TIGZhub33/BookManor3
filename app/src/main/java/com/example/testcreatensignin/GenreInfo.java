package com.example.testcreatensignin;

import android.widget.ImageView;

public class GenreInfo
{

    private String customGenreName;
    private int booksGoal;
    //private ImageView icon;


    public GenreInfo() {

    }

    public GenreInfo(String customGenreName, int booksGoal, ImageView icon) {
        this.customGenreName = customGenreName;
        this.booksGoal = booksGoal;
        //this.icon = icon;
    }

    public GenreInfo(String customGenreName, int booksGoal) {
        this.customGenreName = customGenreName;
        this.booksGoal = booksGoal;
    }


    /*
    public ImageView getIcon() {
        return icon;
    }

    public void setIcon(ImageView icon) {
        this.icon = icon;
    }

     */


    public String getCustomGenreName() {
        return customGenreName;
    }

    public void setCustomGenreName(String customGenreName) {
        this.customGenreName = customGenreName;
    }

    public int getBooksGoal() {
        return booksGoal;
    }

    public void setBooksGoal(int booksGoal) {
        this.booksGoal = booksGoal;
    }


}
