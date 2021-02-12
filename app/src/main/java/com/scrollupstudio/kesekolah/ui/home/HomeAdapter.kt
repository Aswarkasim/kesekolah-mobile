package com.scrollupstudio.kesekolah.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.scrollupstudio.kesekolah.R
import com.scrollupstudio.kesekolah.data.model.anak.Anak
import kotlinx.android.synthetic.main.adapter_home.view.*

class HomeAdapter (val context: Context, var anak: ArrayList<Anak>,
                   val clickListener: (Anak, Int, String)->Unit)
    :RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_home, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return anak.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(anak[position])
        holder.view.switch_siap.setOnCheckedChangeListener  { _, isChecked ->
//            val message = if(isChecked) "Switch : On" else "Switch : Off"
//            Toast.makeText(holder.view.context, message, Toast.LENGTH_SHORT).show()
            if (isChecked) {
                clickListener(anak[position], position, "ready")
            }else{
                clickListener(anak[position], position, "unready")
            }
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val view = view
        fun bind(anak: Anak){
            view.tv_nama_anak_home.text = anak.nama_anak
            view.tv_nama_sekolah_home.text = anak.nama_sekolah

            if (anak.is_jemput == 0){
                view.tv_status_is_jemput_home.visibility = View.GONE
            }

            if(anak.is_ready == 1){
                view.switch_siap.isChecked = true
            }


        }
    }

    fun setData(data: List<Anak>){
        anak.clear()
        anak.addAll(data)
        notifyDataSetChanged()
    }

}

