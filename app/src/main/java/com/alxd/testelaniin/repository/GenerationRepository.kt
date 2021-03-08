package com.alxd.testelaniin.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.alxd.testelaniin.model.generation.Generation
import com.alxd.testelaniin.model.generation.PokemonSpecy
import com.alxd.testelaniin.network.BASE_URL
import com.alxd.testelaniin.network.PokeApiNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GenerationRepository(val application: Application) {

    val pokemonSpecy = MutableLiveData<List<PokemonSpecy>>()

    fun getGenerationById(id:String){
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service =retrofit.create(PokeApiNetwork::class.java)

        service.getGenerationById(id).enqueue(object : Callback<Generation> {
            override fun onResponse(call: Call<Generation>, response: Response<Generation>) {
                pokemonSpecy.value = response.body()?.pokemon_species
            }

            override fun onFailure(call: Call<Generation>, t: Throwable) {
                Toast.makeText(application, "Error accediendo al API (REGIONS_BY_ID)", Toast.LENGTH_SHORT).show()
            }

        })
    }
}