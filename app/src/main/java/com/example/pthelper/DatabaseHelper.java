package com.example.pthelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PTHelper.db";

    //class to define table information
    public static class UserInfo implements BaseColumns {
        public static final String TABLE_NAME = "User_Info";
        public static final String COL_1 = "user_id";
        public static final String COL_2 = "username";
        public static final String COL_3 = "age";
        public static final String COL_4 = "gender";
        public static final String COL_5 = "height";
        public static final String COL_6 = "weight";
    }

    //user info schema
    private static final String SQL_CREATE_USER_ENTRIES =
            "CREATE TABLE " + UserInfo.TABLE_NAME + " (" +
                    UserInfo.COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    UserInfo.COL_2 + " TEXT," +
                    UserInfo.COL_3 + " INT," +
                    UserInfo.COL_4 + " TEXT," +
                    UserInfo.COL_5 + " INT," +
                    UserInfo.COL_6 + " INT)";

    private static final String SQL_DELETE_USER_ENTRIES =
            "DROP TABLE IF EXISTS " + UserInfo.TABLE_NAME;



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_USER_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean insertUser(String username, String gender, int age, int height, int weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserInfo.COL_2, username);
        contentValues.put(UserInfo.COL_3, gender);
        contentValues.put(UserInfo.COL_4, age);
        contentValues.put(UserInfo.COL_5, height);
        contentValues.put(UserInfo.COL_6, weight);
        long result = db.insert(UserInfo.TABLE_NAME, null, contentValues);
        if(result==-1) //insert failed
            return false;
        else
            return true;
    }

    public Cursor getAllUserData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + UserInfo.TABLE_NAME, null);
        return res;
    }

    public Cursor getUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select username from " + UserInfo.TABLE_NAME + " where username = ?", new String[] {"Tom"});
        return res;
    }
}
