package com.example.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import com.example.register.databinding.ActivityMainBinding
import kotlinx.coroutines.NonCancellable.start

class MainActivity : AppCompatActivity(){
    private lateinit var myCycle:MyCycle
    private val binding:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)


    }

    private fun updateWidgets(){
        var progress=0
        if(binding.editTextTextPersonName.text.isNotEmpty())
            progress++
        if(binding.editTextPhone.text.isNotEmpty())
            progress++
        // check radio - 선택된 버튼이 없을 때 -1
        if(binding.radioGroup.checkedRadioButtonId > -1)
            progress++
        if(binding.checkBoxEURA.isChecked)
            progress++

        binding.progressBar.progress = progress
        binding.buttonApply.isEnabled = progress==binding.progressBar.max
    }

    private val textWatcher:TextWatcher = object:TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            updateWidgets()
        }
    }

    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val result = it.resultCode
            Log.d("MainActivity", "Activity result code: $result")
        }

    private val onApply = View.OnClickListener{
        var course = when (binding.radioGroup.checkedRadioButtonId){
            R.id.radioButtonAdult -> "성인반"
            else -> "학생반"
        }
        val intent = Intent(this, CourseActivity::class.java).apply {
            putExtra("name",binding.editTextTextPersonName.text.toString())
            putExtra("course", course)
        }
        getResult.launch(intent)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.progressBar.max=4

        binding.editTextTextPersonName.addTextChangedListener(textWatcher)
        binding.editTextPhone.addTextChangedListener(textWatcher)

        binding.radioGroup.setOnCheckedChangeListener { _, _ ->  updateWidgets()}
        binding.checkBoxEURA.setOnClickListener { updateWidgets() }
        binding.buttonApply.setOnClickListener(onApply)

        myCycle =MyCycle()
        lifecycle.addObserver(myCycle)
        Log.i("MainActivity", "onCreate")

    }
    override fun onStart(){
        super.onStart()
        Log.i("MainActivity", "onStart")
        myCycle.Start()
    }

    override fun onResume(){
        super.onResume()
        Log.i("MainActivity", "onResume")
    }
    override fun onPause(){
        super.onPause()
        Log.i("MainActivity", "onPause")
    }
    override fun onStop(){
        super.onStop()
        Log.i("MainActivity", "onStop")
        myCycle.Stop()
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.i("MainActivity", "onDestroy")
    }

}
class MyCycle: DefaultLifecycleObserver{
    fun Start(){
        Log.i("MainActivity", "Mycycle-Started")
    }

    fun Stop(){
        Log.i("MainActivity", "Mycycle-Stopped")
    }

}