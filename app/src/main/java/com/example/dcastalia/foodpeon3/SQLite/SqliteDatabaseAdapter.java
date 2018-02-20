package com.example.dcastalia.foodpeon3.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by dcastalia on 2/18/18.
 */

public class SqliteDatabaseAdapter  {

    // we can get a reference here

    SqlHelper sqlHelper;




    // for INSTANTIATION sqlhelper we need a context, so make a constructor first

    public SqliteDatabaseAdapter(Context context) {
        sqlHelper=new SqlHelper(context);
    }


/*Create inset method here   */

    public long insertData( String id,String name, String address ){


        SQLiteDatabase db=sqlHelper.getWritableDatabase();
        //this object db represents the physical database stored on which i can perform the queries.
        ContentValues contentValues= new ContentValues();
        contentValues.put(sqlHelper.KID,id);
        contentValues.put(sqlHelper.KITCHEN_NAME,name);
        contentValues.put(sqlHelper.ADDRESS,address);



        // the insertContentID indicates the row id ofthe column that was inserted

        long insertContentID=db.insert(sqlHelper.TABLE_NAME,null,contentValues);

        return insertContentID;
    }

    public String getID(){
        SQLiteDatabase databaseAdapter= sqlHelper.getWritableDatabase();
        //select _id,Name,Address from kitchenDB

        String[] columns = {sqlHelper.KID};

        Cursor cursor= databaseAdapter.query(sqlHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()){
            int cid = cursor.getInt(0);
            stringBuffer.append(cid+"\n");


        }

        return stringBuffer.toString();
    }


    public String getName(){
        SQLiteDatabase databaseAdapter= sqlHelper.getWritableDatabase();
        //select _id,Name,Address from kitchenDB

        String[] columns = {sqlHelper.KITCHEN_NAME};

        Cursor cursor= databaseAdapter.query(sqlHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(sqlHelper.KITCHEN_NAME));
            stringBuffer.append(name+"\n");


        }

        return stringBuffer.toString();
    }

    public String getAddress(){
        SQLiteDatabase databaseAdapter= sqlHelper.getWritableDatabase();
        //select _id,Name,Address from kitchenDB

        String[] columns = {sqlHelper.ADDRESS};

        Cursor cursor= databaseAdapter.query(sqlHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()){
            String address = cursor.getString(cursor.getColumnIndex(sqlHelper.ADDRESS));
            stringBuffer.append(address+"\n");


        }

        return stringBuffer.toString();
    }







   static class SqlHelper extends SQLiteOpenHelper {
        Context context;

        private static final String DATABASE_NAME = "kitchenDB";
        private static final String TABLE_NAME = "KITCHEN";
        private static final int DATABASE_VERSION = 7;
        private static final String ADDRESS = "Address";
        private static final String KID = "_id";
        private static final String KITCHEN_NAME = "Name";
        private static final String CREATE_TABLE =    "CREATE TABLE "+TABLE_NAME+" ("+KID+" INTEGER," + " "+KITCHEN_NAME+" VARCHAR(255), "+ADDRESS+" VARCHAR(255));";


        public SqlHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context=context;
        }



        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            // CR
            try {
                sqLiteDatabase.execSQL(CREATE_TABLE);
            } catch (SQLException e) {
                //e.printStackTrace();
                Toast.makeText(context,
                        "create "+e,Toast.LENGTH_SHORT)
                        .show();
            }







        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {


            try {
                onCreate(sqLiteDatabase);
            } catch (Exception e) {
                //e.printStackTrace();
                Toast.makeText(context,
                        "upgrade "+e,Toast.LENGTH_SHORT)
                        .show();
            }


        }


    }

}
