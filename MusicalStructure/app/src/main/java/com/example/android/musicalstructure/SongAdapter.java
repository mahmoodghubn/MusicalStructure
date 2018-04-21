package com.example.android.musicalstructure;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends ArrayAdapter<Song> {
    private final List<Song> list;
    private final Activity context;

    public SongAdapter(Activity context, ArrayList<Song> songAdapter) {

        super(context, R.layout.list_item, songAdapter);
        this.context = context;
        this.list = songAdapter;
    }

    static class view {
        TextView songName;
        TextView singerName;
        ImageButton runButton;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = null;
        if (listItemView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            listItemView = inflator.inflate(R.layout.list_item, null);

            //set clickListner up for RunButton
            final view viewHolder = new view();
            viewHolder.runButton = (ImageButton) listItemView.findViewById(R.id.run);
            viewHolder.runButton
                    .setOnClickListener(new TextView.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Intent songsIntent = new Intent(context, PlayActivity.class);
                            songsIntent.putExtra("myKey", position);
                            context.startActivity(songsIntent);
                        }
                    });
        }
        //determine witch song the user chose
        Song currentSong = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID songName
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.songName);
        // Get the song name from the current song object and
        // set this text on the name TextView
        nameTextView.setText(currentSong.getSongName());
        // Find the TextView in the list_item.xml layout with the ID singerName
        TextView nameTextView2 = (TextView) listItemView.findViewById(R.id.singerName);
        // Get the singer name from the current Song object and
        // set this text on the name TextView
        nameTextView2.setText(currentSong.getSingerName());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;


    }

}
