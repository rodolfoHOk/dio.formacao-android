package me.dio.android.eletriccarapp.data

import me.dio.android.eletriccarapp.domain.Car
import retrofit2.Call
import retrofit2.http.GET

interface CarsApi {

    @GET("cars.json")
    fun getAllCars() : Call<List<Car>>

}
