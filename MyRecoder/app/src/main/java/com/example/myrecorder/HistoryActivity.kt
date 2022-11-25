package com.example.myrecorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myrecorder.databinding.ActivityHistoryBinding
import com.example.myrecorder.model.MyDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityHistoryBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch{
            val dao = MyDatabase.getInstance(this@HistoryActivity).myDao()
        }
    }
}