package com.example.galen.db_contacts.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Galen on 12/8/17.
 */

public class DBOpenHelper extends SQLiteOpenHelper{
    final static String DB_NAME = "mycontacts.db";
    final static int DB_VERSION = 7;

    public DBOpenHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //NotesTable.onCreate(sqLiteDatabase);
        ContactsTable.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        ContactsTable.onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }
}

