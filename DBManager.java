package com.example.gettingrecentdateandtime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBManager extends SQLiteOpenHelper
{
    static final String dbname="expchart.db";
    static final String tbl_name="tbl_testcharts";

    BarChartActivity baract;

    public DBManager(@Nullable Context context)
    {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String qry="CREATE TABLE tbl_testcharts( id Integer primary key autoincrement, year text, month text, day text, expense text, amount text, status text )";
        db.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String qry="DROP TABLE IF EXISTS tbl_testcharts";
        db.execSQL(qry);
        onCreate(db);

    }

    public String AddData(String year, String month, String day, String exp, String amt)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put("year",year);
        cv.put("month",month);
        cv.put("day",day);
        cv.put("expense",exp);
        cv.put("amount",amt);

        float res=db.insert("tbl_testcharts",null,cv);

        if (res==-1)
        {
            return "Failed to Insert";
        }else
        {
            return "Saved Successfully";
        }

    }

    //METHOD FOR RETURN DATE AND EVENTS
    public Cursor GettingDataBack()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String qry="SELECT * FROM tbl_testcharts";
        Cursor cursor=db.rawQuery(qry,null);
        return cursor;
    }

    public Cursor getdataWithMonth()
    {
        baract=new BarChartActivity();
        SQLiteDatabase db=this.getReadableDatabase();
        String qry=("SELECT day, amount FROM tbl_testcharts WHERE month=?"+baract.newmonth);  //new String[]{month});
        Cursor cursor=db.rawQuery(qry,null);
        return cursor;
    }
}
