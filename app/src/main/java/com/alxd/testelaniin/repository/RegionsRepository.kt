package com.alxd.testelaniin.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.alxd.testelaniin.model.generation.Generation
import com.alxd.testelaniin.model.generation.PokemonSpecy
import com.alxd.testelaniin.model.region.InfoRegionById
import com.alxd.testelaniin.model.region.MainGeneration
import com.alxd.testelaniin.model.region.Region
import com.alxd.testelaniin.model.region.Results
import com.alxd.testelaniin.network.BASE_URL
import com.alxd.testelaniin.network.PokeApiNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegionsRepository(val application: Application) {

    val showProgress = MutableLiveData<Boolean>()
    val regionList = MutableLiveData<List<Results>>()
    val mainGeneration = MutableLiveData<MainGeneration>()

    fun getRegions(){

        showProgress.postValue(true)
        //networkcall
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service =retrofit.create(PokeApiNetwork::class.java)

        service.getRegions().enqueue(object : Callback<Region>{
            override fun onResponse(call: Call<Region>, response: Response<Region>) {
//                showProgress.value = false
                showProgress.postValue(false)
                regionList.value = response.body()?.results
            }

            override fun onFailure(call: Call<Region>, t: Throwable) {
//                showProgress.value = false
                showProgress.postValue(false)
                Toast.makeText(application, "Error accediendo al API (REGIONS)", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun getInfoRegionById(id:String){

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service =retrofit.create(PokeApiNetwork::class.java)

        service.getInfoRegionById(id).enqueue(object : Callback<InfoRegionById>{
            override fun onResponse(
                call: Call<InfoRegionById>,
                response: Response<InfoRegionById>
            ) {
                mainGeneration.value = response.body()?.main_generation
            }

            override fun onFailure(call: Call<InfoRegionById>, t: Throwable) {
                Toast.makeText(application, "Error accediendo al API (REGIONS_BY_ID)", Toast.LENGTH_SHORT).show()
            }

        })
    }
}