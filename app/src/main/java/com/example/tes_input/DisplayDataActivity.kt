//package com.example.tes_input;
//
//
//import DatabaseHelper
//import android.os.Bundle
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import com.example.tes_input.R
//
//class DisplayDataActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_display_data)
//
//        val dbHelper = DatabaseHelper(this)
//        val dataList = dbHelper.getAllData()
//
//        val textView = findViewById<TextView>(R.id.textView)
//        textView.text = dataList.joinToString("\n")
//    }
//}
