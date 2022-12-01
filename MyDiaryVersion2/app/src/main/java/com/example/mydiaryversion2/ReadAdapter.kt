package com.example.mydiaryversion2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiaryversion2.model.MyDatabase
import com.example.mydiaryversion2.model.MyRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReadAdapter:RecyclerView.Adapter<ReadAdapter.ReadViewHolder>() {

    private var data = listOf<MyRecord>()

    class ReadViewHolder(v: View):RecyclerView.ViewHolder(v){

        val textViewDate: TextView = v.findViewById(R.id.textViewDate)
        val textViewContent: TextView = v.findViewById(R.id.textViewContent)

        //val modifyDiaryButton: Button = v.findViewById(R.id.modifyDiaryButton)
        //val deleteDiaryButton: Button = v.findViewById(R.id.deleteDiaryButton)
        //contents_read.xml 레이아웃에 삭제 버튼을 만들어 볼려고 했지만 도저히 방법이 나오지 않아서 포기함.
    }
    fun setData(data:List<MyRecord>){
        this.data = data
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadViewHolder {
        val v:View = LayoutInflater.from(parent.context).inflate(R.layout.contents_read,parent,false)
        return ReadViewHolder(v)

    }

    override fun onBindViewHolder(holder: ReadViewHolder, position: Int) {
        val item = data[position]
        holder.textViewDate.text = item.date
        holder.textViewContent.text = item.story
    }
    override fun getItemCount(): Int {
        return data.size
    }

}