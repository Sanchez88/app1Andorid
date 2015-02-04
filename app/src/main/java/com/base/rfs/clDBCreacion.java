package com.base.rfs;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by elite88 on 04/02/2015.
 */
public class clDBCreacion extends SQLiteOpenHelper {


    private static String dbName = "DBLUZ";
    private String tb = "CREATE TABLE CONTADOR("+
    "CON_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
    "VALOR INTEGER NOT NULL," +
    "FECHA TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL" +
    ")";

    public clDBCreacion(Context context){
        super(context,dbName,null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CONTADOR");
        onCreate(db);
    }

    public void insContador(int valor){
        SQLiteDatabase base = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("VALOR", valor);
        base.insert("CONTADOR",null,values);
        base.close();
    }
}
