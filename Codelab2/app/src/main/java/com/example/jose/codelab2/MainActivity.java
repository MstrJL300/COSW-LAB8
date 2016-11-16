package com.example.jose.codelab2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    List<Post> postList = new ArrayList<>();
    Intent intent;
    //MESSAGE
    Intent messageIntent;
    EditText editText;

    //PHOTO
    private ImageView mImageView;
    Bitmap imageBitmap;
    private static final int ACTION_TAKE_PHOTO_S = 2;
    Intent takePictureIntent;

    //SAVE
    DialogFragment newFragment = new MessageDialogFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, PostActivity.class);
        //MESSAGE
        messageIntent = new Intent(this, DisplayMessageActivity.class);
//        editText = (EditText) findViewById(R.id.edit_message);
        //PHOTO
        mImageView = (ImageView) findViewById(R.id.imageView1);
        imageBitmap = null;
        takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //SAVE
    }

    //MESSAGE SECTION

    /**
     * Called when the user clicks the Send button
     */
    public void sendMessage(View view) {
        editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        messageIntent.putExtra(EXTRA_MESSAGE, message);
        startActivity(messageIntent);
    }

    //PHOTO SECTION

    /**
     * Called when the user clicks the Photo button
     */
    public void sendPhoto(View view) {
        dispatchTakePictureIntent();
    }

    /**
     * Called to call an action that takes the photo.
     */
    private void dispatchTakePictureIntent() {
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    /**
     * Called to retrieve the image and display an ImageView
     * @param requestCode
     * @param resultCode
     * @param data  Is only use to control small images
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    //SAVE SECTION

    /**
     * Called when the user clicks the Save button
     */
    public void save(View view) {
        editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
//        System.out.println("|||"+message.isEmpty());
        if(message.isEmpty() && imageBitmap == null)
            newFragment.show(getSupportFragmentManager(), "misiles"); //Shows the dialog
        else {
//            postList.add(new Post(message, imageBitmap));
            System.out.println("1|||"+imageBitmap); //Lo que pasa es que imageBitmap no es serializable, por lo que la actividad la toma como null.
            intent.putExtra("myObject", new Post(message, imageBitmap));
            startActivity(intent);
        }
    }
}