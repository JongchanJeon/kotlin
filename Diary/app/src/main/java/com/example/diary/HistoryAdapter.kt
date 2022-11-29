package com.example.diary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diary.model.MyRecord

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private var data = listOf<MyRecord>()

    fun updateData(data:List<MyRecord>){
        this.data = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.diary_history, parent, false)
        return HistoryViewHolder(v)
    }
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val record = data[position]
        holder.textViewTime.text = record.time
        holder.textViewDiary.text = record.diary
    }
    override fun getItemCount(): Int {
        return data.size
    }
    class HistoryViewHolder(v: View): RecyclerView.ViewHolder(v){
        val textViewTime: TextView = v.findViewById(R.id.textViewTime)
        val textViewDiary:TextView = v.findViewById(R.id.textViewDiary)
    }
}