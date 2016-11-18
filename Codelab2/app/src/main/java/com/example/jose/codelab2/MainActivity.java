package com.example.jose.codelab2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
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
    int select;
    private ImageView mImageView;
    Bitmap imageBitmap;
    private static final int ACTION_TAKE_PHOTO_S = 2;
    Intent takePictureIntent;
    //Atributes for gallery
    Intent galleryIntent;
    String imgDecodableString;
    static final int RESULT_LOAD_IMG = 2;
    static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 3;

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
        //Atributes for gallery
        galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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
        select=0;
        dispatchTakePictureIntent();
    }

    /**
     * Called when the user clicks the Photo for Gallery
     */
    public void sendGalleryPhoto(View view) {
        select=1;
        dispatchTakePictureIntent();
    }

    /**
     * Called to call an action that takes the photo.
     */
    private void dispatchTakePictureIntent() {
        if(select==0)
            if (takePictureIntent.resolveActivity(getPackageManager()) != null)
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        if(select==1)
            if (galleryIntent.resolveActivity(getPackageManager()) != null)
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }



    /**
     * Called to retrieve the image and display an ImageView
     * @param requestCode
     * @param resultCode
     * @param data  Is only use to control small images
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(select==0)
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                imageBitmap = (Bitmap) extras.get("data");
                mImageView.setImageBitmap(imageBitmap);
            }
        if(select==1) {
            //This section request permition for reading the images
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {
                //Permission for reading
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (shouldShowRequestPermissionRationale(
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        // Explain to the user why we need to read the contacts
                    }

                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                    // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                    // app-defined int constant
                }
                try {
                    Bundle extras = data.getExtras();
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imgDecodableString = cursor.getString(columnIndex);
                    mImageView.setImageBitmap(BitmapFactory
                            .decodeFile(imgDecodableString));

                    mImageView.setDrawingCacheEnabled(true);
                    mImageView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    mImageView.layout(0, 0, mImageView.getMeasuredWidth(), mImageView.getMeasuredHeight());

                    mImageView.buildDrawingCache(true);
                    imageBitmap = Bitmap.createBitmap(mImageView.getDrawingCache());
                    mImageView.setDrawingCacheEnabled(false);

                } catch (Exception e) {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                            .show();
                }
            }
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
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);

            intent.putExtra("myObject", new Post(message, bs.toByteArray()));
            startActivity(intent);
        }
    }
}