package fr.krzjeremy.jeremy_krzeczowski.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.krzjeremy.jeremy_krzeczowski.model.LocalDataSourceSample

@Dao
interface PhoneDao {

    @Query("SELECT * FROM phone_object_table ORDER BY name ASC")
    fun selectAll(): LiveData<List<LocalDataSourceSample>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(phone: LocalDataSourceSample)


    @Query("DELETE FROM phone_object_table")
    fun deleteAll()
}
