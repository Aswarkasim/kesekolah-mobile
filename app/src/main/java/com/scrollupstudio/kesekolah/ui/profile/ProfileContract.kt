package com.scrollupstudio.kesekolah.ui.profile

import android.view.View
import com.scrollupstudio.kesekolah.data.database.PrefsManager
import com.scrollupstudio.kesekolah.data.model.profile.ProfileResponse

interface ProfileContract {

    interface Presenter {
        fun getUser(id_user: String)
        fun doLogout(prefsManager: PrefsManager)
    }
    interface View{
        fun initFragment()
        fun initListener(view: android.view.View)
        fun onResult(profileResponse: ProfileResponse)
        fun onResultLogout()
    }
}