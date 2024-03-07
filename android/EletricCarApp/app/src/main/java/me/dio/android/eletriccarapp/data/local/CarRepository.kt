package me.dio.android.eletriccarapp.data.local

import android.content.ContentValues
import android.content.Context
import android.util.Log
import me.dio.android.eletriccarapp.domain.Car

class CarRepository(private val context: Context) {
    fun save(car: Car) : Boolean {
        var isSaved = false
        try {
            val dbHelper = CarsDbHelper(context)
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(CarsContract.CarEntry.COLUMN_NAME_PRICE, car.price)
                put(CarsContract.CarEntry.COLUMN_NAME_BATTERY, car.battery)
                put(CarsContract.CarEntry.COLUMN_NAME_POTENCY, car.potency)
                put(CarsContract.CarEntry.COLUMN_NAME_RECHARGE, car.recharge)
                put(CarsContract.CarEntry.COLUMN_NAME_PHOTO_URL, car.photoUrl)
            }
            val inserted = db.insert(CarsContract.CarEntry.TABLE_NAME, null, values)
            if (inserted > 0) {
                isSaved = true
            }
        } catch (ex: Exception) {
            ex.message?.let {
                Log.e("Erro ao inserir ->", it)
            }
        }
        return isSaved
    }
}
