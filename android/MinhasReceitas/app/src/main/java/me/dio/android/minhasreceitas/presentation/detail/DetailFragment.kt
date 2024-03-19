package me.dio.android.minhasreceitas.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import me.dio.android.minhasreceitas.R
import me.dio.android.minhasreceitas.databinding.FragmentDetailBinding
import me.dio.android.minhasreceitas.presentation.detail.adapter.ItemListAdapter
import me.dio.android.minhasreceitas.presentation.detail.adapter.ItemListViewModel
import me.dio.android.minhasreceitas.presentation.dialog.DialogEditTextFragment

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailFragmentArgs>()

    private val viewModel by viewModels<ItemListViewModel> {
        ItemListViewModel.Factory()
    }

    // Como utilizar o concatAdapter para trabalhar apenas com 1 Recycler View
    // Exemplo - #https://medium.com/androiddevelopers/merge-adapters-sequentially-with-mergeadapter-294d2942127a
    private val adapterIngredients by lazy { ItemListAdapter() }
    private val adapterPrepareMode by lazy { ItemListAdapter() }

    private var typeInsert = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observe()
        setupListeners()
    }

    private fun setupAdapter() {
        binding.rvIngredients.adapter = adapterIngredients
        binding.rvPrepareMode.adapter = adapterPrepareMode
    }

    private fun observe() {
        viewModel.getRecipeWithIngredientsAndPrepareMode(args.recipeId)
            .observe(viewLifecycleOwner) {
                when(it) {
                    ItemListState.Loading -> {
                        // TODO mostrar loading para o usuario
                    }
                    is ItemListState.Error -> {
                        // TODO Mostrar error parar o usuario
                    }
                    is ItemListState.Success -> {
                        adapterIngredients.submitList(it.ingredients)
                        adapterPrepareMode.submitList(it.prepareMode)
                    }
                }
            }
    }

    private fun setupListeners() {
        binding.btnAddIngredient.setOnClickListener {
            showDialogNewIngredient()
        }
        binding.btnAddPrepareMode.setOnClickListener {
            showDialogNewPrepareMode()
        }
        setFragmentResultListener(DialogEditTextFragment.FRAGMENT_RESULT) { requestKey, bundle ->
            val name = bundle.getString(DialogEditTextFragment.EDIT_TEXT_VALUE) ?: ""
            viewModel.insertIngredientsOrPrepareMode(
                typeInsert = typeInsert,
                name = name,
                recipeId = args.recipeId
            )
        }
    }

    private fun showDialogNewIngredient() {
        typeInsert = "INGREDIENT"
        DialogEditTextFragment.show(
            title = getString(R.string.label_new_ingredient),
            placeholder = getString(R.string.label_item_description),
            fragmentManager = parentFragmentManager
        )
    }

    private fun showDialogNewPrepareMode() {
        typeInsert = "PREPARE_MODE"
        DialogEditTextFragment.show(
            title = getString(R.string.label_new_prepare_mode),
            placeholder = getString(R.string.label_item_description),
            fragmentManager = parentFragmentManager
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
