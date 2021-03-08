package com.alxd.testelaniin.model.generation

data class Generation(
    val abilities: List<Any>,
    val id: Int,
    val main_region: MainRegion,
    val moves: List<Move>,
    val name: String,
    val names: List<Name>,
    val pokemon_species: List<PokemonSpecy>,
    val types: List<Type>,
    val version_groups: List<VersionGroup>
)