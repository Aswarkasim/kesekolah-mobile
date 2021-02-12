package com.scrollupstudio.kesekolah.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.scrollupstudio.kesekolah.R
import com.scrollupstudio.kesekolah.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        btnNavMain.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.action_home_driver -> {
                    openFragment(MainFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_aman -> {
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_profile -> {
                    openFragment(ProfileFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
        btnNavMain.selectedItemId = R.id.action_home_driver
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameMainDriver, fragment)
            .addToBackStack(null)
            .commit()
    }
}