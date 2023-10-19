package cuttib.itmox.chinesezodiac.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import cuttib.itmox.chinesezodiac.R
import cuttib.itmox.chinesezodiac.databinding.ActivityMainBinding
import cuttib.itmox.chinesezodiac.presentation.main_fragments.AIAdviceFragment
import cuttib.itmox.chinesezodiac.presentation.main_fragments.HomeFragment
import cuttib.itmox.chinesezodiac.presentation.main_fragments.RewardsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val initialFragment = HomeFragment()
        replaceFragment(initialFragment)

        binding.bottomNavView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    val homeFragment = HomeFragment()
                    replaceFragment(homeFragment)
                    true
                }

                R.id.aiAdviceFragment -> {
                    val ideaFragment = AIAdviceFragment()
                    replaceFragment(ideaFragment)
                    true
                }

                R.id.rewardsFragment -> {
                    val rewardsFragment = RewardsFragment()
                    replaceFragment(rewardsFragment)
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentMainContainer.id, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }
}