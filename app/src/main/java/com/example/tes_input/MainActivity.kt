package com.example.tes_input;

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tes_input.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton: Button = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            val intent = Intent(this, AddInfoActivity::class.java)
            startActivity(intent)
        }

        val displayButton: Button = findViewById(R.id.displayButton)
        displayButton.setOnClickListener {
            val intent = Intent(this, DisplayInfoActivity::class.java)
            startActivity(intent)
        }
    }
}
