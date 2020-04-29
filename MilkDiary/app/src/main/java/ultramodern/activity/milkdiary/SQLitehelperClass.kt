package ultramodern.activity.milkdiary

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.JsonReader
import kotlinx.android.synthetic.main.activity_my_milk.*
import org.json.JSONObject
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.System.`in`


open class SQLiteHelperClass() : SQLiteOpenHelper(null, DATABASE_NAME, null, VERSION_NUMBER) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" + LITRES + "INTEGER," + DATE + "TIMESTAMP" + ")"
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS DATABASE_NAME")
        onCreate(db)
    }
    fun jsonread(){
        val jsonwriter = JsonReader(InputStreamReader(`in`,"UTF-8"))
        jsonwriter.beginObject()
        jsonwriter
        jsonwriter.endObject()
    }
    fun addqty() {
        val db: SQLiteDatabase = this.getWritableDatabase()

        val values = ContentValues()
        values.put(LITRES,jsonread().toString())
        //values.put(LITRES,"harry")
        //values.put(DATE, "${MyMilk().textView11.text}")
        db.insert(LITRES, null, values)
        db.close() // Closing database connection
    }



    companion object {
        const val VERSION_NUMBER = 1
        const val DATABASE_NAME = "MAZIWAPP"
        const val TABLE_NAME = "MAZIWA"
        const val LITRES = "LITRES"
        const val DATE = "DATE"
    }
}