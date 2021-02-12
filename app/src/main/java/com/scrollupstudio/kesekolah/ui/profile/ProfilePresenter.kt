package com.scrollupstudio.kesekolah.ui.profile

import android.util.Log
import com.scrollupstudio.kesekolah.data.database.PrefsManager
import com.scrollupstudio.kesekolah.data.model.profile.ProfileResponse
import com.scrollupstudio.kesekolah.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilePresenter(val view:  ProfileContract.View): ProfileContract.Presenter {

    init {
        view.initFragment()
    }

    override fun getUser(id_user: String) {
        ApiService.user.getProfile(id_user)
            .enqueue(object : Callback<ProfileResponse>{
                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    Log.d("Profile", "Gagal : $t")
                }

                override fun onResponse(
                    call: Call<ProfileResponse>,
                    response: Response<ProfileResponse>
                ) {
                    if(response.isSuccessful){
                        val responseProfile: ProfileResponse? = response.body()
                        Log.d("Profile", "Berhasil : $responseProfile")
                        view.onResult(responseProfile!!)
                    }else{
                        Log.d("Profile", "Tidak sukses")
                    }
                }

            })
    }

    override fun doLogout(prefsManager: PrefsManager) {
        prefsManager.logout()
        view.onResultLogout()
    }
}