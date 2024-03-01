package me.dio.android.eletriccarapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.dio.android.eletriccarapp.ui.CarsFragment
import me.dio.android.eletriccarapp.ui.FavoritesFragment

class TabsAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CarsFragment()
            1 -> FavoritesFragment()
            else -> CarsFragment()
        }
    }
}
