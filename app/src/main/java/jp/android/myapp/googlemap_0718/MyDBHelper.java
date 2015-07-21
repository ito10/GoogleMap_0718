package jp.android.myapp.googlemap_0718;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ItoYuhei on 2015/07/18.
 */
public class MyDBHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "myDatabase.db";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "myData";

    static final String ID = "id";
    static final String REG_NAME = "Name";
    static final String REG_ADDRESS = "Address";
    static final String REG_BROWSE = "Browse";
    static final String REG_BEAUTY = "Beauty";
    static final String REG_SERVICE = "Service";
    static final String REG_CUSTOMER = "Customer";
    static final String REG_TOILET = "Toilet";


    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY autoincrement," +
                REG_NAME + " TEXT," +
                REG_ADDRESS + " TEXT," +
                REG_BROWSE + " TEXT," +
                REG_BEAUTY + " TEXT," +
                REG_SERVICE + " TEXT," +
                REG_CUSTOMER + " TEXT," +
                REG_TOILET + " TEXT);";
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

}