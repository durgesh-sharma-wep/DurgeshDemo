package com.example.itemdatabaseactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper( Context context) {
        super(context,"user_login_registration.db",null, 5); // database name and version
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE UserLoginDetails(ID INTEGER PRIMARY  KEY AUTOINCREMENT, USERNAME TEXT,PASSWORD TEXT )"); // crating user details tables
        sqLiteDatabase.execSQL("CREATE TABLE VehicleItemDataBase(ID INTEGER PRIMARY  KEY AUTOINCREMENT, VEHICLECODE TEXT,VEHICLETYPE TEXT,PARKINGCHARGE TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE InEntryDatabase(ID INTEGER PRIMARY KEY AUTOINCREMENT,INVECHCODE TEXT,INVECHTYPE TEXT,INVECHNUM TEXT,DAY INTEGER,MONTH INTEGER,YEAR INTERGER,HH INETGER,MM INTERGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS UserLoginDetails");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS VehicleItemDataBase");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS InEntryDatabase");
        onCreate(sqLiteDatabase);
    }

    /*-------------------------------------- User Login Details ------------------------------------------------------------*/

    /**********************************************************************
                       Add new users
     *********************************************************************/
    public long AddUserDetails(String username, String password)
    {
        ContentValues contentValues =new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.insert("UserLoginDetails",null,contentValues); //insert details in table
        db.close();
        return res;
    }

    /********************************************************************
                      Delete user
     *******************************************************************/
    public void DeleteUserDetails(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("UserLoginDetails","Username='" + username+"'",null);
    }

    /*******************************************************************
                                Check users
     *******************************************************************/
    public boolean checkUser(String username, String password)
    {
        String[] columns = { "ID" };
        SQLiteDatabase db = getWritableDatabase();
        String selection = "USERNAME" + "=?" + " and " + "PASSWORD" + "=?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query("UserLoginDetails",columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return true;
        else
            return false;

    }
/*-------------------------------------------------------- Vehicle Database --------------------------------------------------------------------------*/
    /**********************************************************************
                          Add Vehicle in Database
     *********************************************************************/
    public long AddVehicleDetails(String vehiclecode, String vehicletype,String parkingcharge)
    {
        ContentValues contentValues =new ContentValues();
        contentValues.put("vehiclecode",vehiclecode);
        contentValues.put("vehicletype",vehicletype);
        contentValues.put("parkingcharge",parkingcharge);
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.insert("VehicleItemDataBase",null,contentValues); //insert details in table
        db.close();
        return res;
    }


    /********************************************************************
                      View content from Table VehicleItemDataBase
     *******************************************************************/

    public Cursor GetListData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor Data = db.rawQuery("SELECT * FROM VehicleItemDataBase",null);
        return Data;
    }

    /********************************************************************
                      Get ID from Table 'VehicleItemDataBase'
     *******************************************************************/

    public Cursor GetListID(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT ID  FROM VehicleItemDataBase WHERE VEHICLETYPE = '" + name + "'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    /********************************************************************
                       Update Row
     *******************************************************************/

    public void UpdateVechname(int id, String code, String Name, String price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE VehicleItemDataBase SET VEHICLETYPE = '" + Name + "',VEHICLECODE = '" + code + "',PARKINGCHARGE = '" + price + "' WHERE ID = '" + id + "';" ;
       db.execSQL(query);
    }

    /********************************************************************
                     Delete Row
     *******************************************************************/
    public void DeleteVehicleDetails(int id , String vehiclename)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM VehicleItemDataBase WHERE ID" + "= '" + id + "'" + "AND VEHICLETYPE" + "= '" + vehiclename + "'" ;
        db.execSQL(query);
    }
    /********************************************************************
                       Erase database
     *******************************************************************/
    public void EraseDatabase()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM VehicleItemDataBase " ;
        db.execSQL(query);
    }

    /*------------------------------------------------ In Entry Details -----------------------------------------------*/


    /**********************************************************************
                       IN Vehicle Entry
     *********************************************************************/
    public long InVehicleEntry(String vechcode, String vechtype,String vechnum,int day, int month, int year, int hour,int min)
    {
        ContentValues contentValues =new ContentValues();
        contentValues.put("INVECHCODE",vechcode);
        contentValues.put("INVECHTYPE",vechtype);
        contentValues.put("INVECHNUM",vechnum);
        contentValues.put("DAY",day);
        contentValues.put("MONTH",month);
        contentValues.put("YEAR",year);
        contentValues.put("HH",hour);
        contentValues.put("MM",min);
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.insert("InEntryDatabase",null,contentValues); //insert details in table
        db.close();
        return res;
    }
}
