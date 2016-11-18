package com.example.jose.codelab2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 16/11/16.
 */

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        Post post = (Post) intent.getSerializableExtra("myObject"); //We turn it in Post with "myObject" key

//TEXT SECTION
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(post.getMessage());
//PHOTO SECTION
        ImageView imageView = new ImageView(this);
        byte[] encodeByte = post.getImage();
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(encodeByte,0,encodeByte.length);
        imageView.setImageBitmap(imageBitmap);
//LAYOUT SECTION
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);
        imageView.setY(textView.getLineHeight());
        layout.addView(imageView);
    }
}
