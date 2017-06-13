package com.example.charan_celigo.myexpenses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Expenses.db";
    public static final String CONTACTS_TABLE_NAME = "expenses";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "price";
    public static final String CONTACTS_COLUMN_EMAIL = "notes";

    public static final String EXPENSE_TYPE = "ExpenseType";
    public static final String EXPENSE_AMOUNT = "ExpenseAmount";
    public static final String EXPENSE_NOTES = "ExpenseNotes";
    public static final String EXPENSE_DATE = "Date";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table expenses " +
                        "(id integer primary key, " + EXPENSE_TYPE + " text," + EXPENSE_AMOUNT + " real, " + EXPENSE_NOTES + " text, " + EXPENSE_DATE + " text)"
        );

        db.execSQL(
                "create table expensetype " +
                        "(id integer primary key, " + EXPENSE_TYPE + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS expenses");
        onCreate(db);
    }

    public boolean insertExpense(HashMap<String, String> expDetails){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Set<String> keySet = expDetails.keySet();
        for (String key : keySet){
            if (key.equals(EXPENSE_AMOUNT)){
                contentValues.put(key, Double.parseDouble(expDetails.get(key)));
            }
            else {
                contentValues.put(key, expDetails.get(key));
            }
        }
        database.insert("expenses", null, contentValues);
        database.close();
        return true;
    }

    public HashMap<String, String> getAllExpenses(){

        ArrayList<HashMap<String, String>> expRows = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        String selectQuery = "select * from expenses";

/*
        SQLiteDatabase mDataBase;
        mDataBase = getReadableDatabase();
        Cursor dbCursor = mDataBase.query("expenses", null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        for (int i=0;i<columnNames.length;i++){
            map.put(i+"", columnNames[i]);
        }
*/


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String[] data = null;
        if (cursor.moveToFirst()) {
            do {
                // get  the  data into array,or class variable
                map.put(EXPENSE_TYPE, cursor.getString(1));
                map.put(EXPENSE_AMOUNT, cursor.getString(2));
                map.put(EXPENSE_NOTES, cursor.getString(0));

            } while (cursor.moveToNext());
        }


        db.close();
        return map;
    }

    public ArrayList<HashMap<String, String>> getExpenseDetails(String columns, String where){

        ArrayList<HashMap<String, String>> expRows = new ArrayList<HashMap<String, String>>();
        //HashMap<String, String> map = new HashMap<String, String>();
        String selectQuery = "select "+ columns +" from expenses";

/*
        SQLiteDatabase mDataBase;
        mDataBase = getReadableDatabase();
        Cursor dbCursor = mDataBase.query("expenses", null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        for (int i=0;i<columnNames.length;i++){
            map.put(i+"", columnNames[i]);
        }
*/


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String[] data = null;
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                // get  the  data into array,or class variable
                map.put(EXPENSE_TYPE, cursor.getString(1));
                map.put(EXPENSE_AMOUNT, cursor.getString(2));
                map.put(EXPENSE_NOTES, cursor.getString(3));
                expRows.add(map);

            } while (cursor.moveToNext());
        }


        db.close();
        return expRows;
    }
    public ArrayList<HashMap<String, String>> getExpenseDetailsWithCriteria() {
        ArrayList<HashMap<String, String>> expRows = new ArrayList<HashMap<String, String>>();
        return  expRows;
    }

    public ArrayList<HashMap<String, String>> getTableContents(String query) {
        ArrayList<HashMap<String, String>> tableContents = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String[] columnNames = cursor.getColumnNames();
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                // get  the  data into array,or class variable
                for (int i=0;i<columnNames.length;i++){
                    //Toast.makeText(, "Updated", Toast.LENGTH_SHORT).show();
                    map.put(columnNames[i], cursor.getString(i));
                }
                tableContents.add(map);

            } while (cursor.moveToNext());
        }
        return tableContents;
    }

    public boolean insertNewExpType(String newExpType){
        String selectQuery = "select "+ EXPENSE_TYPE +" from expensetype where " + EXPENSE_TYPE + "="+newExpType;
        ArrayList<HashMap<String, String>> al = getTableContents(selectQuery);

        for (HashMap<String, String> map : al){
            Set<String> rowNames = map.keySet();
            for (String rowName : rowNames){
                if (!rowName.equals("id")){
                    if (map.get(rowName).equals(newExpType))
                        return false;
                }
            }
        }

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXPENSE_TYPE, newExpType);
        database.insert("expesetype", null, contentValues);
        database.close();
        return true;
    }
}
