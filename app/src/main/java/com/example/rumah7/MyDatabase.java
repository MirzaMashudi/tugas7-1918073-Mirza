package com.example.rumah7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "db_jaket";
    private static final String tb_jaket = "tb_jaket";
    private static final String tb_jaket_id = "id";
    private static final String tb_jaket_nama = "nama";
    private static final String tb_jaket_jenis = "jenis";
    private static final String CREATE_TABLE_JAKET = "CREATE TABLE " + tb_jaket +"("
            + tb_jaket_id + " INTEGER PRIMARY KEY ,"
            + tb_jaket_nama + " TEXT ,"
            + tb_jaket_jenis + " TEXT " + ")";
    public MyDatabase (Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_JAKET);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
    public void CreateJaket(Jaket data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_jaket_id, data.get_id());
        values.put(tb_jaket_nama, data.get_nama());
        values.put(tb_jaket_jenis, data.get_jenis());
        db.insert(tb_jaket, null, values);
        db.close();
    }
    public List<Jaket> ReadJaket() {
        List<Jaket> listMhs = new ArrayList<Jaket>();
        String selectQuery = "SELECT * FROM " + tb_jaket;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Jaket data = new Jaket();
                data.set_id(cursor.getString(0));
                data.set_nama(cursor.getString(1));
                data.set_jenis(cursor.getString(2));
                listMhs.add(data);
            } while (cursor.moveToNext());
        }
        db.close();
        return listMhs;
    }
    public int UpdateJaket (Jaket data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_jaket_nama, data.get_nama());
        values.put(tb_jaket_jenis, data.get_jenis());
        return db.update(tb_jaket, values, tb_jaket_id +
                        " = ?",
                new String[]{String.valueOf((data.get_id()))});
    }
    public void DeleteJaket(Jaket data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_jaket,tb_jaket_id+ " = ?",
                new String[]{String.valueOf(data.get_id())});
        db.close();
    }
}

