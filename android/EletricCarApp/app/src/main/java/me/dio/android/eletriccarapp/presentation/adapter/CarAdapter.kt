package me.dio.android.eletriccarapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.dio.android.eletriccarapp.R

class CarAdapter(private val cars: Array<String>) : RecyclerView.Adapter<CarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return CarViewHolder(view)
    }

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.textView.text = cars[position]
    }
}

class CarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var textView: TextView
    init {
        textView = view.findViewById(R.id.tv_price_value)
    }
}
