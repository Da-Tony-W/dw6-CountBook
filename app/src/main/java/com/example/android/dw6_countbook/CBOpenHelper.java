/**
 * Class Name: CBOpenHelper
 *  Version = 1
 * Created by Tony on 2017/9/26.
 */

package com.example.android.dw6_countbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "counters.db";
    private static final int DATABASE_VERSION = 1;


    public static final String TABLE_NOTES = "notes";

    public static final String NOTE_ID = "_id";

    public static final String NOTE_TEXT = "noteText";

    public static final String NOTE_CREATED = "noteCreated";



//SQL to create table

    private static final String TABLE_CREATE =

            "CREATE TABLE " + TABLE_NOTES + " (" +

                    NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    NOTE_TEXT + " TEXT, " +

                    NOTE_CREATED + " TEXT default CURRENT_TIMESTAMP" +

                    ")";




    public CBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
