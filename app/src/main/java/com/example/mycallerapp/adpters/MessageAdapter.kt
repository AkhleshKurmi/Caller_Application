package com.example.mycallerapp.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.mycallerapp.databinding.CustomMessageItemViewBinding
import com.example.mycallerapp.interfaces.OnMessageClick
import com.example.mycallerapp.models.MessagesItems
import com.example.mycallerapp.utalities.RandomColors

class MessageAdapter(var list: ArrayList<MessagesItems>, var onMessageClick: OnMessageClick) : Adapter<MessageAdapter.MyViewHolder>() {

    inner class MyViewHolder(var binding : CustomMessageItemViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = CustomMessageItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var listItems  = list[position]
        holder.binding.tvAddress.text = listItems.Address
        holder.binding.messageImage.circleBackgroundColor = RandomColors().randomColors()
        var messagebody = ""
        var bodyLength = listItems.msgBody.length
        if (bodyLength<50){
            messagebody = listItems.msgBody
        }
        else{
            messagebody = listItems.msgBody.substring(0, minOf(50, bodyLength))
        }
        holder.binding.tvBody.text = messagebody

        holder.itemView.setOnClickListener {
            onMessageClick.onMessageClick(listItems)
        }
    }

}
