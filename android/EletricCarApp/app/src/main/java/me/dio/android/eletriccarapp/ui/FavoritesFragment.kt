package me.dio.android.eletriccarapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import me.dio.android.eletriccarapp.R
import me.dio.android.eletriccarapp.data.local.CarRepository
import me.dio.android.eletriccarapp.ui.adapter.CarAdapter

class FavoritesFragment : Fragment() {
    private lateinit var favoritesCarsList : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorites_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    override fun onResume() {
        super.onResume()
        setupList()
    }

    private fun setupView(view: View) {
        view.let {
            favoritesCarsList = it.findViewById(R.id.rv_favorites_cars)
        }
    }

    private fun setupList() {
        val repository = CarRepository(requireContext())
        val carsList = repository.getAll()
        val carAdapter = CarAdapter(carsList, true)
        favoritesCarsList.let {
            it.isVisible = true
            it.adapter = carAdapter
        }
        carAdapter.carItemListener = { car ->
            repository.deleteById(car.id)
            setupList()
        }
    }
}
