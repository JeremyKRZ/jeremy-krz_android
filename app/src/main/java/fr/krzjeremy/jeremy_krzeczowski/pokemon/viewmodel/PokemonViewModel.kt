package fr.krzjeremy.jeremy_krzeczowski.pokemon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import fr.krzjeremy.jeremy_krzeczowski.pokemon.model.*
import fr.krzjeremy.jeremy_krzeczowski.pokemon.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    private val pokemonRepository: PokemonRepository by lazy { PokemonRepository() }
    val pokemonList: LiveData<List<PokemonItemGeneric>> =
        pokemonRepository.selectAllPokemon().map { list ->
            list.toMyPokemonDomainToUi()
        }

    fun insertPokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.fetchData()
        }
    }

    fun deleteAllPokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.deleteAllPokemon()
        }
    }

    private fun List<PokemonDomain>.toMyPokemonDomainToUi(): List<PokemonItemGeneric> {
        val result = mutableListOf<PokemonItemGeneric>()
        this.fromDomainToUI()
            .groupBy {
                it.pokemonWeight > 500
            }.forEach { (isBig, items) ->
                val size = if (isBig) "Gros" else "Petit"
                result.add(PokemonItemHeader(size))
                result.addAll(items)
                result.add(PokemonItemFooter("Nombre de " + size.lowercase() + " pokemon : " + items.size))
            }
        return result
    }
}

fun List<PokemonDomain>.fromDomainToUI(): List<PokemonItem> {
    return map { pokemonDomain ->
        PokemonItem(
            pokemonNumber = pokemonDomain.pokedexNumber,
            pokemonName = pokemonDomain.name,
            pokemonWeight = pokemonDomain.weight,
            pokemonImage = pokemonDomain.imageURL
        )
    }
}