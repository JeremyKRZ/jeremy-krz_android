package fr.krzjeremy.jeremy_krzeczowski.viewmodel

import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import fr.krzjeremy.jeremy_krzeczowski.model.MyObjectForRecyclerView
import fr.krzjeremy.jeremy_krzeczowski.model.ObjectDataFooterSample
import fr.krzjeremy.jeremy_krzeczowski.model.ObjectDataHeaderSample
import fr.krzjeremy.jeremy_krzeczowski.model.ObjectDataSample
import fr.krzjeremy.jeremy_krzeczowski.repository.PhoneRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhoneViewModel: ViewModel() {
    private val phoneRepository: PhoneRepository by lazy { PhoneRepository() }
    val phoneList: LiveData<List<MyObjectForRecyclerView>> = phoneRepository.selectAllPhone().map { list ->
        list.toMyObjectForRecyclerView()
    }
    fun insertPhone(phoneName: String, brandName: String, categoryName: String, imageUrl: String) {
        viewModelScope.launch(Dispatchers.IO) {
            phoneRepository.insertPhone(
                ObjectDataSample(phoneName, brandName, categoryName, imageUrl)
            )
        }
    }
    fun deleteAllPhone() {
        viewModelScope.launch(Dispatchers.IO) {
            phoneRepository.deleteAllPhone()
        }
    }

    private fun List<ObjectDataSample>.toMyObjectForRecyclerView(): List<MyObjectForRecyclerView> {
        val result = mutableListOf<MyObjectForRecyclerView>()

        groupBy {
            it.categoryName
        }.forEach { (cat, items) ->
            result.add(ObjectDataHeaderSample(cat))
            result.addAll(items)
            result.add(ObjectDataFooterSample("Nombre d'appareil : " + items.size))
        }
        return result
    }

}