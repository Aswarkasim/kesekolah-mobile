package com.scrollupstudio.kesekolah.ui.login

import android.util.Log
import com.scrollupstudio.kesekolah.data.database.PrefsManager
import com.scrollupstudio.kesekolah.data.model.login.DataLogin
import com.scrollupstudio.kesekolah.data.model.login.LoginResponse
import com.scrollupstudio.kesekolah.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter (val view: LoginContract.View): LoginContract.Presenter{

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun doLogin(username: String, password: String) {
        view.onLoading(true)
        ApiService.auth.login(username, password)
            .enqueue(object : Callback<LoginResponse>{
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    view.onLoading(false)
                    view.showMessage(t.message.toString())
                    Log.d("Adakah", "Gagal : ${t.message.toString()}")
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful){
                        val loginResponse: LoginResponse? = response.body()
                        view.showMessage(loginResponse!!.status)
                        if(loginResponse!!.error == false){
                            Log.d("Adakah", "anu : ${loginResponse!!.user}")
                            view.onResult(loginResponse)
                        }
                    }else{
                        view.showMessage("Username atau password salah")
                    }
                }

            })
    }

    override fun setPrefs(prefsmanager: PrefsManager, dataLogin: DataLogin) {
        prefsmanager.prefsIsLogin = true
        prefsmanager.prefsIdUser = dataLogin.id_user!!
        prefsmanager.prefsUsername = dataLogin.username!!
        prefsmanager.prefsNamalengkap = dataLogin.namalengkap!!
        prefsmanager.prefsRole = dataLogin.role!!
        prefsmanager.prefsIsActive = dataLogin.is_active!!
    }

}
