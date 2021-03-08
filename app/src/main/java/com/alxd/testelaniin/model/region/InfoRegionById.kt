package com.alxd.testelaniin.model.region

data class InfoRegionById(
    val id: Int,
    val locations: List<Location>,
    val main_generation: MainGeneration,
    val name: String,
    val names: List<Name>,
    val pokedexes: List<Pokedexe>,
    val version_groups: List<VersionGroup>
)