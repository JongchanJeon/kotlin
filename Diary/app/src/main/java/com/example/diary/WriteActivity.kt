package com.example.diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.diary.databinding.ActivityMainBinding
import com.example.diary.databinding.ActivityWriteBinding
import com.example.diary.model.MyDatabase
import com.example.diary.model.MyRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class WriteActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityWriteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener {
            val diary = binding.diarynote.text.toString()
            if(diary.isNotEmpty()) {
                val time:String = LocalDateTime.now().toString().substring(0, 10)

                CoroutineScope(Dispatchers.IO).launch{
                    val db = MyDatabase.getInstance(this@WriteActivity)
                    db?.myDao()?.insert(MyRecord(0, diary, time))
                }
            }
        }





    }
}