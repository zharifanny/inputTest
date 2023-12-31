package com.example.tes_input;


import android.provider.BaseColumns

object DBContract {

    object InfoEntry : BaseColumns {
        const val TABLE_NAME = "info"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
    }
}
