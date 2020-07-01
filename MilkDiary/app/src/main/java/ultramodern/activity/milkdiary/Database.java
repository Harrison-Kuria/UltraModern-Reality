package ultramodern.activity.milkdiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static String DATABASENAME = "MY_NOTES_DATABASE";
    private static int DATABASEVERSION = 1;
    private static String DATABASETABLE = "MY_NOTES_TABLE";


    private static String AMOUNT = "AMOUNT";
    private static String SMS = "SMS";
    private static String DATE = "DATE";


    public Database(Context context ) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String meza = " CREATE TABLE IF NOT EXISTS " + DATABASETABLE + "(" + AMOUNT + "INTEGER PRIMARY KEY, "
                + SMS + " TEXT , "
                + DATE + " TEXT NOT NULL " + ")";
        db.execSQL(meza);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            return;
        String upgrade = " DROP TABLE IF EXISTS " + DATABASETABLE;
        db.execSQL(upgrade);
    }


    public void addNote(ModelClass model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put(ID, model.getID());
        cv.put(AMOUNT, model.getAmount());
        cv.put(SMS,model.setSMS("CONFIRMED. Your Milk Sales today was ..."));
        cv.put(DATE, model.getDate());

        db.insert(DATABASETABLE, null, cv);
        Log.d("Inserted Value:","" + AMOUNT);
        Log.d("Inserted Value:","" + DATE);
    }

    public ModelClass getnote(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASETABLE,new String[]{AMOUNT,DATE}, AMOUNT +="?",new String[]{String.valueOf(id)},null,null,null);
        if (cursor !=null)
            cursor.moveToFirst();

        ModelClass model = new ModelClass(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
        return model;
    }

    public List<ModelClass> getnotes(){
        SQLiteDatabase db =this.getReadableDatabase();
        List<ModelClass> allnotes= new ArrayList<>();

        String query = "SELECT * FROM "+DATABASETABLE;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor != null){
            do {
                //ModelClass model = new ModelClass();
                //model.setAmount(cursor.getInt(0));
                //model.setSMS(cursor.getString(1));
                //model.setDate(cursor.getString(3));


                //allnotes.add(model);
            }while(cursor.moveToNext());
        }
        return allnotes;
    }

}

