package fr.krzjeremy.jeremy_krzeczowski.pokemon.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fr.krzjeremy.jeremy_krzeczowski.architecture.CustomApplication
import fr.krzjeremy.jeremy_krzeczowski.pokemon.architecture.RetrofitBuilder
import fr.krzjeremy.jeremy_krzeczowski.pokemon.PokemonRoom
import fr.krzjeremy.jeremy_krzeczowski.pokemon.PokemonRetrofit
import fr.krzjeremy.jeremy_krzeczowski.pokemon.model.PokemonDomain
import kotlin.random.Random

class PokemonRepository {
    private val mPokemonDao = CustomApplication.instance.mApplicationDatabase.mPokemonDao()

    fun selectAllPokemon() : LiveData<List<PokemonDomain>> {
        return mPokemonDao.selectAll().map { list ->
            list.fromRoomToDomain()
        }
    }

    private fun insertPokemon(pokemonRoom: PokemonRoom) {
        mPokemonDao.insert(pokemonRoom)
    }

    suspend fun fetchData() {
        val randomGenerator = Random(System.currentTimeMillis())
        val random = randomGenerator.nextInt(1, 600)
        insertPokemon(RetrofitBuilder.getPokemon().getRandomPokemon(random).fromRetrofitToRoom())
    }

    fun deleteAllPokemon() {
        mPokemonDao.deleteAll()
    }
}

private fun PokemonRetrofit.fromRetrofitToRoom():PokemonRoom {
    return PokemonRoom(
        number= number,
        name= name,
        weight=weight,
        image=image
    )
}

private fun List<PokemonRoom>.fromRoomToDomain(): List<PokemonDomain> {
    return map { eachItem ->
        PokemonDomain(
            pokedexNumber = eachItem.number,
            name = eachItem.name,
            weight = eachItem.weight,
            imageURL = eachItem.image
        )
    }
}