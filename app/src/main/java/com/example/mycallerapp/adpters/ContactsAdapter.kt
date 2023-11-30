package com.example.mycallerapp.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mycallerapp.databinding.CustomContactViewBinding
import com.example.mycallerapp.interfaces.OnCallBtnClickListener
import com.example.mycallerapp.models.ContactItems
import com.example.mycallerapp.utalities.RandomColors

class ContactsAdapter(var list : ArrayList<ContactItems>,var onclick : OnCallBtnClickListener) :Adapter<ContactsAdapter.MyViewHolder>()  {

    inner class MyViewHolder(var binding : CustomContactViewBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       var binding = CustomContactViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
     return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return  list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       var listItems  = list[position]
        holder.binding.tvName.text = listItems.name
        holder.binding.tvMob.text = listItems.phone
        holder.binding.ivContact.circleBackgroundColor = RandomColors().randomColors()
        holder.binding.btnCall.setOnClickListener {
            onclick.onCallBtnClick(listItems.phone)
        }
    }
}