package com.dineshdk.universities.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dineshdk.universities.MainActivity
import com.dineshdk.universities.databinding.RowItemBinding
import com.dineshdk.universities.models.University

class UniversityAdapter(val clickListener: ItemClickListener, var universityList: List<University>?)
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


    inner class UniversityViewHolder(val binding:RowItemBinding):ViewHolder(binding.root) {
        fun bindData(univ : University){
            binding.apply {
                name.text = univ?.name
                tvAddress.text = "${univ?.stateProvince ?: ""} ${univ?.country}"
                root.setOnClickListener{
                    clickListener.onItemClick(adapterPosition)
                }
            }
        }

    }
    public interface ItemClickListener{
        public fun onItemClick(position:Int)
    }
}