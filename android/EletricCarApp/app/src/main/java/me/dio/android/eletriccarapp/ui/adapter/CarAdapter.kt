package me.dio.android.eletriccarapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.dio.android.eletriccarapp.R
import me.dio.android.eletriccarapp.domain.Car

class CarAdapter(private val cars: List<Car>, private val isFavoritesScreen: Boolean = false) : RecyclerView.Adapter<CarViewHolder>() {
    var carItemListener : (Car) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return CarViewHolder(view)
    }

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.price.text = cars[position].price
        holder.battery.text = cars[position].battery
        holder.potency.text = cars[position].potency
        holder.recharge.text = cars[position].recharge
        if (isFavoritesScreen) {
            holder.favorite.setImageResource(R.drawable.ic_star_selected)
        }
        holder.favorite.setOnClickListener {
            val car = cars[position]
            carItemListener(car)
            setupFavorite(car, holder)
        }
    }

    private fun setupFavorite(car: Car, holder: CarViewHolder) {
        car.isFavorite = !car.isFavorite
        if (car.isFavorite) {
            holder.favorite.setImageResource(R.drawable.ic_star_selected)
        } else {
            holder.favorite.setImageResource(R.drawable.ic_star)
        }
    }
}

class CarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var price: TextView
    var battery: TextView
    var potency: TextView
    var recharge: TextView
    var favorite: ImageView

    init {
        view.apply {
            price = findViewById(R.id.tv_price_value)
            battery = findViewById(R.id.tv_battery_value)
            potency = findViewById(R.id.tv_potency_value)
            recharge = findViewById(R.id.tv_recharge_value)
            favorite = findViewById(R.id.iv_favorite)
        }
    }
}
