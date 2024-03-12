package me.dio.android.android_material

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import me.dio.android.android_material.databinding.ActivityNavigationBarBinding
import me.dio.android.android_material.fragments.FavoriteFragment
import me.dio.android.android_material.fragments.HomeFragment
import me.dio.android.android_material.fragments.PersonFragment

class NavigationBarActivity : AppCompatActivity() {
    private val binding by lazy { ActivityNavigationBarBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    openFragment(HomeFragment.newInstance())
                    true
                }
                R.id.favorite -> {
                    openFragment(FavoriteFragment.newInstance())
                    true
                }
                R.id.person -> {
                    openFragment(PersonFragment.newInstance())
                    true
                }
                else -> false
            }
        }
        
        binding.bottomNavigation.selectedItemId = R.id.home
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, NavigationBarActivity::class.java)
    }
}
