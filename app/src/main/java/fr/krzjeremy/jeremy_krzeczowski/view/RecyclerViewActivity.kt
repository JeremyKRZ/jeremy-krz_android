package fr.krzjeremy.jeremy_krzeczowski.view

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
import fr.krzjeremy.jeremy_krzeczowski.model.MyObjectForRecyclerView
import fr.krzjeremy.jeremy_krzeczowski.model.ObjectDataFooterSample
import fr.krzjeremy.jeremy_krzeczowski.model.ObjectDataHeaderSample
import fr.krzjeremy.jeremy_krzeczowski.model.ObjectDataSample
import fr.krzjeremy.jeremy_krzeczowski.viewmodel.PhoneViewModel
import kotlin.random.Random

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var adapter: PhoneAdapter
    private lateinit var viewModel: PhoneViewModel

    private val phoneListObserver = Observer<List<MyObjectForRecyclerView>> {
        adapter.submitList(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PhoneViewModel::class.java]

        // Create the instance of adapter
        adapter = PhoneAdapter{ item, view ->
            onItemClick(item,view)
        }

        // We define the type of linear layout
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        // We set the adapter to recycler view
        binding.recyclerView.adapter = adapter

        binding.addItemButton.setOnClickListener{addRandomPhone()}
        binding.deleteAllItemButton.setOnClickListener { deletePhone() }
    }

    override fun onStart() {
        super.onStart()
        viewModel.phoneList.observe(this, phoneListObserver)
    }

    override fun onStop() {
        super.onStop()
        viewModel.phoneList.removeObserver(phoneListObserver)
    }

    private fun onItemClick(objectDataSample: ObjectDataSample, view : View) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Toast.makeText(this, objectDataSample.phoneName, Toast.LENGTH_SHORT).show()
    }

    private fun addRandomPhone() {
        val random = Random.nextInt(10, 22)
        viewModel.insertPhone("Samsung S$random", "Samsung", "S$random"
            , "https://www.bouyguestelecom.fr/media/catalog/product//s/a/samsung-galaxy-s22-ultra-bordeaux-face_2_1_1.png")
    }

    private fun deletePhone() {
        viewModel.deleteAllPhone()
    }


}
