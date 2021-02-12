package com.scrollupstudio.kesekolah.ui.home

import com.scrollupstudio.kesekolah.data.model.anak.AnakResponse
import com.scrollupstudio.kesekolah.data.model.siap.SiapResponse
import com.scrollupstudio.kesekolah.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter (val view: HomeContract.View): HomeContract.presenter{

    init {
        view.initFragment()
    }

    override fun getAnak(id_user: String) {
        view.onLoading(true)
        ApiService.user.getAnak(id_user)
            .enqueue(object : Callback<AnakResponse>{
                override fun onFailure(call: Call<AnakResponse>, t: Throwable) {
                    view.onLoading(false)
                }

                override fun onResponse(
                    call: Call<AnakResponse>,
                    response: Response<AnakResponse>
                ) {
                    view.onLoading(false)
                    if(response.isSuccessful){
                        val anakResponse: AnakResponse? = response.body()
                        view.onResultAnak(anakResponse!!)
                    }
                }

            })
    }

    override fun is_ready(id_anak: String, is_ready: Int) {

        ApiService.user.siap(id_anak, is_ready)
            .enqueue(object : Callback<SiapResponse>{
                override fun onFailure(call: Call<SiapResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<SiapResponse>,
                    response: Response<SiapResponse>
                ) {
                    if(response.isSuccessful){
                        val siapResponse: SiapResponse? = response.body()
                        view.onResultUpdateReady(siapResponse!!)
                        view.setMessage("id anak : $id_anak dan is_ready: $is_ready")
                    }
                }

            })
    }

}