package me.dio.android.eletriccarapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import me.dio.android.eletriccarapp.R
import me.dio.android.eletriccarapp.data.CarFactory
import me.dio.android.eletriccarapp.ui.adapter.CarAdapter

class CarsFragment : Fragment() {
    private lateinit var btnCalculatorRedirect : Button
    private lateinit var carsList: RecyclerView

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
        setupListeners(view)
        setupList()
    }

    private fun setupView(view: View) {
        view.let {
            btnCalculatorRedirect = it.findViewById(R.id.btn_calculator_redirect)
            carsList = it.findViewById(R.id.rv_cars_list)
        }
    }

    private fun setupListeners(view: View) {
        view.let {
            btnCalculatorRedirect.setOnClickListener {
                startActivity(Intent(it.context, AutonomyCalculatorActivity::class.java))
            }
        }
    }

    private fun setupList() {
        val data = CarFactory.list
        val adapter = CarAdapter(data)
        carsList.adapter = adapter
    }
}
