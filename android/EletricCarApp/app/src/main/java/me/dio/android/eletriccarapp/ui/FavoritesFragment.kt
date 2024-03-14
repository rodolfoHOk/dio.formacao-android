package me.dio.android.eletriccarapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import me.dio.android.eletriccarapp.data.local.CarRepository
import me.dio.android.eletriccarapp.databinding.CarsFragmentBinding
import me.dio.android.eletriccarapp.ui.adapter.CarAdapter

class FavoritesFragment : Fragment() {
    private var _binding: CarsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CarsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setupList()
    }

    private fun setupList() {
        val repository = CarRepository(requireContext())
        val carsList = repository.getAll()
        val carAdapter = CarAdapter(carsList, true)
        binding.rvCarsList.let {
            it.isVisible = true
            it.adapter = carAdapter
        }
        carAdapter.carItemListener = { car ->
            repository.deleteById(car.id)
            setupList()
        }
    }
}
