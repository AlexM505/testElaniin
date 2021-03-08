package com.alxd.testelaniin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alxd.testelaniin.model.region.MainGeneration
import com.alxd.testelaniin.model.region.Results
import com.alxd.testelaniin.repository.RegionsRepository

class RegionsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RegionsRepository(application)
    val showProgress : LiveData<Boolean>
    val regionList : MutableLiveData<List<Results>>
    val mainGeneration : MutableLiveData<MainGeneration>

    init {
        this.showProgress = repository.showProgress
        this.regionList = repository.regionList
        this.mainGeneration = repository.mainGeneration
    }

    fun getRegions(){
        repository.getRegions()
    }

    fun getInfoRegionById(id:String){
        repository.getInfoRegionById(id)
    }
}