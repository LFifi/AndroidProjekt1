package com.example.gra_memory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gra_memory.data.db.PhotoDAO;
import com.example.gra_memory.pojo.Photo;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private PhotoDAO photoDAO;
   // private static FeedReaderDbHelper mReaderDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  mReaderDbHelper = new FeedReaderDbHelper(this);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView2);
        photoDAO = new PhotoDAO(this);
        List<Photo> allNotes = photoDAO.getAllNotes();

        for (int i = 0; i < allNotes.size(); i++) {
            String s = String.valueOf(i);
            //tv.setText(s);

            tv.setText(allNotes.get(i).getCategory() + "  " + allNotes.get(i).getUri() + "  " + s);

        }


    }

    public void klik(View view) {
        Intent intent;
        switch (view.getId())
        {
            case R.id.button :
                intent= new Intent(MainActivity.this, KameraActivity.class);
                startActivity(intent);
                break;
            case R.id.button2 :
                intent= new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
                break;
        }
    }
}
