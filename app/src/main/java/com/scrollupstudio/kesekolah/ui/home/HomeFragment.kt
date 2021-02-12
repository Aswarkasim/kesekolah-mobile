package com.scrollupstudio.kesekolah.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scrollupstudio.kesekolah.R
import com.scrollupstudio.kesekolah.data.database.PrefsManager
import com.scrollupstudio.kesekolah.data.model.anak.Anak
import com.scrollupstudio.kesekolah.data.model.anak.AnakResponse
import com.scrollupstudio.kesekolah.data.model.siap.SiapResponse
import kotlinx.android.synthetic.main.adapter_home.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), HomeContract.View {

    private lateinit var prefManager: PrefsManager
    private lateinit var presenter: HomePresenter
    private lateinit var adapterHome: HomeAdapter
    lateinit var dataAnak: Anak


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        prefManager = PrefsManager(view.context)
        presenter = HomePresenter(this)
        initListener(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title = "Ke sekolah"
        presenter.getAnak(prefManager.prefsIdUser)
    }

    override fun initFragment() {
        adapterHome = HomeAdapter(requireContext(), arrayListOf()){
            anak: Anak, position: Int, type: String ->
            dataAnak = anak


            when(type){
                "ready" -> presenter.is_ready(dataAnak.id_anak, 1)
                "unready" -> presenter.is_ready(dataAnak.id_anak, 0)
//                "ready" -> setMessage(dataAnak.id_anak)
            }
        }
    }

    override fun initListener(view: View) {
        val rvAnak = view.findViewById<RecyclerView>(R.id.rv_home)

        rvAnak.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterHome
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading){
            true -> {
                pb_home.visibility = View.VISIBLE
                rv_home.visibility = View.GONE
            }
            false->{
                pb_home.visibility = View.GONE
                rv_home.visibility = View.VISIBLE
            }
        }
    }

    override fun onResultAnak(anakResponse: AnakResponse) {
        val dataAnak: List<Anak> = anakResponse.anak
        adapterHome.setData(dataAnak!!)
    }

    override fun onResultUpdateReady(siapResponse: SiapResponse) {
      //  setMessage("siap dijemput : ${siapResponse.message}")
    }

    override fun showDialogUpdateReady(anak: Anak, position: Int) {
        
    }

    override fun setMessage(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }


}