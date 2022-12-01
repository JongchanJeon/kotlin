package com.example.mydiaryversion2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import com.example.mydiaryversion2.databinding.ActivityDeleteBinding
import com.example.mydiaryversion2.model.MyDatabase
import com.example.mydiaryversion2.model.MyRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DeleteActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityDeleteBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val calendarView: CalendarView = findViewById(R.id.calendarView)
        val whenistoDay: TextView = findViewById(R.id.whenistoDay)
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")

        val date:Date = Date(calendarView.date)
        whenistoDay.text = dateFormat.format(date)
        var date2: String = dateFormat.format(date)

        calendarView.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
            var day:String = "${year}-${month+1}-${dayOfMonth}"
            whenistoDay.text = day
            date2 = day
            CoroutineScope(Dispatchers.IO).launch {
                val db = MyDatabase.getInstance(this@DeleteActivity)
                var temp: String = db?.myDao().select(day)
                binding.diaryContent2.text = temp

            }
        }
        binding.deleteButton2.setOnClickListener {
            val datetext = binding.whenistoDay.text.toString()
            if(datetext.isNotEmpty()){
                CoroutineScope(Dispatchers.IO).launch {
                    val db = MyDatabase.getInstance(this@DeleteActivity)
                    db?.myDao().deleteByDate(date2)
                }
            }
        }
    }
}