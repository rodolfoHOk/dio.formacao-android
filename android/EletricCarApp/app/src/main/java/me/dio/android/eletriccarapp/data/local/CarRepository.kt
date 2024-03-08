package me.dio.android.eletriccarapp.data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import android.widget.Toast
import me.dio.android.eletriccarapp.domain.Car

class CarRepository(private val context: Context) {
    fun save(car: Car) : Boolean {
        var isSaved = false
        try {
            val dbHelper = CarsDbHelper(context)
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(CarsContract.CarEntry.COLUMN_NAME_CAR_ID, car.id)
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

    fun findById(id: Int) : Car? {
        try {
            val dbHelper = CarsDbHelper(context)
            val db = dbHelper.readableDatabase
            val columns = arrayOf(
                BaseColumns._ID,
                CarsContract.CarEntry.COLUMN_NAME_CAR_ID,
                CarsContract.CarEntry.COLUMN_NAME_PRICE,
                CarsContract.CarEntry.COLUMN_NAME_BATTERY,
                CarsContract.CarEntry.COLUMN_NAME_POTENCY,
                CarsContract.CarEntry.COLUMN_NAME_RECHARGE,
                CarsContract.CarEntry.COLUMN_NAME_PHOTO_URL,
                CarsContract.CarEntry.COLUMN_NAME_IS_FAVORITE
            )
            val filter = "${CarsContract.CarEntry.COLUMN_NAME_CAR_ID} = ?"
            val filterValues = arrayOf(id.toString())
            val cursor = db.query(
                CarsContract.CarEntry.TABLE_NAME,
                columns,
                filter,
                filterValues,
                null,
                null,
                null
            )
            with(cursor) {
                while (moveToNext()) {
                    val itemId =
                        getString(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_CAR_ID))
                    val itemPrice =
                        getString(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_PRICE))
                    val itemBattery =
                        getString(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_BATTERY))
                    val itemPotency =
                        getString(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_POTENCY))
                    val itemRecharge =
                        getString(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_RECHARGE))
                    val itemPhotoUrl =
                        getString(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_PHOTO_URL))
                    val itemIsFavorite =
                        getInt(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_IS_FAVORITE))
                    return Car(
                        id = itemId.toInt(),
                        price = itemPrice,
                        battery = itemBattery,
                        potency = itemPotency,
                        recharge = itemRecharge,
                        photoUrl = itemPhotoUrl,
                        isFavorite = itemIsFavorite == 1,
                    )
                }
            }
            cursor.close()
            return null
        } catch (ex: Exception) {
            ex.message?.let {
                Log.e("Erro ao buscar ->", it)
            }
            return null
        }
    }

    fun saveIfNotExist(car: Car) {
        val existCar = findById(car.id)
        if (existCar == null) {
            save(car)
        }
    }

    fun getAll() : List<Car> {
        try {
            val dbHelper = CarsDbHelper(context)
            val db = dbHelper.readableDatabase
            val columns = arrayOf(
                BaseColumns._ID,
                CarsContract.CarEntry.COLUMN_NAME_CAR_ID,
                CarsContract.CarEntry.COLUMN_NAME_PRICE,
                CarsContract.CarEntry.COLUMN_NAME_BATTERY,
                CarsContract.CarEntry.COLUMN_NAME_POTENCY,
                CarsContract.CarEntry.COLUMN_NAME_RECHARGE,
                CarsContract.CarEntry.COLUMN_NAME_PHOTO_URL,
                CarsContract.CarEntry.COLUMN_NAME_IS_FAVORITE
            )
            val cursor = db.query(
                CarsContract.CarEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
            )
            val carsList = mutableListOf<Car>()
            with(cursor) {
                while (moveToNext()) {
                    val itemId =
                        getString(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_CAR_ID))
                    val itemPrice =
                        getString(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_PRICE))
                    val itemBattery =
                        getString(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_BATTERY))
                    val itemPotency =
                        getString(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_POTENCY))
                    val itemRecharge =
                        getString(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_RECHARGE))
                    val itemPhotoUrl =
                        getString(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_PHOTO_URL))
                    val itemIsFavorite =
                        getInt(getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_IS_FAVORITE))
                    val car = Car(
                        id = itemId.toInt(),
                        price = itemPrice,
                        battery = itemBattery,
                        potency = itemPotency,
                        recharge = itemRecharge,
                        photoUrl = itemPhotoUrl,
                        isFavorite = itemIsFavorite == 1,
                    )
                    carsList.add(car)
                }
            }
            cursor.close()
            return carsList
        } catch (ex: Exception) {
            ex.message?.let {
                Log.e("Erro ao buscar ->", it)
            }
            return emptyList()
        }
    }

    fun deleteById(id: Int) {
        try {
            val existCar = findById(id)
            if (existCar != null) {
                val dbHelper = CarsDbHelper(context)
                val db = dbHelper.writableDatabase
                val filter = "${CarsContract.CarEntry.COLUMN_NAME_CAR_ID} = ?"
                val filterValues = arrayOf(id.toString())
                db.delete(
                    CarsContract.CarEntry.TABLE_NAME,
                    filter,
                    filterValues
                )
            } else {
                throw Exception("Carro não encontrado com o id informado")
            }
        } catch (ex: Exception) {
            ex.message?.let {
                Log.e("Erro ao deletar ->", it)
            }

        }
    }
}
