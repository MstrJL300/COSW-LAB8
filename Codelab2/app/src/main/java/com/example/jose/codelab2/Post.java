package com.example.jose.codelab2;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Created by jose on 16/11/16.
 */

public class Post implements java.io.Serializable {

    String message;
    byte[] image;

    Post(String message, byte[] image){
        this.message = message;
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
