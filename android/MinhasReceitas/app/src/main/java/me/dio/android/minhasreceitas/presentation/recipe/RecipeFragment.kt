package me.dio.android.minhasreceitas.presentation.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import me.dio.android.minhasreceitas.R
import me.dio.android.minhasreceitas.databinding.FragmentRecipeBinding
import me.dio.android.minhasreceitas.presentation.dialog.DialogEditTextFragment
import me.dio.android.minhasreceitas.presentation.recipe.adapter.RecipeAdapter

class RecipeFragment : Fragment() {
    private val viewModel: RecipesViewModel by viewModels {
        RecipesViewModel.Factory()
    }

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { RecipeAdapter() }

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
        observeStates()
    }

    private fun setupListeners() {
        binding.fabRecipe.setOnClickListener {
            showDialog()
        }
        setFragmentResultListener(DialogEditTextFragment.FRAGMENT_RESULT) { requestKey, bundle ->  
            val recipeName = bundle.getString(DialogEditTextFragment.EDIT_TEXT_VALUE) ?: ""
            viewModel.insert(recipeName)
        }
    }

    private fun setupAdapter() {
        binding.rvRecipes.adapter = adapter
    }

    private fun observeStates() {
        viewModel.state.observe(viewLifecycleOwner) {state ->
            when(state) {
                RecipeState.Loading -> {
                    binding.pbLoading.isVisible = true
                }
                RecipeState.Empty -> {
                    binding.pbLoading.isVisible = false
                    Snackbar.make(
                        requireContext(),
                        binding.root,
                        getString(R.string.label_empty_recipes),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                is RecipeState.Failed -> {
                    binding.pbLoading.isVisible = false
                    Snackbar.make(
                        requireContext(),
                        binding.root,
                        state.message,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                is RecipeState.Success -> {
                    binding.pbLoading.isVisible = false
                    adapter.submitList(state.recipes)
                }
            }
        }
    }

    private fun showDialog() {
        DialogEditTextFragment.show(
            title = getString(R.string.title_new_recipe),
            placeholder = getString(R.string.label_name_recipe),
            fragmentManager = parentFragmentManager
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
