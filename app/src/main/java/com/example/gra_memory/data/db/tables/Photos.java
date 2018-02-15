package com.example.gra_memory.data.db.tables;

/**
 * Created by lenovo on 09.02.2018.
 */

public interface Photos {
    String TABLE_NAME = "photos";

    interface Columns {
        String PHOTO_ID = "_id";
        String PHOTO_CATEGORY = "category";
        String PHOTO_URI = "uri";
    }
}
