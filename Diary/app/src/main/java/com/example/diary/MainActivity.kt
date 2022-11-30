package com.example.diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.diary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.writeBtn.setOnClickListener{
            startActivity(Intent(this, WriteActivity::class.java))
        }

        binding.listBtn.setOnClickListener{
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        binding.deleteBtn.setOnClickListener{
            startActivity(Intent(this, deleteActivity::class.java))
        }
    }
}