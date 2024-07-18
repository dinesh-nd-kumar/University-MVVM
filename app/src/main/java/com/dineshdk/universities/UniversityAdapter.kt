package com.dineshdk.universities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dineshdk.universities.databinding.RowItemBinding

class UniversityAdapter(val context: Context, var universityList: List<University>?)
    : RecyclerView.Adapter<UniversityAdapter.UniversityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        return UniversityViewHolder(RowItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,false))
    }

    override fun getItemCount(): Int {

        if (universityList == null)
            return 0
            return universityList!!.size

    }


    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        universityList?.get(position)?.let { holder.bindData(it) }

    }


    inner class UniversityViewHolder(val binding:RowItemBinding):ViewHolder(binding.root),
        View.OnClickListener {
            var url:String? = null
        fun bindData(univ : University){
            binding.apply {
                name.text = univ?.name
                tvAddress.text = "${univ?.stateProvince} ${univ?.country}"
                url = univ.webPages[0]
                root.setOnClickListener(this@UniversityViewHolder)
                imageButton.setOnClickListener(this@UniversityViewHolder)
            }
        }

        override fun onClick(p0: View?) {
            if (!url?.startsWith("http://")!! && !url?.startsWith("https://")!!) {
                url = "http://$url"
            }
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
        }
    }
}