package me.dio.android.urlshortener

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.dio.android.urlshortener.databinding.FragmentUrlsBinding

class UrlsFragment : Fragment() {

    private var _binding: FragmentUrlsBinding? = null
    private val binding get() = _binding!!

    private val urlsAdapter = UrlsAdapter()

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
        urlsAdapter.submitList(getUrls())
    }

    private fun getUrls() = listOf(
        ShortenedUrl("https://www.dio.me", "https://hideuri.com/yQJA11"),
        ShortenedUrl("https://www.dio.me", "https://hideuri.com/yQJA11"),
        ShortenedUrl("https://www.dio.me", "https://hideuri.com/yQJA11"),
        ShortenedUrl("https://www.dio.me", "https://hideuri.com/yQJA11"),
        ShortenedUrl("https://www.dio.me", "https://hideuri.com/yQJA11"),
        ShortenedUrl("https://www.dio.me", "https://hideuri.com/yQJA11"),
    )
}
