package com.alxd.testelaniin.model.team

import com.google.firebase.database.PropertyName

class Team(
    @PropertyName("teamName") val teamName:String = "",
    @PropertyName("region") val region:String = "",
    @PropertyName("listPokemon") val listPokemon:List<Pokemon> = arrayListOf()
) {
    constructor() : this("","", emptyList())
}

//{teamName=kanto, listPokemon=[{name=weedle}], region=kanto}
