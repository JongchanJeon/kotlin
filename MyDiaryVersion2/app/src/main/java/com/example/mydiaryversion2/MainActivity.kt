package com.example.mydiaryversion2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mydiaryversion2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)

    }
    private val onWrite = View.OnClickListener {
        val intent = Intent(this, WriteActivity::class.java)
        startActivity(intent)
    }
    private val onDelete = View.OnClickListener {
        val intent = Intent(this, DeleteActivity::class.java)
        startActivity(intent)
    }

    private val onRead = View.OnClickListener {
        val intent = Intent(this , ReadActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener(onWrite)
        binding.deleteButton.setOnClickListener(onDelete)
        binding.readDiary.setOnClickListener(onRead)
    }

}