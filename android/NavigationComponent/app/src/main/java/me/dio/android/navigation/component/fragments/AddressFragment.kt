package me.dio.android.navigation.component.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import me.dio.android.navigation.component.databinding.FragmentAddressBinding
import me.dio.android.navigation.component.extensions.text
import me.dio.android.navigation.component.models.PersonModel

class AddressFragment : Fragment() {
    private var _binding: FragmentAddressBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<AddressFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            val model = PersonModel(
                name = args.model.name,
                age = args.model.age,
                street = binding.tilStreet.text,
                number = binding.tilNumber.text.toInt()
            )
            Log.d("Model ->", model.toString())
            // TODO mandar os dados para outra fragment
            // TODO navegar entre os fragments
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
