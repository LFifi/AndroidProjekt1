package com.example.gra_memory;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gra_memory.data.db.PhotoDAO;
import com.example.gra_memory.pojo.Photo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class KameraActivity extends AppCompatActivity {
    private EditText t;
    private TextView tv;
    private Button b;
    private ImageView mImageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Uri photoURI;
    private PhotoDAO photoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //  mReaderDbHelper = new FeedReaderDbHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamera);
        b = (Button) findViewById(R.id.button3);
        t = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);
        mImageView = (ImageView) findViewById(R.id.imageView3);
        photoDAO = new PhotoDAO(this);
        dispatchTakePictureIntent();

    }


    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                String autoris = getApplicationContext().getPackageName() + ".fileprovider";
                // String mCurrentPhotoPath = photoFile.getAbsolutePath();


                photoURI = FileProvider.getUriForFile(this,
                        autoris,
                        photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String q = photoURI.toString();

        mImageView.setImageURI(Uri.parse(q));


    }

    // add photo
    public void addNewNote(View view) {
        Photo photo = new Photo();
        String text = t.getText().toString();
        String text2 = photoURI.toString();
        if (text.length() > 0) {
            photo.setCategory(text);
            photo.setUri(text2);
        }

        photoDAO.insertNote(photo);
        reloadNotesList();
    }


    // show photo uri
    private void reloadNotesList() {
        // pobieramy z bazy danych listę notatek
        List<Photo> allNotes = photoDAO.getAllNotes();

        for (int i = 0; i < allNotes.size(); i++) {
            String s = String.valueOf(i);
            //tv.setText(s);

            tv.setText(allNotes.get(i).getCategory() + "  " + allNotes.get(i).getUri() + "  " + s);

        }

    }
}






