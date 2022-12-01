package com.example.mydiaryversion2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.TextView
import androidx.room.Update
import com.example.mydiaryversion2.databinding.ActivityWriteBinding
import com.example.mydiaryversion2.model.MyDatabase
import com.example.mydiaryversion2.model.MyRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class WriteActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityWriteBinding.inflate(layoutInflater)
    }
    //read를 같은 화면에서 구현할려고했으나 능력의 부족으로 새로운 인텐트로 새로운 액티비티로 보냈습니다 ㅜ
    private val onUpdateByCalendar = View.OnClickListener {
        val intent = Intent(this, UpdateByCalendarActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //날짜담기
        var today: TextView = findViewById(R.id.toDaysDate)
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        var a:CalendarView = findViewById(R.id.selectCalendar)

        var selectdate: Date = Date(a.date)
        today.text = dateFormat.format(selectdate)
        var selecteddate: String = dateFormat.format(selectdate)
        lateinit var selectedstory : String


        binding.selectCalendar.setOnDateChangeListener {view, year, month, dayOfMonth ->
            //형식지정(db와같게 ?)
            var day: String = "${year}-${month+1}-${dayOfMonth}"
            today.text = day
            selecteddate = day

            //val db = MyDatabase.getInstance(this@WriteActivity)
            //var story = db?.myDao().select(day)
            //var a = story[0].story.toString()
            //Log.d("하하하",a)
        }
        //등록버튼 온클릭리스너
        binding.registDiary.setOnClickListener {
            val story = binding.inputDiary.text.toString()
            if(story.isNotEmpty()){
                val date:String = selecteddate
                CoroutineScope(Dispatchers.IO).launch {
                    val db = MyDatabase.getInstance(this@WriteActivity)
                    db?.myDao().insert(MyRecord(date,story))
                }
            }
        }
        //삭제버튼 온클릭리스너
        //삭제,수정버튼을 같은 activity안에 집어 넣어 보려고 했으나 여러번 시도해도 삭제버튼이 먹지를 않아서
        //다른 액티비티로 분리하여서 구현함.
        //binding.removeDiary.setOnClickListener {
        //    val removetext = binding.toDaysDate.text.toString()
        //    val story = selectedstory
        //    if (removetext.isNotEmpty()) {
        //        CoroutineScope(Dispatchers.IO).launch {
        //            val db = MyDatabase.getInstance(this@WriteActivity)
        //            db?.myDao()?.delete(MyRecord(removetext, selecteddate))
        //        }
        //    }
        //}
        //홈으로 가는 리스너(명시적 인텐트사용)
        binding.backToHome.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        binding.readDiary2.setOnClickListener(onUpdateByCalendar)



    }


}

