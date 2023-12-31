package com.example.tes_input;


import DBHelper
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tes_input.R

class AddInfoActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_info)

        dbHelper = DBHelper(this)

        val nameEditText: EditText = findViewById(R.id.nameEditText)
        val ageEditText: EditText = findViewById(R.id.ageEditText)
        val saveButton: Button = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val age = ageEditText.text.toString().trim()

            if (name.isNotEmpty() && age.isNotEmpty()) {
                val db: SQLiteDatabase = dbHelper.writableDatabase
                val values = ContentValues().apply {
                    put(DBContract.InfoEntry.COLUMN_NAME, name)
                    put(DBContract.InfoEntry.COLUMN_AGE, age.toInt())
                }

                val newRowId = db.insert(DBContract.InfoEntry.TABLE_NAME, null, values)
                if (newRowId != -1L) {
                    Toast.makeText(this, "Info saved successfully", Toast.LENGTH_SHORT).show()
                    nameEditText.text.clear()
                    ageEditText.text.clear()
                } else {
                    Toast.makeText(this, "Error saving info", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter both name and age", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
