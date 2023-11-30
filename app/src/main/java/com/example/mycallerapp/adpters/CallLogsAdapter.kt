package com.example.mycallerapp.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mycallerapp.R
import com.example.mycallerapp.databinding.CustomCallLogItmesBinding
import com.example.mycallerapp.interfaces.OnCallBtnClickListener
import com.example.mycallerapp.models.CallLogItems

class CallLogsAdapter(var list : ArrayList<CallLogItems>, var onCallBtnClickListener: OnCallBtnClickListener) : Adapter<CallLogsAdapter.MyViewHolder>()  {

    inner class MyViewHolder(var binding : CustomCallLogItmesBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = CustomCallLogItmesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var callDetails = list[position]
        var duration = ""
        var durationSecond = 0
         if (callDetails.Duration.toInt()<= 59){
             duration = "0:${callDetails.Duration}"
         }else
         {
            var durationminute = callDetails.Duration.toInt()/60
             durationSecond = callDetails.Duration.toInt()%60
             duration = "$durationminute:$durationSecond"
         }
        holder.binding.mobNo.text = callDetails.Number
        holder.binding.callDuration.text = "$duration"
//        holder.binding.callType.text = callDetails.callType
        holder.binding.tvDate.text = "${callDetails.Date}"
        holder.binding.btnCall.setOnClickListener {
            onCallBtnClickListener.onCallBtnClick(callDetails.Number)
        }
        var calltype = callDetails.callType

        when(calltype){
            1 ->{
              holder.binding.callType.setImageResource(R.drawable.baseline_incoming_24)
            }
            2 ->{
                holder.binding.callType.setImageResource(R.drawable.baseline_call_made_24)
            }
            3 -> {
                holder.binding.callType.setImageResource(R.drawable.baseline_call_missed_24)
            }
            5 ->{
                holder.binding.callType.setImageResource(R.drawable.baseline_call_end_24)
            }
            6 ->{
                holder.binding.callType.setImageResource(R.drawable.baseline_block_24)
            }
        }
    }
}