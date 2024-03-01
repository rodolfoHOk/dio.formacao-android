package me.dio.android.eletriccarapp.data

import me.dio.android.eletriccarapp.domain.Car

class CarFactory {
    companion object {
        val list = listOf(
            Car(
                id = 1,
                price = "R$ 300.000,00",
                battery = "300 kWh",
                potency = "200 cv",
                recharge = "30 min",
                photoUrl = "www.google.com.br"
            ),
            Car(
                id = 2,
                price = "R$ 200.000,00",
                battery = "200 kWh",
                potency = "150cv",
                recharge = "40 min",
                photoUrl = "www.google.com.br"
            )
        )
    }
}
