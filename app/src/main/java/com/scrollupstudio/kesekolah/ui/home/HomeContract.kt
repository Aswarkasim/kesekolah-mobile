package com.scrollupstudio.kesekolah.ui.home

import com.scrollupstudio.kesekolah.data.model.anak.Anak
import com.scrollupstudio.kesekolah.data.model.anak.AnakResponse
import com.scrollupstudio.kesekolah.data.model.siap.SiapResponse

interface HomeContract{

    interface presenter{
        fun getAnak(id_user: String)
        fun is_ready(id_anak: String, is_ready: Int)
    }

    interface View{
        fun initFragment()
        fun initListener(view: android.view.View)
        fun onLoading(loading: Boolean)
        fun onResultAnak(anakResponse: AnakResponse)
        fun onResultUpdateReady(siapResponse: SiapResponse)
        fun showDialogUpdateReady(anak: Anak, position: Int)
        fun setMessage(message: String)
    }
}