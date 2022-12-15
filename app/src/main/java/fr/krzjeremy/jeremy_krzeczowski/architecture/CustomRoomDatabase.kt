package fr.krzjeremy.jeremy_krzeczowski.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.krzjeremy.jeremy_krzeczowski.dao.PhoneDao
import fr.krzjeremy.jeremy_krzeczowski.model.LocalDataSourceSample
import fr.krzjeremy.jeremy_krzeczowski.pokemon.PokemonRoom
import fr.krzjeremy.jeremy_krzeczowski.pokemon.dao.PokemonDao

@Database(
    entities = [
        LocalDataSourceSample::class,
        PokemonRoom::class
    ],
    version = 4,
    exportSchema = false
)
abstract class CustomRoomDatabase : RoomDatabase() {
    abstract fun mPhoneDao() : PhoneDao
    abstract fun mPokemonDao() : PokemonDao
}
