package fr.krzjeremy.jeremy_krzeczowski.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.krzjeremy.jeremy_krzeczowski.databinding.ActivityRecyclerViewBinding
import fr.krzjeremy.jeremy_krzeczowski.model.MyObjectForRecyclerView
import fr.krzjeremy.jeremy_krzeczowski.model.ObjectDataFooterSample
import fr.krzjeremy.jeremy_krzeczowski.model.ObjectDataHeaderSample
import fr.krzjeremy.jeremy_krzeczowski.model.ObjectDataSample

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var adapter: AndroidVersionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create the instance of adapter
        adapter = AndroidVersionAdapter{ item, view ->
            onItemClick(item,view)
        }

        // We define the type of linear layout
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // We set the adapter to recycler view
        binding.recyclerView.adapter = adapter


        // Generate data and give it to adapter
        adapter.submitList(generateFakeData())
    }

    private fun generateFakeData(): MutableList<MyObjectForRecyclerView> {
        val result = mutableListOf<MyObjectForRecyclerView>()
        mutableListOf(
            ObjectDataSample("Galaxy S10e", "Samsung", "S10"),
            ObjectDataSample("Galaxy S20", "Samsung", "S20"),
            ObjectDataSample("Galaxy S20 FE", "Samsung", "S20"),
            ObjectDataSample("Galaxy S20 FE 5G", "Samsung", "S20"),
            ObjectDataSample("Galaxy S20 Plus", "Samsung", "S20"),
            ObjectDataSample("Galaxy S20 Ultra", "Samsung", "S20"),
            ObjectDataSample("Galaxy S21", "Samsung", "S21"),
            ObjectDataSample("Galaxy S21 Plus", "Samsung", "S21"),
            ObjectDataSample("Galaxy S21 Ultra", "Samsung", "S21")
        ).groupBy {
           it.categoryName
        }.forEach { (cat, items) ->
            // For each mean for each list split
            // Here we have a map (key = isModulo) and each key have a list of it's items
            result.add(ObjectDataHeaderSample(cat))
            result.addAll(items)
            result.add(ObjectDataFooterSample("Nombre d'appareil : " + items.size))
            // Here we can add footer, just after our items
        }
        return result
    }
    private fun onItemClick(objectDataSample: ObjectDataSample, view : View) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Toast.makeText(this, objectDataSample.phoneName, Toast.LENGTH_SHORT).show()
    }
}
