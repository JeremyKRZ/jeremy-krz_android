package fr.krzjeremy.jeremy_krzeczowski.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fr.krzjeremy.jeremy_krzeczowski.architecture.CustomApplication
import fr.krzjeremy.jeremy_krzeczowski.model.*

class PhoneRepository {

    private val mPhoneDao =
        CustomApplication.instance.mApplicationDatabase.mPhoneDao()

    fun selectAllPhone(): LiveData<List<ObjectDataSample>> {
        return mPhoneDao.selectAll().map { list ->
            list.toObjectDataSample()
        }
    }

    fun insertPhone(objectDataSample: ObjectDataSample) {
        mPhoneDao.insert(objectDataSample.toRoomObject())
    }

    fun deleteAllPhone() {
        mPhoneDao.deleteAll()
    }

}

private fun ObjectDataSample.toRoomObject(): LocalDataSourceSample {
    return LocalDataSourceSample(
        name = phoneName,
        brand = brandName,
        image = phoneImage,
        category = categoryName
    )
}

private fun List<LocalDataSourceSample>.toObjectDataSample(): List<ObjectDataSample> {
    return map { eachItem ->
        ObjectDataSample(
            phoneName = eachItem.name,
            brandName = eachItem.brand,
            phoneImage = eachItem.image,
            categoryName = eachItem.category
        )
    }
}
