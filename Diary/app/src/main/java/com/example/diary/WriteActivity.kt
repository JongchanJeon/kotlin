package com.example.diary

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.diary.databinding.ActivityMainBinding
import com.example.diary.databinding.ActivityWriteBinding
import com.example.diary.model.MyDatabase
import com.example.diary.model.MyRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class WriteActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityWriteBinding.inflate(layoutInflater)
    }
    companion object{
        var result:String = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        binding.saveBtn.setOnClickListener {
//            CoroutineScope(Dispatchers.IO).launch {
//                val dao = MyDatabase.getInstance(this@WriteActivity)?.myDao()
//
//                result = dao?.checkdate()!!.toString()
//
//            }
            if (result == "0"){
                val diary = binding.diarynote.text.toString()
                if(diary.isNotEmpty()) {
                    val time:String = LocalDateTime.now().toString().substring(0, 10)

                    CoroutineScope(Dispatchers.IO).launch{
                        val db = MyDatabase.getInstance(this@WriteActivity)
                        db?.myDao()?.insert(MyRecord(0, diary, time))
                    }
            } else {
                    val intent = Intent(this, WriteActivity::class.java)
                    val dlg: AlertDialog.Builder = AlertDialog.Builder(this@WriteActivity,  android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth)
                    dlg.setTitle("일기장 작성에러") //제목
                    dlg.setMessage("하루에 한개씩만 작성 가능합니다.") // 메시지
                    dlg.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                        startActivity(intent)
                        finish()
                    })
                    dlg.show()
                }

            }
        }





    }
}