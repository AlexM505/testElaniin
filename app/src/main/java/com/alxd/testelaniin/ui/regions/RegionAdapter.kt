package com.alxd.testelaniin.ui.regions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alxd.testelaniin.model.region.Results
import com.alxd.testelaniin.databinding.ItemRegionBinding
import com.alxd.testelaniin.utils.RegionDiffUtil

class RegionAdapter : RecyclerView.Adapter<RegionAdapter.MyViewHolder>() {

    var dataRegionList = emptyList<Results>()

    class MyViewHolder(private val binding: ItemRegionBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(region: Results){
            binding.region = region
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRegionBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataRegionList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return dataRegionList.size
    }

    fun setRegionData(regionData: List<Results>){
        val regionDiffUtil = RegionDiffUtil(dataRegionList, regionData)
        val regionDiffUtilResult = DiffUtil.calculateDiff(regionDiffUtil)
        this.dataRegionList = regionData
        regionDiffUtilResult.dispatchUpdatesTo(this)
    }

}