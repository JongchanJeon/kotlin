package com.example.listpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.listpractice.databinding.ActivityRoomBinding

class RoomActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityRoomBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        intent.getStringExtra("name")?.let{
            Log.d("Room", it)
        }
    }
}