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

public class FolderAdapter extends ArrayAdapter {

    private Activity mContext;
    private List<Folders> folderList;
    private TextView aFolderName;

    public FolderAdapter(Activity mContext, List<Folders> folderList){
        super(mContext, R.layout.item_folder, folderList);

        this.mContext = mContext;
        this.folderList = folderList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.item_folder, null, true);

        aFolderName = listItemView.findViewById(R.id.txtFolderName);

        Folders folders = folderList.get(position);

        aFolderName.setText(folders.getFolder());

        return listItemView;
    }
}
