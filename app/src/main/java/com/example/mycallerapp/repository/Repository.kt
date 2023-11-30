package com.example.mycallerapp.repository

import android.content.Context
import com.example.mycallerapp.models.CallLogItems
import com.example.mycallerapp.models.ContactItems
import com.example.mycallerapp.models.MessagesItems
import com.example.mycallerapp.resources.CallLogsResources
import com.example.mycallerapp.resources.ContactResources
import com.example.mycallerapp.resources.MessagesResources

class Repository(var context: Context) {

    var contect = ContactResources(context)
    var calllog = CallLogsResources(context)
    var messages = MessagesResources(context)
 suspend fun contectList () : ArrayList<ContactItems>{
     return contect.contectInfo()
 }

    suspend fun callLogs () : ArrayList<CallLogItems>{
        return  calllog.calLogs()
    }

    suspend fun messageList(): ArrayList<MessagesItems>{
        return messages.getMessage()
    }

}