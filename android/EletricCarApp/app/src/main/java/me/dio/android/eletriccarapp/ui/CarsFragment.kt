package me.dio.android.eletriccarapp.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import me.dio.android.eletriccarapp.R
import me.dio.android.eletriccarapp.domain.Car
import me.dio.android.eletriccarapp.ui.adapter.CarAdapter
import org.json.JSONArray
import org.json.JSONTokener
import java.net.HttpURLConnection
import java.net.URL

class CarsFragment : Fragment() {
    private lateinit var fabCalculatorRedirect : FloatingActionButton
    private lateinit var carsList: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var cars : ArrayList<Car> = ArrayList();

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
        val checkInternet = checkForInternet(view.context)
        Log.d("Internet connection ->", checkInternet.toString())
        callService()
    }

    private fun setupView(view: View) {
        view.let {
            fabCalculatorRedirect = it.findViewById(R.id.fab_calculate)
            carsList = it.findViewById(R.id.rv_cars_list)
            progressBar = it.findViewById(R.id.pb_loader)
        }
    }

    private fun setupListeners() {
        fabCalculatorRedirect.setOnClickListener {
            startActivity(Intent(context, AutonomyCalculatorActivity::class.java))
        }
    }

    private fun setupList() {
        val carAdapter = CarAdapter(cars)
        carsList.let {
            it.visibility = View.VISIBLE
            it.adapter = carAdapter
        }
    }

    private fun callService() {
        val baseUrl = "https://rodolfohok.github.io/dio.formacao-android.cars-api/cars.json"
        GetCarsTask().execute(baseUrl)
    }

    private fun checkForInternet(context: Context) : Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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

    inner class GetCarsTask : AsyncTask<String, String, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            Log.d("GetCarsTask", "Iniciando...")
            progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: String?): String {
            var urlConnection : HttpURLConnection? = null
            try {
                val urlBase = URL(params[0])
                urlConnection = urlBase.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 60000
                urlConnection.setRequestProperty("Accept", "application/json")
                val responseCode = urlConnection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val response = urlConnection.inputStream.bufferedReader().use {
                        it.readText()
                    }
                    publishProgress(response)
                } else {
                    Log.e("Error ->", "Serviço indisponível no momento")
                }
            } catch (ex: Exception) {
                Log.e("Error ->", ex.message.toString())
            } finally {
                urlConnection?.disconnect()
            }
            return ""
        }

        override fun onProgressUpdate(vararg values: String?) {
            try {
                val jsonArray = JSONTokener(values[0]).nextValue() as JSONArray
                for (i in 0 until jsonArray.length()) {
                    val id = jsonArray.getJSONObject(i).getString("id")
                    val price = jsonArray.getJSONObject(i).getString("price")
                    val battery = jsonArray.getJSONObject(i).getString("battery")
                    val potency = jsonArray.getJSONObject(i).getString("potency")
                    val recharge = jsonArray.getJSONObject(i).getString("recharge")
                    val photoUrl = jsonArray.getJSONObject(i).getString("photoUrl")
                    val model = Car(
                        id = id.toInt(),
                        price = price,
                        battery = battery,
                        potency = potency,
                        recharge = recharge,
                        photoUrl = photoUrl
                    )
                    cars.add(model)
                }
                progressBar.visibility = View.GONE
                setupList()
            } catch (ex: Exception) {
                Log.e("Error ->", ex.message.toString())
            }
        }
    }
}
