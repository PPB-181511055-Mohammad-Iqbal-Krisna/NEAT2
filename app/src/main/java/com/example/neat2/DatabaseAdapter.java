package com.example.neat2;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseAdapter {
    static final String KEY_ROWID = "_id";
    static final String KEY_NAME = "task_name";
    static final String KEY_DETAILS = "task_detail";
    static final String KEY_DATE = "task_date";
    static final int DATABASE_VERSION = 2;
    static final String DB_NAME="taskDB";
    static final String DB_TABLE="task";
    static final String TAG = "DatabaseAdapter";
    static final String DATABASE_CREATE = "create table task(_id integer primary key autoincrement," + "task_name text not null, task_detail text, task_date text);";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DatabaseAdapter(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper{
        DatabaseHelper(Context context){
            super(context, DB_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            try{
                db.execSQL(DATABASE_CREATE);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            Log.w(TAG, "Upgrading database from version"+ oldVersion +"to"+newVersion+",which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }
    //opens the database
    public DatabaseAdapter open() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this;
    }
    //closes database
    public void close(){
        DBHelper.close();
    }
    //insert a contact into the database
    public long insertData(String name, String details,String date){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_DETAILS,details);
        initialValues.put(KEY_DATE,date);
        return db.insert(DB_TABLE,null, initialValues);
    }
    //deletes a particular contact
    public boolean deleteData(long rowId){
        return db.delete(DB_TABLE,KEY_ROWID +"="+ rowId, null)>0;
    }
    //retrieves all the contacts
    public Cursor getAllData(){
        return db.query(DB_TABLE, new String[] {KEY_ROWID,KEY_NAME,KEY_DETAILS,KEY_DATE},
                null, null, null, null, null);
    }

    public Cursor getData (long rowId) throws SQLException{
        Cursor mCursor=
                db.query(true, DB_TABLE, new String[]{KEY_ROWID,KEY_NAME,KEY_DETAILS,KEY_DATE},
                        KEY_ROWID + "=" + rowId, null, null, null, null,null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //updates a contact
    public boolean updateData(long rowId, String name, String details, String date){
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_DETAILS, details);
        args.put(KEY_DATE, date);
        return db.update(DB_TABLE, args, KEY_ROWID+"="+rowId,null)>0;
    }
}
