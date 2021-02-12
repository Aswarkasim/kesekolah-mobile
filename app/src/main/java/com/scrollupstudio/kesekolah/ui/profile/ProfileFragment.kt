package com.scrollupstudio.kesekolah.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import android.widget.TextView
import com.scrollupstudio.kesekolah.R
import com.scrollupstudio.kesekolah.data.database.PrefsManager
import com.scrollupstudio.kesekolah.data.model.profile.ProfileResponse
import com.scrollupstudio.kesekolah.ui.home.HomeActivity
import com.scrollupstudio.kesekolah.ui.login.LoginActivity
import com.scrollupstudio.kesekolah.ui.main.MainActivity
import com.scrollupstudio.kesekolah.ui.profile.edit.EditProfileActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.startActivity


class ProfileFragment : Fragment(), ProfileContract.View {

    lateinit var prefsManager: PrefsManager
    lateinit var presenter: ProfilePresenter

    lateinit var profileResponseData: ProfileResponse


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        prefsManager = PrefsManager(view.context)
        presenter = ProfilePresenter(this)
        initListener(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.getUser(prefsManager.prefsIdUser)
        setHasOptionsMenu(true)
    }



    override fun initFragment() {


    }

    override fun initListener(view: View) {
        val tvNamalengkap = view?.findViewById<TextView>(R.id.tv_nama_lengkap_akun)
       // tvNamalengkap.text = prefsManager.prefsNamalengkap
    }

    override fun onResult(profileResponse: ProfileResponse) {

        val  profile = profileResponse.user

        val tvNamalengkap = view?.findViewById<TextView>(R.id.tv_nama_lengkap_akun)
        val tvAlamat = view?.findViewById<TextView>(R.id.tv_alamat_profile)
        val tvPhone = view?.findViewById<TextView>(R.id.tv_phone_profile)
        val tvGender = view?.findViewById<TextView>(R.id.tv_gender_profile)
        val tvTitik = view?.findViewById<TextView>(R.id.tv_titik_profile)


        tvNamalengkap?.setText(profile[1].namalengkap)
        tvAlamat?.setText(profile[1].alamat)
        tvPhone?.setText(profile[1].nohp)
        tvGender?.setText(profile[1].gender)
        tvTitik?.setText(profile[1].latitude +" , "+profile[1].longtitude)

        //profileResponseData = profileResponse
    }

    override fun onResultLogout() {
        context?.startActivity<LoginActivity>()
       activity?.finishAffinity()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_edit -> {
//                context?.startActivity<EditProfileActivity>()
                this.startActivity(Intent(view?.context, EditProfileActivity::class.java))
                return true
            }
            R.id.action_logout -> {
                presenter.doLogout(prefsManager)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }



}