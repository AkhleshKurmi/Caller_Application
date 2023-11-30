package com.example.mycallerapp.resources

import android.content.Context
import android.net.Uri
import com.example.mycallerapp.models.MessagesItems
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MessagesResources(var context: Context) {

    val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())

    var projection  = arrayOf("_id", "address", "body", "date")

    suspend fun getMessage(): ArrayList<MessagesItems>{
        var messagesItems : ArrayList<MessagesItems> = ArrayList<MessagesItems>()
        val sortOrder = "date DESC"
        var cursor = context.contentResolver.query(Uri.parse("content://sms/inbox"),projection,null,null,sortOrder)

        if (cursor!!.count > 0){
            cursor.moveToFirst()
            do {
                var address = cursor.getString(1)
                var body = cursor.getString(2)
                var date = cursor.getLong(3)
                val formattedDate = dateFormat.format(Date(date))

                messagesItems.add(MessagesItems(address,body,formattedDate))
            }while (cursor.moveToNext())
        }

        return messagesItems
    }
}