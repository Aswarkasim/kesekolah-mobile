package com.scrollupstudio.kesekolah.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.scrollupstudio.kesekolah.R
import com.scrollupstudio.kesekolah.data.database.PrefsManager
import com.scrollupstudio.kesekolah.ui.home.HomeActivity
import com.scrollupstudio.kesekolah.ui.login.LoginActivity
import com.scrollupstudio.kesekolah.ui.main.MainActivity
import org.jetbrains.anko.startActivity

class SplashScreenActivity : AppCompatActivity() {

    private val pref by lazy { PrefsManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            if(pref.prefsIsLogin){
                when(pref.prefsRole){
                    "user" -> {
                        startActivity<HomeActivity>()
                    }
                    "driver" -> {
                        startActivity<MainActivity>()
                    }
                }
            }else{
                startActivity<LoginActivity>()
            }
            finish()
        }, 1200)
    }
}