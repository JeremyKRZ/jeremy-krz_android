package fr.krzjeremy.jeremy_krzeczowski.pokemon.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.krzjeremy.jeremy_krzeczowski.databinding.ActivityRecyclerViewBinding
import fr.krzjeremy.jeremy_krzeczowski.pokemon.model.PokemonItemGeneric
import fr.krzjeremy.jeremy_krzeczowski.pokemon.viewmodel.PokemonViewModel
import fr.krzjeremy.jeremy_krzeczowski.pokemon.model.PokemonItem

class PokemonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var adapter: PokemonAdapter
    private lateinit var viewModel: PokemonViewModel

    private val pokemonListObserver = Observer<List<PokemonItemGeneric>> {
        adapter.submitList(it)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PokemonViewModel::class.java]

        // Create the instance of adapter
        adapter = PokemonAdapter{ item, view ->
            onItemClick(item,view)
        }

        // We define the type of linear layout
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        // We set the adapter to recycler view
        binding.recyclerView.adapter = adapter

        binding.addItemButton.setOnClickListener{addRandomPokemon()}
        binding.deleteAllItemButton.setOnClickListener { deletePokemon() }
    }
    override fun onStart() {
        super.onStart()
        viewModel.pokemonList.observe(this, pokemonListObserver)
    }

    override fun onStop() {
        super.onStop()
        viewModel.pokemonList.removeObserver(pokemonListObserver)
    }

    private fun onItemClick(pokemonObjectDataSample: PokemonItem, view : View) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Toast.makeText(this, pokemonObjectDataSample.pokemonName, Toast.LENGTH_SHORT).show()
    }

    private fun addRandomPokemon() {
        viewModel.insertPokemon()
    }

    private fun deletePokemon() {
        viewModel.deleteAllPokemon()
    }

}