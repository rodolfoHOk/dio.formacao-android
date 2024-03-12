package me.dio.android.android_material

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import me.dio.android.android_material.databinding.BottomSheetModalBinding

class ModalBottomSheet : BottomSheetDialogFragment() {
    private val binding by lazy {
        BottomSheetModalBinding.inflate(LayoutInflater.from(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    companion object {
        private const val TAG = "ModalBottomSheet"

        fun start(fragmentManager: FragmentManager) {
            ModalBottomSheet().show(fragmentManager, TAG)
        }
    }
}
