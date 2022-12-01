package com.example.mydiaryversion2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.TextView
import com.example.mydiaryversion2.databinding.ActivityUpdateByCalendarBinding
import com.example.mydiaryversion2.model.MyDatabase
import com.example.mydiaryversion2.model.MyRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class UpdateByCalendarActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityUpdateByCalendarBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var today: TextView = findViewById(R.id.whenisToday2)
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-mm-dd")
        var a: CalendarView = findViewById(R.id.calendarView3)

        var selectdate: Date = Date(a.date)
        today.text = dateFormat.format(selectdate)
        var selecteddate: String = dateFormat.format(selectdate)
        lateinit var selectedstory : String

//        //binding.calendarView3.setOnClickListener {
//        //    val date: String = selecteddate
//        //    CoroutineScope(Dispatchers.IO).launch {
//        //        val db = MyDatabase.getInstance(this@UpdateByCalendarActivity)
//                var temp: String = db?.myDao().select(date)
//                binding.tempDiaryContents.text = temp
//            }
//        }


            binding.calendarView3.setOnDateChangeListener { view, year, month, dayOfMonth ->
                //형식지정(db와같게 ?)
                var day: String = "${year}-${month + 1}-${dayOfMonth}"
                today.text = day
                selecteddate = day
                CoroutineScope(Dispatchers.IO).launch {
                    val db = MyDatabase.getInstance(this@UpdateByCalendarActivity)
                    var temp: String = db?.myDao().select(day)
                    binding.tempDiaryContents.text = temp

                }
//                binding.editButton.setOnClickListener {
//                    val story = binding.editDiaryText.text.toString()
//                    if (story.isNotEmpty()) {
//                        val date: String = selecteddate
//                        CoroutineScope(Dispatchers.IO).launch {
//                            val db = MyDatabase.getInstance(this@UpdateByCalendarActivity)
//                            var temp: String = db?.myDao().select(date)
//
//                            binding.tempDiaryContents.text = temp
//
//                            db?.myDao().updateByDate(story, date)
//                        }
//                    }
//                }
                //List 자료형으로 가져올려다가 무슨문제인지 실행이안되어 폐기
                //val db = MyDatabase.getInstance(this@WriteActivity)
                //var story = db?.myDao().select(day)
                //var a = story[0].story.toString()
                //Log.d("하하하",a)

            }

    }
}