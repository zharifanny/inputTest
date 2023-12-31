package com.example.tes_input;


import DBHelper
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.tes_input.R

class DisplayInfoActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_info)

        dbHelper = DBHelper(this)

        displayInfo()
    }

    private fun displayInfo() {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val projection = arrayOf(
            DBContract.InfoEntry.COLUMN_ID,
            DBContract.InfoEntry.COLUMN_NAME,
            DBContract.InfoEntry.COLUMN_AGE
        )

        val cursor: Cursor = db.query(
            DBContract.InfoEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val displayLayout: LinearLayout = findViewById(R.id.displayLayout)

        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.InfoEntry.COLUMN_NAME))
            val age = cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.InfoEntry.COLUMN_AGE))
            val id = cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.InfoEntry.COLUMN_ID))

            val deleteButton = Button(this)
            deleteButton.text = "Delete"
            deleteButton.setOnClickListener {
                deleteInfo(id)
                displayLayout.removeAllViews()
                displayInfo()
            }

            val infoText = "$name, $age years old"
            val infoButton = Button(this)
            infoButton.text = infoText

            val layout = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            displayLayout.addView(infoButton, layout)
            displayLayout.addView(deleteButton, layout)
        }
        cursor.close()
    }

    private fun deleteInfo(id: Long) {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val selection = "${DBContract.InfoEntry.COLUMN_ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        db.delete(DBContract.InfoEntry.TABLE_NAME, selection, selectionArgs)
    }
}
