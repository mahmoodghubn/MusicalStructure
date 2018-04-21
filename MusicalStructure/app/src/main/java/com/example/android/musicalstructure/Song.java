package com.example.android.musicalstructure;

public class Song {
    private String songName ;
    private String singerName;

    public Song(String songName,String singerName ){
        setSongName(songName);
        setSingerName(singerName);

    }
    public void setSingerName(String singerName){
        this.singerName= singerName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSongName(String songName){
        this.songName = songName;

    }
    public String getSongName(){
        return songName;
    }


}
