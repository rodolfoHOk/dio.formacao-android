package me.dio.android.minhasreceitas.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import me.dio.android.minhasreceitas.R
import me.dio.android.minhasreceitas.databinding.FragmentDetailBinding
import me.dio.android.minhasreceitas.presentation.detail.adapter.ItemListAdapter
import me.dio.android.minhasreceitas.presentation.dialog.DialogEditTextFragment

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailFragmentArgs>()

    private val viewModel by viewModels<ItemListViewModel> {
        ItemListViewModel.Factory(args.recipeId)
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
        viewModel.state
            .observe(viewLifecycleOwner) {
                when(it) {
                    ItemListState.Loading -> {
                        binding.pbLoading.isVisible = true
                    }
                    is ItemListState.Error -> {
                        binding.pbLoading.isVisible = false
                        Snackbar.make(
                            requireContext(),
                            binding.root,
                            it.message,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    is ItemListState.Success -> {
                        binding.pbLoading.isVisible = false
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
            val splitTypeInsert = typeInsert.split(",")
            when(splitTypeInsert[0]) {
                "INGREDIENT" -> {
                    viewModel.insertIngredientsOrPrepareMode(
                        typeInsert = typeInsert,
                        name = name,
                        recipeId = args.recipeId
                    )
                }
                "PREPARE_MODE" -> {
                    viewModel.insertIngredientsOrPrepareMode(
                        typeInsert = typeInsert,
                        name = name,
                        recipeId = args.recipeId
                    )
                }
                "UPDATE_INGREDIENT" -> {
                    viewModel.updateIngredient(
                        id = splitTypeInsert[1].toInt(),
                        name = name,
                        recipeId = args.recipeId
                    )
                }
                "UPDATE_PREPARE_MODE" -> {
                    viewModel.updatePrepareMode(
                        id = splitTypeInsert[1].toInt(),
                        name = name,
                        recipeId = args.recipeId
                    )
                }
                else -> {}
            }
        }
        adapterIngredients.edit = {
            showDialogUpdateIngredient(it.id)
        }
        adapterIngredients.remove = {
            viewModel.removeIngredient(
                id = it.id,
                name = it.name,
                recipeId = args.recipeId
            )
        }
        adapterPrepareMode.edit = {
            showDialogUpdatePrepareMode(it.id)
        }
        adapterPrepareMode.remove = {
            viewModel.removePrepareMode(
                id = it.id,
                name = it.name,
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

    private fun showDialogUpdateIngredient(ingredientId: Int) {
        typeInsert = "UPDATE_INGREDIENT,$ingredientId"
        DialogEditTextFragment.show(
            title = getString(R.string.label_update_ingredient),
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

    private fun showDialogUpdatePrepareMode(prepareModeId: Int) {
        typeInsert = "UPDATE_PREPARE_MODE,$prepareModeId"
        DialogEditTextFragment.show(
            title = getString(R.string.label_update_prepare_mode),
            placeholder = getString(R.string.label_item_description),
            fragmentManager = parentFragmentManager
        )
    }

    private fun <T> Flow<T>.observe(owner: LifecycleOwner, observe: (T) -> Unit) {
        owner.lifecycleScope.launch {
            owner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@observe.collect(observe)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
