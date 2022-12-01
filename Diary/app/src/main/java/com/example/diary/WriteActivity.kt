package com.example.diary

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import androidx.appcompat.app.AlertDialog
import com.example.diary.databinding.ActivityMainBinding
import com.example.diary.databinding.ActivityWriteBinding
import com.example.diary.model.MyDatabase
import com.example.diary.model.MyRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class WriteActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityWriteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // 달력 객체 생성
        val calendarView: CalendarView = findViewById(R.id.calendarView)

        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-d")

        val date:Date = Date(calendarView.date)

        var time: String = dateFormat.format(date)
    calendarView.setOnDateChangeListener{calendarView, year, month, dayOfMonth ->
        var day: String = "${year}-${month + 1}-${dayOfMonth}"

        time = day
    }
        binding.saveBtn.setOnClickListener {
            val diary = binding.diarynote.text.toString()
            if (diary.isNotEmpty()) {

                CoroutineScope(Dispatchers.IO).launch {
                    val db = MyDatabase.getInstance(this@WriteActivity)
                    db?.myDao()?.insert(MyRecord(diary, time))
                }
            }
        }

    }
}

