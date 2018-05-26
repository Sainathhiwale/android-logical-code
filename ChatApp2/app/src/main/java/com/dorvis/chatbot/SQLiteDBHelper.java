package com.dorvis.chatbot;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDBHelper extends SQLiteOpenHelper {
  private   static final  String DATABASE_NAME ="Users.db";
   private static  final int DATABASE_VERSION =1;

   public static final String TABLE_NAME= "user";
    public static final String COLUMN_USERNAME =  "name";
    public static  final String COLUMN_USERAGE = "age";
    public static final String COLUMN_USEREMAIL =  "email";
    public static final String COLUMN_USERGENDER =  "gender";
    public static final String COLUMN_USERMOBILE =  "mobile";

    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_USERAGE + " TEXT, "+
                    COLUMN_USEREMAIL + " TEXT, " +
                    COLUMN_USERGENDER + " TEXT, " +
                    COLUMN_USERMOBILE + " TEXT " + ")";

    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(CREATE_TABLE_QUERY);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
