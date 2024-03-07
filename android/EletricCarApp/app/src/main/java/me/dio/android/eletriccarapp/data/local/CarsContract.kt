package me.dio.android.eletriccarapp.data.local

import android.provider.BaseColumns

object CarsContract {
    object CarEntry: BaseColumns {
        const val TABLE_NAME = "cars"
        const val COLUMN_NAME_PRICE = "price"
        const val COLUMN_NAME_BATTERY = "battery"
        const val COLUMN_NAME_POTENCY = "potency"
        const val COLUMN_NAME_RECHARGE = "recharge"
        const val COLUMN_NAME_PHOTO_URL = "photo_url"
        const val COLUMN_NAME_IS_FAVORITE = "is_favorite"
    }

    const val TABLE_CAR = "CREATE TABLE ${CarEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${CarEntry.COLUMN_NAME_PRICE} TEXT NOT NULL," +
            "${CarEntry.COLUMN_NAME_BATTERY} TEXT NOT NULL," +
            "${CarEntry.COLUMN_NAME_POTENCY} TEXT NOT NULL," +
            "${CarEntry.COLUMN_NAME_RECHARGE} TEXT NOT NULL," +
            "${CarEntry.COLUMN_NAME_PHOTO_URL} TEXT NOT NULL," +
            "${CarEntry.COLUMN_NAME_IS_FAVORITE} INTEGER DEFAULT 0 NOT NULL," +
            ");"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${CarEntry.TABLE_NAME}"
}
