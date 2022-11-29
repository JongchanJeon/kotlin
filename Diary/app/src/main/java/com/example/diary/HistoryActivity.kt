package com.example.diary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diary.databinding.ActivityHistoryBinding
import com.example.diary.databinding.ActivityMainBinding
import com.example.diary.model.MyDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityHistoryBinding.inflate(layoutInflater)
    }
    private val adapter = HistoryAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val manager = LinearLayoutManager(this)

        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            val dao = MyDatabase.getInstance(this@HistoryActivity)?.myDao()
            val result = dao?.selectAll()!!
            withContext(Dispatchers.Main) {
                adapter.updateData(result)
            }
        }
    }
}