package fr.krzjeremy.jeremy_krzeczowski.pokemon.architecture

import com.google.gson.GsonBuilder
import fr.krzjeremy.jeremy_krzeczowski.pokemon.remote.PokemonEndpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/pokemon/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    fun getPokemon(): PokemonEndpoint = retrofit.create(PokemonEndpoint::class.java)
}