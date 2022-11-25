package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val buttonSub = View.OnClickListener {
        try{
            val number1 = binding.editTextNumber2.text.toString().toDouble()
            val number2 = binding.editTextNumber3.text.toString().toDouble()
            binding.textViewResult.text=(number1 - number2).toString()
        }catch(e:NumberFormatException) {
        }
    }
    override fun onClick(v: View?) {
        try {
            val number1 = binding.editTextNumber2.text.toString().toDouble()
            val number2 = binding.editTextNumber3.text.toString().toDouble()
            binding.textViewResult.text=(number1 + number2).toString()
        }catch(e:NumberFormatException) {
            return
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener(this)
        binding.buttonSub.setOnClickListener(buttonSub)
        binding.buttonMul.setOnClickListener(View.OnClickListener {
            try {
                val number1 = binding.editTextNumber2.text.toString().toDouble()
                val number2 = binding.editTextNumber3.text.toString().toDouble()
                binding.textViewResult.text = (number1 * number2).toString()
            } catch (e: NumberFormatException) {

            }
        })
        binding.buttonDiv.setOnClickListener{
            try{
                val number1 = binding.editTextNumber2.text.toString().toDouble()
                val number2 = binding.editTextNumber3.text.toString().toDouble()
                if(number2 != 0.0 && number1 != 0.0)
                    binding.textViewResult.text=(number1 / number2).toString()
                else
                    binding.textViewResult.text = "0"
            }catch(e: NumberFormatException){

            }
        }
    }



}

