package me.dio.android.eletriccarapp.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import me.dio.android.eletriccarapp.R
import me.dio.android.eletriccarapp.data.CarsApi
import me.dio.android.eletriccarapp.data.local.CarRepository
import me.dio.android.eletriccarapp.databinding.CarsFragmentBinding
import me.dio.android.eletriccarapp.domain.Car
import me.dio.android.eletriccarapp.ui.adapter.CarAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarsFragment : Fragment() {
    private var _binding: CarsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var carsApi : CarsApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CarsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetrofit()
    }

    override fun onResume() {
        super.onResume()
        if (checkForInternet(context)) {
            getAllCars()
        } else {
            emptyState()
        }
    }

    private fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rodolfohok.github.io/dio.formacao-android.cars-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        carsApi = retrofit.create(CarsApi::class.java)
    }

    private fun getAllCars() {
        binding.pbLoader.isVisible = true
        carsApi.getAllCars().enqueue(object : Callback<List<Car>> {
            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
                if (response.isSuccessful) {
                    binding.pbLoader.isVisible = false
                    binding.ivEmptyState.isVisible = false
                    binding.tvNoInternet.isVisible = false
                    response.body()?.let {
                        setupList(it)
                    }
                } else {
                    Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun emptyState() {
        binding.pbLoader.isVisible = false
        binding.rvCarsList.isVisible = false
        binding.ivEmptyState.isVisible = true
        binding.tvNoInternet.isVisible = true
    }

    private fun setupList(cars: List<Car>) {
        val carAdapter = CarAdapter(cars)
        binding.rvCarsList.let {
            it.isVisible = true
            it.adapter = carAdapter
        }
        carAdapter.carItemListener = { car ->
            CarRepository(requireContext()).saveIfNotExist(car)
        }
    }

    private fun checkForInternet(context: Context?) : Boolean {
        val connectivityManager = context
            ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}
