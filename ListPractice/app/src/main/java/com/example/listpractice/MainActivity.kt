package com.example.listpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listpractice.databinding.ActivityMainBinding
import com.example.listpractice.util.DataGenerator

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val adapter = ChatAdapter()
    private val itemTouchCallback =
        object: ItemTouchHelper.SimpleCallback (
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,ItemTouchHelper.LEFT
    ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                adapter.swapItem(viewHolder.layoutPosition, target.layoutPosition)
                return true
            }

            override fun onSwiped(viewHolder:RecyclerView.ViewHolder, direction: Int){
                adapter.removeItem(viewHolder.layoutPosition)
            }


            }

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val manager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter
        adapter.setListener {v, position ->
            val data = adapter.getItem(position)
            val intent = Intent(this, RoomActivity::class.java)
            intent.putExtra("name", data.name)
            startActivity(intent)
        }
        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(binding.recyclerView)
        binding.buttonRandom.setOnClickListener {
            val data = DataGenerator.get()
            adapter.setData(data)
        }
    }
}