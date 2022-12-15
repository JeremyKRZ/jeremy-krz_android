package fr.krzjeremy.jeremy_krzeczowski.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone_object_table")
data class LocalDataSourceSample(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "brand")
    val brand: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "image")
    val image: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
