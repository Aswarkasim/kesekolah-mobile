package com.scrollupstudio.kesekolah.ui.login

import com.scrollupstudio.kesekolah.data.database.PrefsManager
import com.scrollupstudio.kesekolah.data.model.login.DataLogin
import com.scrollupstudio.kesekolah.data.model.login.LoginResponse

interface LoginContract {

    interface Presenter{
        fun doLogin(username: String, password: String)
        fun setPrefs(prefsmanager: PrefsManager, dataLogin: DataLogin)
    }

    interface View{
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(loginResponse: LoginResponse)
        fun showMessage(message: String)
    }
}