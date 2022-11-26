package com.example.myrecorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myrecorder.databinding.ActivityMainBinding
import com.example.myrecorder.model.MyDatabase
import com.example.myrecorder.model.MyRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            val food = binding.editTextFoodName.text.toString()
            if(food.isNotEmpty()) {
                val time:String = LocalDateTime.now().toString()
                CoroutineScope(Dispatchers.IO).launch{
                    val db = MyDatabase.getInstance(this@MainActivity)
                    db?.myDao()?.insert(MyRecord(0, food, time))
                }
            }
        }

        binding.buttonHistory.setOnClickListener{
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }


}