package com.example.jose.codelab2;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by jose on 16/11/16.
 */

public class Post implements java.io.Serializable {

    String message;
    transient Bitmap image;

    Post(String message, Bitmap image){
        this.message = message;
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
