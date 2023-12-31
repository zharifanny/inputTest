package com.example.tes_input;
import DatabaseHelper
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var displayTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val saveButton = findViewById<Button>(R.id.saveButton)
        displayTextView = findViewById(R.id.displayTextView)
        val displayButton = findViewById<Button>(R.id.displayButton)



        saveButton.setOnClickListener {
            val name = editTextName.text.toString().trim()

            if (name.isNotEmpty()) {
                val insertedRowId = dbHelper.insertName(name)
                if (insertedRowId != -1L) {
                    // Data inserted successfully
                    editTextName.text.clear()
                    updateDisplay()

                    Toast.makeText(this, "Name inserted successfully", Toast.LENGTH_SHORT).show()
                } else {
                    // Failed to insert data
                    Toast.makeText(this, "Failed to insert name", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            }
        }

        displayButton.setOnClickListener {
            updateDisplay()
        }
        updateDisplay()

    }


    private fun updateDisplay() {
        val names = dbHelper.getAllNames()
        displayTextView.text = names.joinToString("\n")
    }

}
