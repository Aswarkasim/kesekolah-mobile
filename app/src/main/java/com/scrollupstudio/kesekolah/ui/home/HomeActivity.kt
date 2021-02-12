package com.scrollupstudio.kesekolah.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.scrollupstudio.kesekolah.R
import com.scrollupstudio.kesekolah.data.database.PrefsManager
import com.scrollupstudio.kesekolah.ui.login.LoginActivity
import com.scrollupstudio.kesekolah.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity() {

    lateinit var prefManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        prefManager = PrefsManager(this)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        btnNavHome.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.action_home -> {
                    openFragment(HomeFragment())
                 return@setOnNavigationItemSelectedListener  true
                }
                R.id.action_profile -> {
                    openFragment(ProfileFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
        btnNavHome.selectedItemId = R.id.action_home
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameHome, fragment)
            .addToBackStack(null)
            .commit()
    }


}