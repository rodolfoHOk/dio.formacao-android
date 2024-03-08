package me.dio.android.eletriccarapp.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import me.dio.android.eletriccarapp.R
import me.dio.android.eletriccarapp.data.CarsApi
import me.dio.android.eletriccarapp.data.local.CarRepository
import me.dio.android.eletriccarapp.domain.Car
import me.dio.android.eletriccarapp.ui.adapter.CarAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarsFragment : Fragment() {
    private lateinit var fabCalculatorRedirect : FloatingActionButton
    private lateinit var carsList : RecyclerView
    private lateinit var progressBar : ProgressBar
    private lateinit var noInternetImage : ImageView
    private lateinit var noInternetText : TextView
    private lateinit var carsApi : CarsApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cars_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupListeners()
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

    private fun setupView(view: View) {
        view.let {
            fabCalculatorRedirect = it.findViewById(R.id.fab_calculate)
            carsList = it.findViewById(R.id.rv_cars_list)
            progressBar = it.findViewById(R.id.pb_loader)
            noInternetImage = it.findViewById(R.id.iv_empty_state)
            noInternetText = it.findViewById(R.id.tv_no_internet)
        }
    }

    private fun setupListeners() {
        fabCalculatorRedirect.setOnClickListener {
            startActivity(Intent(context, AutonomyCalculatorActivity::class.java))
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
        progressBar.isVisible = true
        carsApi.getAllCars().enqueue(object : Callback<List<Car>> {
            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
                if (response.isSuccessful) {
                    progressBar.isVisible = false
                    noInternetImage.isVisible = false
                    noInternetText.isVisible = false
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
        progressBar.isVisible = false
        carsList.isVisible = false
        noInternetImage.isVisible = true
        noInternetText.isVisible = true
    }

    private fun setupList(cars: List<Car>) {
        val carAdapter = CarAdapter(cars)
        carsList.let {
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
