package com.example.android.musicalstructure;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<Song> songList = new ArrayList<Song>();

        //get songs names
        Field[] fields = R.raw.class.getFields();
        String  singers[]= {"Kazem_Al-Saher","nancy","Shadi-Aswad","majeda-Elromy","Wael-Kfori","goerge","najwa","Amr Dyab"};
        //initialize songs list
        for (int count = 0; count < fields.length; count++) {
            songList.add(new Song(fields[count].getName(),singers[count]));
        }

        SongAdapter songsAdapter = new SongAdapter(this, songList);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(songsAdapter);

    }
}
