package me.dio.android.minhasreceitas.presentation.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.dio.android.minhasreceitas.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {
    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {  }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupAdapter()
    }

    private fun setupListeners() {
        binding.fabRecipe.setOnClickListener {
            // TODO show dialog
        }
    }

    private fun setupAdapter() {
        // TODO create adapter and use here
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
