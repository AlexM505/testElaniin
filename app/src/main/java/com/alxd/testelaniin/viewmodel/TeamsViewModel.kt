package com.alxd.testelaniin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alxd.testelaniin.model.team.Team
import com.alxd.testelaniin.repository.TeamsRepository

class TeamsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TeamsRepository(application)

    fun fetchTeamsData():LiveData<MutableList<Team>>{
        val mutableData = MutableLiveData<MutableList<Team>>()

        repository.getTeamsData().observeForever{
            mutableData.value = it
        }

        return mutableData
    }
}