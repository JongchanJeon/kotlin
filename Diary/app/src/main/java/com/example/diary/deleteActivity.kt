package com.example.diary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import com.example.diary.databinding.ActivityDeleteBinding
import com.example.diary.databinding.ActivityMainBinding
import com.example.diary.model.MyDatabase
import com.example.diary.model.MyRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class deleteActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityDeleteBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // 달력 객체 생성
        val calendarView: CalendarView = findViewById(R.id.calendarView)
        val dayText: TextView = findViewById(R.id.daytext)
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-d")

        val date: Date = Date(calendarView.date)
        dayText.text = dateFormat.format(date)
        var time: String = dateFormat.format(date)
        calendarView.setOnDateChangeListener{calendarView, year, month, dayOfMonth ->
            var day: String = "${year}-${month + 1}-${dayOfMonth}"
            dayText.text = day
            time= day
        }
        binding.deletebutton.setOnClickListener {
            val deletetext = binding.daytext.text.toString()
            if (deletetext.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    val db = MyDatabase.getInstance(this@deleteActivity)
                    db?.myDao()?.delete(MyRecord(deletetext, time))
                }
            }
        }

    }
}