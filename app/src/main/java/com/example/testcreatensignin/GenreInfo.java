package com.example.testcreatensignin;

import android.media.Image;

public class GenreInfo
{
    private String customGenreName;
    private int booksGoal;

    private Image icon;

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }


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
