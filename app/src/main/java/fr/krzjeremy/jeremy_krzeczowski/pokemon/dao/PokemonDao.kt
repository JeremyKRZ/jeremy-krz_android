package fr.krzjeremy.jeremy_krzeczowski.pokemon.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.krzjeremy.jeremy_krzeczowski.pokemon.PokemonRoom

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon_object_table ORDER BY name ASC")
    fun selectAll(): LiveData<List<PokemonRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemon: PokemonRoom)

    @Query("DELETE FROM pokemon_object_table")
    fun deleteAll()
}