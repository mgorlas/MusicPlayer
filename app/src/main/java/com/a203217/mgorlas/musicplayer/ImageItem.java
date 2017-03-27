package com.a203217.mgorlas.musicplayer;

import android.graphics.Bitmap;

/**
 * Created by marze on 09.03.2017.
 */

public class ImageItem {
    private Bitmap image;
    private int id;
    private int musicId;

    public ImageItem(Bitmap image, int id, int musicId) {
        super();
        this.image = image;
        this.id = id;
        this.musicId = musicId;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getId(){
        return id;
    }

    public int getMusicId(){
        return musicId;
    }
}
