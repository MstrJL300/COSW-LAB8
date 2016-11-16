package com.example.jose.codelab2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 16/11/16.
 */

public class PostActivity extends AppCompatActivity {
    List<Post> postList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        Post message = (Post) intent.getSerializableExtra("myObject");

        System.out.println("2|||"+message.getImage());

        TextView textView = new TextView(this);
        textView.setTextSize(50);
        textView.setText(message.getMessage());

        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(message.getImage());

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);
        layout.addView(imageView);
    }
}
