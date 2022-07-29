package com.enma.hafiza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "puzzleDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Scores(id INTEGER primary key AUTOINCREMENT, name TEXT,mod TEXT,moves INTEGER,time DATETIME)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("drop Table if exists Scores");
    }

    public Boolean insertData (String name, int moves,String mod)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("moves",moves);
        contentValues.put("mod",mod);
        contentValues.put("time",System.currentTimeMillis());

        long result = db.insert("Scores",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getEasyData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select name,moves,time,mod from Scores where mod='easy' order by moves asc Limit 3 ",null);
        if (cursor != null && cursor.moveToFirst()) {
            do
            {
                return cursor;
            } while (cursor.moveToNext());
        }
        return cursor;
    }
    public Cursor getNormalData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name,moves,time FROM Scores where mod='normal' order by moves asc Limit 3 ",null);
        if (cursor != null && cursor.moveToFirst()) {
            do
            {
                return cursor;
            } while (cursor.moveToNext());
        }
        return cursor;
    }
    public Cursor getHardData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name,moves,time FROM Scores where mod='hard' order by moves asc Limit 3 ",null);
        if (cursor != null && cursor.moveToFirst()) {
            do
            {
                return cursor;
            } while (cursor.moveToNext());
        }
        return cursor;
    }
}
