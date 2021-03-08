package com.alxd.testelaniin.network

import com.alxd.testelaniin.model.generation.Generation
import com.alxd.testelaniin.model.region.InfoRegionById
import com.alxd.testelaniin.model.region.Region
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

const val BASE_URL = "https://pokeapi.co/api/v2/"

interface PokeApiNetwork {

    @GET("region/")
    fun getRegions() : Call<Region>

    @GET("region/{id}")
    fun getInfoRegionById(@Path("id") id:String) : Call<InfoRegionById>

    @GET("generation/{id}")
    fun getGenerationById(@Path("id") id:String) : Call<Generation>
}