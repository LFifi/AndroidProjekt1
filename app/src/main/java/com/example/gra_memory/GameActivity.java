package com.example.gra_memory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gra_memory.data.db.PhotoDAO;
import com.example.gra_memory.pojo.Photo;

import java.io.File;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private PhotoDAO photoDAO;
    private ImageView mImageView1;
    private ImageView mImageView2;
    private ImageView mImageView3;
    private ImageView mImageView4;

    private int count=0;
    private int swap1 = -1;
    private int swap2 = -1;
    private int swap11 = -1;
    private int swap22 = -1;
    private int tab[] = {-1, -1, -1, -1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mImageView1 = (ImageView) findViewById(R.id.imageView1);
        mImageView2 = (ImageView) findViewById(R.id.imageView2);
        mImageView3 = (ImageView) findViewById(R.id.imageView3);
        mImageView4 = (ImageView) findViewById(R.id.imageView4);


        photoDAO = new PhotoDAO(this);
        final List<Photo> allNotes = photoDAO.getAllNotes();

       //Random photo
        allNotes.size();
        Random r = new Random();
        for (int i = 0; i < 2; i++) {
            int a = r.nextInt(allNotes.size());
            for (int j = 0; j < 2; j++) {
                while (true) {
                    int b = r.nextInt(4);
                    if (tab[b] == -1) {
                        tab[b] = a;
                        break;

                    }
                }
            }
        }
//paint
        mImageView1.setColorFilter(0xff000000);
        mImageView2.setColorFilter(0xff000000);
        mImageView3.setColorFilter(0xff000000);
        mImageView4.setColorFilter(0xff000000);
        //set pgoto
        mImageView1.setImageURI(Uri.parse(allNotes.get(tab[0]).getUri()));
        mImageView2.setImageURI(Uri.parse(allNotes.get(tab[1]).getUri()));
        mImageView3.setImageURI(Uri.parse(allNotes.get(tab[2]).getUri()));
        mImageView4.setImageURI(Uri.parse(allNotes.get(tab[3]).getUri()));

    }

    public void PhotoClick(View view) {

        switch (view.getId()) {
            case R.id.imageView1:
                if (swap1 == -1 && tab[0] != -2) {
                    mImageView1.setColorFilter(0x00000000);
                    swap1 = tab[0];
                    swap11 = 0;
                } else {                        //>
                    if (swap2 == -1 && tab[0] != -2 && swap1 != -1 && swap11 != 0) {
                        mImageView1.setColorFilter(0x00000000);
                        swap2 = tab[0];
                        swap22 = 0;
                    }
                }
                break;
            case R.id.imageView2:
                if (swap1 == -1 && tab[1] != -2) {
                    mImageView2.setColorFilter(0x00000000);
                    swap1 = tab[1];
                    swap11 = 1;
                } else {
                    if (swap2 == -1 && tab[1] != -2 && swap1 != -1 && swap11 != 1) {
                        mImageView2.setColorFilter(0x00000000);
                        swap2 = tab[1];
                        swap22 = 1;
                    }
                }

                break;
            case R.id.imageView3:
                if (swap1 == -1 && tab[2] != -2) {
                    mImageView3.setColorFilter(0x00000000);
                    swap1 = tab[2];
                    swap11 = 2;
                } else {
                    if (swap2 == -1 && tab[2] != -2 && swap1 != -1 && swap11 != 2) {
                        mImageView3.setColorFilter(0x00000000);
                        swap2 = tab[2];
                        swap22 = 2;
                    }
                }

                break;
            case R.id.imageView4:
                if (swap1 == -1 && tab[3] != -2) {
                    mImageView4.setColorFilter(0x00000000);
                    swap1 = tab[3];
                    swap11 = 3;
                } else {
                    if (swap2 == -1 && tab[3] != -2 && swap1 != -1 && swap11 != 3) {
                        mImageView4.setColorFilter(0x00000000);
                        swap2 = tab[3];
                        swap22 = 3;
                    }
                }

                break;
        }


        if (swap1 > -1 && swap2 > -1 && swap1 == swap2) {
            tab[swap11] = -2;
            tab[swap22] = -2;
            swap1 = -1;
            swap2 = -1;
            swap11 = -1;
            swap22 = -1;
            count++;
            if(count==2)
                Toast.makeText(GameActivity.this,"You WIN !!!",Toast.LENGTH_LONG).show();
        } else {
            if (swap1 != swap2 && swap2 != -1) {
                swap1 = -1;
                swap2 = -1;
                swap11 = -1;
                swap22 = -1;
                if (tab[0] != -2) {
                    mImageView1.setColorFilter(0xff000000);
                }
                if (tab[1] != -2) {
                    mImageView2.setColorFilter(0xff000000);
                }
                if (tab[2] != -2) {
                    mImageView3.setColorFilter(0xff000000);
                }
                if (tab[3] != -2) {
                    mImageView4.setColorFilter(0xff000000);
                }

            }
        }
    }


    public void Back(View view) {
        Intent intent;
        finish();
        intent = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent);

    }
}

