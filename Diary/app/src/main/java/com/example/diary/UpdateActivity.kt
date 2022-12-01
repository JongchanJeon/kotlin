package com.example.diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.diary.databinding.ActivityUpdateBinding
import com.example.diary.model.MyDatabase
import com.example.diary.model.MyRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class UpdateActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityUpdateBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var today: TextView = findViewById(R.id.textView3)

        val calendarView: CalendarView = findViewById(R.id.calendarView)
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-d")

        val date: Date = Date(calendarView.date)

        var time: String = dateFormat.format(date)
        binding.updatetext.text = "날짜를 입력 해 주세요."
        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var day: String = "${year}-${month + 1}-${dayOfMonth}"
            time = day

            CoroutineScope(Dispatchers.IO).launch {
                val db = MyDatabase.getInstance(this@UpdateActivity)
                var result: String? = db?.myDao()?.selectTime(day)
                //UI변경을 위한 withContext 없으면 sub Thread 에서 UI 변경이 되므로 앱이 꺼짐
                withContext(Dispatchers.Main) {
                    binding.updatetext.text = result
                }
            }
        }
        binding.updateBtn.setOnClickListener {
            val diary: String = binding.diarynote1.text.toString()
            if (diary.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    val db = MyDatabase.getInstance(this@UpdateActivity)
                    db?.myDao()?.update(MyRecord(diary, time))
                }
            }
        }
    }
}