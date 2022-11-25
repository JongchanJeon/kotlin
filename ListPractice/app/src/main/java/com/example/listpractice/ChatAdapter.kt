package com.example.listpractice

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listpractice.util.ChatRoomInfo
import java.util.*

class ChatAdapter:RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    fun interface OnItemClickListener{
        fun onItemClick(v:View, position:Int)
    }
    private var listener:OnItemClickListener? = null
    private var data:MutableList<ChatRoomInfo> = mutableListOf()

    fun setListener(listener: OnItemClickListener){
        this.listener = listener
    }

    fun setData(data:MutableList<ChatRoomInfo>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun getItem(position: Int):ChatRoomInfo{
        return data[position]
    }

    fun swapItem(from:Int, to:Int) {
        Collections.swap(data, from, to)
        notifyItemMoved(from, to)
    }

    fun removeItem(index:Int) {
        data.removeAt(index)
        notifyItemRemoved(index)
    }
    class  ChatViewHolder(v: View, listener:OnItemClickListener?):RecyclerView.ViewHolder(v) {
        val textViewName: TextView = v.findViewById(R.id.textViewName)
        val textViewTime: TextView = v.findViewById(R.id.textViewTime)
        val imageView: ImageView = v.findViewById(R.id.imageView)
        init{
            v.setOnClickListener{
                Log.d(":List", "${this.layoutPosition} th item clicked");
                listener?.onItemClick(v, this.layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val v:View = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_list, parent, false)
        return ChatViewHolder(v,listener)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val item = data[position]
        holder.imageView.setImageResource(item.image)
        holder.textViewName.text = item.name
        holder.textViewTime.text = item.time
    }

    override fun getItemCount(): Int {
        return data.size
    }
}