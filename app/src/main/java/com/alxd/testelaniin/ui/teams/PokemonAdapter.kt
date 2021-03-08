package com.alxd.testelaniin.ui.teams

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.alxd.testelaniin.R
import com.alxd.testelaniin.databinding.FragmentAddTeamsBinding
import com.alxd.testelaniin.model.generation.PokemonSpecy
import com.alxd.testelaniin.model.team.Pokemon
import com.alxd.testelaniin.utils.LocalStorage
import kotlinx.android.synthetic.main.item_pokemon.view.*
import kotlin.properties.Delegates

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.MyViewHolder>(){

    private var dataPokemonList = emptyList<PokemonSpecy>()
    lateinit var mDialog:Dialog
    lateinit var mBinding: FragmentAddTeamsBinding
    var mPos by Delegates.notNull<Int>()

    fun setPokemonList(
        pos: Int,
        pokemonList: List<PokemonSpecy>,
        dialog: Dialog,
        binding: FragmentAddTeamsBinding
    ){
        dataPokemonList = pokemonList
        mDialog = dialog
        mBinding = binding
        mPos = pos
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(pos:Int, pokemon:PokemonSpecy, dialog: Dialog, binding: FragmentAddTeamsBinding){
            itemView.tvPokemonName.text = pokemon.name

            itemView.cardViewPokemon.setOnClickListener {
                itemView.cardViewPokemon.setCardBackgroundColor(itemView.context.getColor(R.color.lightGray))

                var list = arrayListOf<Pokemon>()
                val data = Pokemon(pokemon.name)

                list.addAll(LocalStorage.getPokemonList())
                list.add(data)
                LocalStorage.savePokemonList(list)
                Toast.makeText(itemView.context, "Se ha seleccionado el pokemon ${pokemon.name}",Toast.LENGTH_SHORT).show()

                when(pos){
                    1->binding.tvNamePokemon1.text = pokemon.name
                    2->binding.tvNamePokemon2.text = pokemon.name
                    3->binding.tvNamePokemon3.text = pokemon.name
                    4->binding.tvNamePokemon4.text = pokemon.name
                    5->binding.tvNamePokemon5.text = pokemon.name
                    6->binding.tvNamePokemon6.text = pokemon.name
                }

                dialog.dismiss()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(mPos,dataPokemonList[position], mDialog, mBinding)
    }

    override fun getItemCount(): Int {
        return dataPokemonList.size
    }

}