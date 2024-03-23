package me.dio.android.hilt.urlshortener.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.dio.android.hilt.urlshortener.core.Event
import me.dio.android.hilt.urlshortener.core.createDialog
import me.dio.android.hilt.urlshortener.core.createProgressDialog
import me.dio.android.hilt.urlshortener.databinding.FragmentUrlsBinding

class UrlsFragment : Fragment() {
    private var _binding: FragmentUrlsBinding? = null
    private val binding get() = _binding!!

    private val viewModel : UrlsViewModel by viewModels {
        ViewModelFactory()
    }

    private val urlsAdapter = UrlsAdapter()

    private var progressDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUrlsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvShortenedUrls.bind()
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
        viewModel.action.observe(viewLifecycleOwner, ::handleAction)
        binding.btnShortenUrl.setOnClickListener {
            viewModel.shorten(binding.etUrl.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun RecyclerView.bind() {
        val linearLayoutManager = layoutManager as LinearLayoutManager
        val divider = DividerItemDecoration(context, linearLayoutManager.orientation)
        addItemDecoration(divider)
        adapter = urlsAdapter
    }

    private fun handleState(urlsState: UrlsState) = binding.run {
        urlsAdapter.submitList(urlsState.urls)
        progress.isVisible = urlsState.isProgressVisible
        tvErrorMessage.text = urlsState.errorMessage
        tvErrorMessage.isVisible = urlsState.isErrorMessageVisible
    }

    private fun handleAction(event: Event<UrlsAction>) {
        val action = event.getContentIfNotHandled() ?: return
        when(action) {
            UrlsAction.Loading -> showLoading()
            UrlsAction.Done -> onUrlShortened()
            is UrlsAction.Failed -> showErrorDialog(action.errorMessage)
        }
    }

    private fun showLoading() {
        progressDialog = context?.createProgressDialog("Encurtando URL...")?.apply {
            show()
        }
    }

    private fun hideLoading() {
        progressDialog?.dismiss()
        progressDialog = null
    }

    private fun onUrlShortened() {
        binding.etUrl.setText("")
        hideLoading()
    }

    private fun showErrorDialog(errorMessage: String) = context?.run {
        hideLoading()
        createDialog {
            setTitle("Ops, ocorreu um falha!")
            setMessage(errorMessage)
        }.show()
    }
}
