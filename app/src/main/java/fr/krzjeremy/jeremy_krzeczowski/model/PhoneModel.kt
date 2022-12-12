package fr.krzjeremy.jeremy_krzeczowski.model

sealed class MyObjectForRecyclerView()

data class ObjectDataHeaderSample(
    val header: String
) : MyObjectForRecyclerView()

data class ObjectDataFooterSample(
    val footer: String
) : MyObjectForRecyclerView()

data class ObjectDataSample(
    val phoneName : String,
    val brandName : String,
    val categoryName: String
) : MyObjectForRecyclerView()
