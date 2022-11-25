package com.example.playgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.playgame.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() , View.OnClickListener{
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    val random = Random()
    companion object {
        var win = 0
        var draw = 0
        var lose = 0
    }

    private val rock = View.OnClickListener { /*바위는 0의 값*/
        try{
        val own = 0;
            val num = random.nextInt(3)
            var changeNum = ""
            if (num == 0) {
                changeNum = "바위"
            }else if (num == 1) {
                changeNum = "가위"
            }else {
                changeNum = "보"
            }

        if (own == num){
            binding.result.text= "무"
            draw++
            binding.record.text = "${win}승 ${draw}무 ${lose}패"
            binding.userchoice.text = "user : 바위 computer : ${changeNum}"

        }else if(num == 1) {
            binding.result.text = "승"
            win++
            binding.record.text = "${win}승 ${draw}무 ${lose}패"
            binding.userchoice.text = "user : 바위 computer : ${changeNum}"
        }else {
            binding.result.text= "패"
            lose++
            binding.record.text = "${win}승 ${draw}무 ${lose}패"
            binding.userchoice.text = "user : 바위 computer : ${changeNum}"
        }
        }catch(e:NumberFormatException) {
        }
    }
    private val scissors = View.OnClickListener { /* 가위는 1의 값*/
        try{
        val own = 1;
            val num = random.nextInt(3)
            var changeNum = ""
            if (num == 0) {
                changeNum = "바위"
            }else if (num == 1) {
                changeNum = "가위"
            }else {
                changeNum = "보"
            }
            if (own == num){
                binding.result.text= "무"
                draw++
                binding.record.text = "${win}승 ${draw}무 ${lose}패"
                binding.userchoice.text = "user : 가위 computer : ${changeNum}"

            }else if (num == 2) {
                binding.result.text = "승"
                win++
                binding.record.text = "${win}승 ${draw}무 ${lose}패"
                binding.userchoice.text = "user : 가위 computer : ${changeNum}"
            }else {
                binding.result.text = "패"
                lose++
                binding.record.text = "${win}승 ${draw}무 ${lose}패"
                binding.userchoice.text = "user : 가위 computer : ${changeNum}"
            }
        }catch(e:NumberFormatException) {
        }
    }
    private val paper = View.OnClickListener { /* 보는 1의 값*/
        try{
        val own = 2;
            val num = random.nextInt(3)
            var changeNum = ""
            if (num == 0) {
                changeNum = "바위"
            }else if (num == 1) {
                changeNum = "가위"
            }else {
                changeNum = "보"
            }

            if(num == 0) "바위"
            else if (num == 1) "가위"
            else "보"
            if (own == num){
                binding.result.text= "무"
                draw++
                binding.record.text = "${win}승 ${draw}무 ${lose}패"
                binding.userchoice.text = "user : 보 computer : ${changeNum}"
            }else if (num == 0) {
                binding.result.text = "승"
                win++
                binding.record.text = "${win}승 ${draw}무 ${lose}패"
                binding.userchoice.text = "user : 보 computer : ${changeNum}"
            }else {
                binding.result.text = "패"
                lose++
                binding.record.text = "${win}승 ${draw}무 ${lose}패"
                binding.userchoice.text = "user : 보 computer : ${changeNum}"
            }
        }catch(e:NumberFormatException) {
        }


    }
    private val reset = View.OnClickListener { /* 보는 1의 값*/
        try{

                binding.result.text= "-"
                win = 0
                draw = 0
                lose = 0
                binding.record.text = "${win}승 ${draw}무 ${lose}패"
            binding.userchoice.text = "user : ? computer : ?"
        }catch(e:NumberFormatException) {
        }


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rock.setOnClickListener(rock)
        binding.scissors.setOnClickListener(scissors)
        binding.paper.setOnClickListener(paper)
        binding.reset.setOnClickListener(reset)
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}