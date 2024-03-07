package me.dio.android.eletriccarapp.ui

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        callService()
    }

    private fun setupView(view: View) {
        view.let {
            fabCalculatorRedirect = it.findViewById(R.id.fab_calculate)
            carsList = it.findViewById(R.id.rv_cars_list)
        }
    }

    private fun setupListeners() {
        fabCalculatorRedirect.setOnClickListener {
            startActivity(Intent(context, AutonomyCalculatorActivity::class.java))
        }
    }

    private fun setupList() {
        val adapter = CarAdapter(cars)
        carsList.adapter = adapter
    }

    private fun callService() {
        val baseUrl = "https://rodolfohok.github.io/dio.formacao-android.cars-api/cars.json"
        GetCarsTask().execute(baseUrl)
    }

    inner class GetCarsTask : AsyncTask<String, String, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            Log.d("GetCarInformationTask", "Iniciando...")
        }

        override fun doInBackground(vararg params: String?): String {
            var urlConnection : HttpURLConnection? = null
            try {
                val urlBase = URL(params[0])
                urlConnection = urlBase.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 60000
                val response = urlConnection.inputStream.bufferedReader().use {
                    it.readText()
                }
                publishProgress(response)
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
                setupList()
            } catch (ex: Exception) {
                Log.e("Error ->", ex.message.toString())
            }
        }
    }
}
