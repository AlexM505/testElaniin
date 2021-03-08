package com.alxd.testelaniin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.alxd.testelaniin.model.generation.PokemonSpecy
import com.alxd.testelaniin.repository.GenerationRepository

class GenerationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GenerationRepository(application)

    val pokemonSpecy : MutableLiveData<List<PokemonSpecy>>

    init {
        this.pokemonSpecy = repository.pokemonSpecy
    }

    fun getGenerationById(id:String){
        repository.getGenerationById(id)
    }
}