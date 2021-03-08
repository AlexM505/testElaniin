package com.alxd.testelaniin.utils

import android.content.Context
import com.alxd.testelaniin.model.team.Pokemon
import io.paperdb.Paper

object LocalStorage {

    val pokemon = "pokemonKey"

    fun initPaper(context:Context){
        Paper.init(context)
    }

    fun savePokemonList(pokemonList:List<Pokemon>){
        Paper.book().write(pokemon,pokemonList)
    }

    fun getPokemonList():List<Pokemon>{
        return Paper.book().read(pokemon, arrayListOf())
    }

    fun deletePokemonList(){
        Paper.book().delete(pokemon)
    }
}