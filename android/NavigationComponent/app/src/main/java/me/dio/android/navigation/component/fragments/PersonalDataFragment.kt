package me.dio.android.navigation.component.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.dio.android.navigation.component.databinding.FragmentPersonalDataBinding
import me.dio.android.navigation.component.extensions.text
import me.dio.android.navigation.component.models.PersonModel

class PersonalDataFragment : Fragment() {
    private var _binding: FragmentPersonalDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonalDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            val personModel = PersonModel(
                name = binding.tilName.text,
                age = binding.tilAge.text.toInt()
            )
            // TODO mandar os dados para outra fragment
            // TODO navegar entre os fragments
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
