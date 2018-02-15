package com.example.gra_memory.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.gra_memory.data.db.tables.Photos;
import com.example.gra_memory.pojo.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 09.02.2018.
 */

public class PhotoDAO {
    // obiekt umożliwiający dostęp do bazy danych
    private DBHelper dbHelper;

    public PhotoDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    // wstawienie nowej notatki do bazy danych
    public void insertNote(final Photo photo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Photos.Columns.PHOTO_CATEGORY, photo.getCategory() );
        contentValues.put(Photos.Columns.PHOTO_URI, photo.getUri());
        dbHelper.getWritableDatabase().insert(Photos.TABLE_NAME, null, contentValues);
    }

    // pobranie notatki na podstawie jej id
    public Photo getNoteById(final int id) {
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery("select * from " + Photos.TABLE_NAME + " where " + Photos.Columns.PHOTO_ID + " = " + id, null);
        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            return mapCursorToNote(cursor);
        }
        return null;
    }


    // usunięcie notatki z bazy
    public void deleteNoteById(final Integer id) {
        dbHelper.getWritableDatabase().delete(Photos.TABLE_NAME,
                " " + Photos.Columns.PHOTO_ID + " = ? ",
                new String[]{id.toString()}
        );
    }

    // pobranie wszystkich notatek
    public List getAllNotes() {
        Cursor cursor = dbHelper.getReadableDatabase().query(Photos.TABLE_NAME,
                new String[]{Photos.Columns.PHOTO_ID, Photos.Columns.PHOTO_CATEGORY, Photos.Columns.PHOTO_URI},
                null, null, null, null, null
        );

        List results = new ArrayList<>();

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                results.add(mapCursorToNote(cursor));
            }
        }

        return results;
    }

    // zamiana cursora na obiekt notatki
    private Photo mapCursorToNote(final Cursor cursor) {
        int idColumnId = cursor.getColumnIndex(Photos.Columns.PHOTO_ID);
        int textColumnId = cursor.getColumnIndex(Photos.Columns.PHOTO_CATEGORY);
        int textColumn2Id = cursor.getColumnIndex(Photos.Columns.PHOTO_URI);

        Photo photo = new Photo();
        photo.setId(cursor.getInt(idColumnId));
        photo.setCategory(cursor.getString(textColumnId));
        photo.setUri(cursor.getString(textColumn2Id));
        return photo;
    }
}
