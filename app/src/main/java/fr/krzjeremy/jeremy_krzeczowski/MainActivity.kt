package fr.krzjeremy.jeremy_krzeczowski

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.krzjeremy.jeremy_krzeczowski.databinding.ActivityMainBinding
import fr.krzjeremy.jeremy_krzeczowski.view.RecyclerViewActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // We can get the TextView directly from it's id and interact with it
        binding.firstActivityTextViewTitle.text = "Another text from activity"
        binding.firstActivityButton.setOnClickListener {
            generateIntentAndGoTo()
        }
    }
    private fun generateIntentAndGoTo() {
        val intent = Intent(this, RecyclerViewActivity::class.java)
        startActivity(intent)
    }
}