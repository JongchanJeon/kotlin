package com.example.mydiaryversion2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydiaryversion2.model.MyDatabase
import com.example.mydiaryversion2.databinding.ActivityReadBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReadActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityReadBinding.inflate(layoutInflater)
    }

    private val adapter = ReadAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val manager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter
        CoroutineScope(Dispatchers.IO).launch {
            val dao = MyDatabase.getInstance(this@ReadActivity)?.myDao()
            val result = dao?.selectAll()!!
            withContext(Dispatchers.Main) {
                adapter.setData(result)
            }
        }
    }
}