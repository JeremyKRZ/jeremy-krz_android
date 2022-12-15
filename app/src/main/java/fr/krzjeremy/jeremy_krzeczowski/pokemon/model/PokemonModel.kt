package fr.krzjeremy.jeremy_krzeczowski.pokemon.model

sealed class PokemonItemGeneric()

data class PokemonItemHeader(
    val header: String
) : PokemonItemGeneric()

data class PokemonItemFooter(
    val footer: String
) : PokemonItemGeneric()

data class PokemonItem(
    val pokemonName : String,
    val pokemonNumber : Int,
    val pokemonWeight: Int,
    val pokemonImage: String
) : PokemonItemGeneric()