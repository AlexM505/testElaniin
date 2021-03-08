package com.alxd.testelaniin.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alxd.testelaniin.model.team.Pokemon
import com.alxd.testelaniin.model.team.Team
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class TeamsRepository(val application: Application) {

    fun getTeamsData():LiveData<MutableList<Team>>{
        val mutableData = MutableLiveData<MutableList<Team>>()

        FirebaseFirestore.getInstance().collection("teams")
            .get().addOnSuccessListener { result ->

                val listData = mutableListOf<Team>()

                for (document in result){
                    val team = Team(document.getString("teamName")?:"",
                        document.getString("region")?:"",
                        document.get("listPokemon") as List<Pokemon>)

                    listData.add(team)
                }

                mutableData.value = listData
            }


        return mutableData
    }
}