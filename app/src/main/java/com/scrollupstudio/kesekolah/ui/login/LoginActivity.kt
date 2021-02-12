package com.scrollupstudio.kesekolah.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.scrollupstudio.kesekolah.R
import com.scrollupstudio.kesekolah.data.database.PrefsManager
import com.scrollupstudio.kesekolah.data.model.login.LoginResponse
import com.scrollupstudio.kesekolah.ui.home.HomeActivity
import com.scrollupstudio.kesekolah.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.email
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity(), LoginContract.View {

    lateinit var presenter: LoginPresenter
    lateinit var prefManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)
        prefManager = PrefsManager(this)
    }

    override fun initActivity() {

    }

    override fun initListener() {
        btn_login_login.setOnClickListener {
            val username = et_username_login.text.toString()
            val password = et_password_login.text.toString()

            if(username.isNullOrEmpty()){
                et_username_login.error = "Isi username anda"
                et_username_login.requestFocus()
            }else if(password.isNullOrEmpty()){
                et_password_login.error = "Isi password anda"
                et_password_login.requestFocus()
            }else{
                presenter.doLogin(username, password)
            }
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading){
            true -> {
                pb_login.visibility = View.VISIBLE
                btn_login_login.visibility = View.GONE
            }
            false -> {
                pb_login.visibility = View.GONE
                btn_login_login.visibility = View.VISIBLE
            }

        }
    }

    override fun onResult(loginResponse: LoginResponse) {
        presenter.setPrefs(prefManager, loginResponse.user!!)
//        startActivity<HomeActivity>()
        when (prefManager.prefsRole){
            "user" ->{
                startActivity<HomeActivity>()
                finishAffinity()
            }

            "driver" ->{
                startActivity<MainActivity>()
                finishAffinity()
            }

        }

    }




    override fun showMessage(message: String) {
        toast(message)
    }
}