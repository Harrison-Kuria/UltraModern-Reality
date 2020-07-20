package ultramodern.activity.sqlitedatabasetest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor cursor = null;


    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    //returning instance of database
    public static DatabaseAccess getInstance(Context context){
        if (instance==null){
            instance=new DatabaseAccess(context);
        }
        return instance;
    }
    //method to open database connection
    public void open(){
        this.db = openHelper.getWritableDatabase();
    }
    //method to close database connection
    public void close(){
        if (db!=null){
            this.db.close();
        }
    }
    //method to query and return result from database
    public String getAddress(String name){
        cursor = db.rawQuery("select Address from Table1 where Name = '"+name+"'",new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            String address = cursor.getString(0);
            buffer.append(""+address);
        }
        return buffer.toString();
    }
}
