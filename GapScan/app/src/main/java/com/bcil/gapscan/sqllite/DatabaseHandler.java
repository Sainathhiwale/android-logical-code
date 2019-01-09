package com.bcil.gapscan.sqllite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bcil.gapscan.model.Gapscan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by NG on 20-Jul-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "gapscandb";

    // Contacts table name
    private static final String TABLE_GAP_SCAN = "gapscantb";


    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_EANNO = "eanno";

    private Gapscan gapscan;
    private SQLiteDatabase db;
    private Cursor cursor;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SM_DETAIL_TABLE = "CREATE TABLE " + TABLE_GAP_SCAN + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_EANNO + " TEXT" +")";
        db.execSQL(CREATE_SM_DETAIL_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAP_SCAN);

        // Create tables again
        onCreate(db);
    }

    public void addEanScan(Gapscan gapscan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EANNO, gapscan.getEanno());
        // Inserting Row
        db.insert(TABLE_GAP_SCAN, null, values);
        db.close(); // Closing database connection
    }


    public Gapscan getEanScanInfo(String eanno) {
        try{
             db = this.getReadableDatabase();

             cursor = db.query(TABLE_GAP_SCAN, new String[] { KEY_ID,
                             KEY_EANNO }, KEY_EANNO + "=?",
                    new String[] { eanno }, null, null, null, null);
            if (cursor != null&&cursor.moveToFirst()){
                gapscan = new Gapscan(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1));
            }
        }catch (final Exception e){
            Log.d(DatabaseHandler.class.getSimpleName(),e.getMessage());

        }finally {
            if(cursor!=null)
                cursor.close();
            db.close();
        }


        return gapscan;
    }

    public boolean checkEanNoIfExist(String eanno){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT * FROM " + TABLE_GAP_SCAN + " WHERE " + KEY_EANNO + " = '" + eanno + "'";

        Cursor cursor = db.rawQuery(Query, null);
        if(cursor!=null){
            if(cursor.getCount() <= 0){
                cursor.close();
                return false;
            }
            cursor.close();
        }
        return true;
    }

    /*public Gapscan getScanBarcodeInfo(String scanBarcode) {
        try{
            db = this.getReadableDatabase();

            cursor = db.query(TABLE_GAP_SCAN, new String[] { KEY_ID,
                            KEY_SCAN_BIN,KEY_SCAN_BARCODE }, KEY_SCAN_BARCODE + "=?",
                    new String[] { scanBarcode }, null, null, null, null);
            if (cursor != null&&cursor.moveToFirst()){
                materialPutAway = new MaterialPutAway(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2));
            }
        }catch (final Exception e){
            Log.d(DatabaseHandler.class.getSimpleName(),e.getMessage());

        }finally {
            if(cursor!=null)
                cursor.close();
            db.close();
        }


        return materialPutAway;
    }*/

    public List<Gapscan> getAllGapScan() {
        List<Gapscan> gapscanList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_GAP_SCAN;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Gapscan gapscan = new Gapscan();
                gapscan.setId(Integer.parseInt(cursor.getString(0)));
                gapscan.setEanno(cursor.getString(1));
                gapscanList.add(gapscan);
            } while (cursor.moveToNext());
        }

        return gapscanList;
    }




    public void removeSingleEanNo(String eanno) {
        //Open the database
        SQLiteDatabase database = this.getWritableDatabase();

        //Execute sql query to remove from database
        //NOTE: When removing by String in SQL, value must be enclosed with ''
        database.execSQL("DELETE FROM " + TABLE_GAP_SCAN + " WHERE " + KEY_EANNO + "= '" + eanno + "'");

        //Close the database
        database.close();
    }


    public void removeSingleEanNoWrtId(String id) {
        //Open the database
        SQLiteDatabase database = this.getWritableDatabase();

        //Execute sql query to remove from database
        //NOTE: When removing by String in SQL, value must be enclosed with ''
        database.execSQL("DELETE FROM " + TABLE_GAP_SCAN + " WHERE " + KEY_ID + "= '" + id + "'");

        //Close the database
        database.close();
    }


    public  void deleteGapScantable(){
        db = this.getWritableDatabase();
        db.delete(TABLE_GAP_SCAN,null,null);

    }
//RecyclerView get data method
    public List<Gapscan> getAllResult() {
        String [] columns = {
            KEY_ID,
            KEY_EANNO
        };
        List<Gapscan> list = new ArrayList<Gapscan>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_GAP_SCAN, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,
                KEY_EANNO) ;    //filter by row groups); //The sort order

        if (cursor.moveToFirst()){
            do {
                Gapscan gapscan = new Gapscan();
                gapscan.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                gapscan.setEanno(cursor.getString(cursor.getColumnIndex(KEY_EANNO)));
                // Adding user record to list
                list.add(gapscan);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
}

