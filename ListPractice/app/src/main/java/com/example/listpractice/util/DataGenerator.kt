package com.example.listpractice.util

import com.example.listpractice.R
import java.util.*
import kotlin.random.Random

data class ChatRoomInfo(val image:Int, val name:String, val time:String)

class DataGenerator {
    companion object{
        @JvmField
        val images = arrayOf(
            R.drawable.ic_baseline_emoji_emotions, R.drawable.ic_baseline_emoji_people,
            R.drawable.ic_baseline_nordic_walking, R.drawable.ic_baseline_sick,
            R.drawable.ic_baseline_spatial_audio, R.drawable.ic_baseline_support_agent
        )

        @JvmField
        val charPool: List<Char> = ('a'.. 'z') + ('A'..'Z') + ('0'..'9')

        @JvmStatic
        fun get():MutableList<ChatRoomInfo>{
            val count = Random.nextInt(1, 100)
            val list = MutableList(count) {
                val imageIndex = Random.nextInt(images.size)
                val nameLength = Random.nextInt(3, 10)
                val randomString = (1..nameLength)
                    .map {charPool[Random.nextInt(0, charPool.size)]}
                    .joinToString ("")
                val randomDay = Random.nextInt(1, 32)
                ChatRoomInfo(images[imageIndex], randomString, "3월 ${randomDay}일")
            }
            return list
        }
    }
}