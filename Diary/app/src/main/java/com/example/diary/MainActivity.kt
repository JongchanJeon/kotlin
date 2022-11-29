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

        val moveWriteBtn = findViewById<Button>(R.id.writeBtn)

        // 페이지 이동
        fun moveToAnotherPage(){
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }
        moveWriteBtn.setOnClickListener{
            moveToAnotherPage()
        }
        binding.listBtn.setOnClickListener{
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }
}