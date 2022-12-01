package com.example.diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diary.databinding.ActivityDeleteBinding
import com.example.diary.databinding.ActivityUpdateBinding
import com.example.diary.model.MyDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class UpdateActivity : AppCompatActivity() {
    private val binding by lazy{
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

        binding.calendarView.setOnDateChangeListener{calendarView, year, month, dayOfMonth ->
            var day: String = "${year}-${month + 1}-${dayOfMonth}"
            time=day

            CoroutineScope(Dispatchers.IO).launch {
                val db = MyDatabase.getInstance(this@UpdateActivity)
                var result:String = db.myDao().selectTime(day)

                binding.updatetext.text = result
            }

        }






    }
}