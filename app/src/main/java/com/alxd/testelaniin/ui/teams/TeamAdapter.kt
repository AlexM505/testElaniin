package com.alxd.testelaniin.ui.teams

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alxd.testelaniin.R
import com.alxd.testelaniin.model.team.Team
import kotlinx.android.synthetic.main.item_team.view.*

class TeamAdapter(val context: Context) : RecyclerView.Adapter<TeamAdapter.MyViewHolder>(){

    private var dataList = mutableListOf<Team>()

    fun setListData(data:MutableList<Team>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_team, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        @SuppressLint("SetTextI18n")
        fun bindView(team: Team){
            itemView.tvNameTeamValue.text = team.teamName
            itemView.tvRegionTeamValue.text = team.region
            itemView.tvCantPokemonValue.text = "${team.listPokemon.size} Pokemon"
        }
    }
}