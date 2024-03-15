package me.dio.android.lifecycle.viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import me.dio.android.lifecycle.R
import me.dio.android.lifecycle.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

//    private lateinit var viewModel : MainViewModel // option 1
    private val viewModel by activityViewModels<MainViewModel>() // option 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = ViewModelProvider(activity as AppCompatActivity).get(MainViewModel::class.java) // option 1

        viewModel.counter.observe(viewLifecycleOwner) { counter ->
            binding.counter.text = counter.toString()
        }

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.increment.setOnClickListener {
            viewModel.increment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
