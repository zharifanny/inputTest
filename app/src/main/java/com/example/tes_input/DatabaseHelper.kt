import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "NamesDB"
        private const val TABLE_NAME = "Names"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COL_NAME = "name"
        private const val COL_AGE = "age"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_NAME TEXT, " +
                "$COL_AGE INTEGER)"
        db.execSQL(createTableQuery)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertName(name: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, name)
        return db.insert(TABLE_NAME, null, contentValues)
    }

    // Inside DatabaseHelper.kt

    fun getAllNames(): List<String> {
        val namesList = mutableListOf<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_NAME FROM $TABLE_NAME", null)

        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
            namesList.add(name)
        }

        cursor.close()
        return namesList
    }

    fun insertNameAndAge(name: String, age: Int): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, name)
        contentValues.put(COL_AGE, age)
        return db.insert(TABLE_NAME, null, contentValues)
    }



}
