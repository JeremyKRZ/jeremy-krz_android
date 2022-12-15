package fr.krzjeremy.jeremy_krzeczowski.pokemon.remote

import fr.krzjeremy.jeremy_krzeczowski.pokemon.PokemonRetrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonEndpoint {
    @GET("{id}")
    suspend fun getRandomPokemon(@Path("id") id : Int) : PokemonRetrofit

    @GET("?limit={limit}")
    suspend fun getAllPokemon(@Path("limit") limit: Int) : PokemonRetrofit
}