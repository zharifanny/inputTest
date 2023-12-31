import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tes_input.DBContract

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_ENTRIES = """
            CREATE TABLE ${DBContract.InfoEntry.TABLE_NAME} (
                ${DBContract.InfoEntry.COLUMN_ID} INTEGER PRIMARY KEY,
                ${DBContract.InfoEntry.COLUMN_NAME} TEXT,
                ${DBContract.InfoEntry.COLUMN_AGE} INTEGER
            )
        """.trimIndent()

        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${DBContract.InfoEntry.TABLE_NAME}")
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Info.db"
    }
}
