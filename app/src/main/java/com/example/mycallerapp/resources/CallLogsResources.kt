package com.example.mycallerapp.resources

import android.content.Context
import android.provider.CallLog
import com.example.mycallerapp.models.CallLogItems
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CallLogsResources(var context: Context) {

    var projection = listOf<String>(CallLog.Calls.NUMBER,
        CallLog.Calls.TYPE,
        CallLog.Calls.DURATION,
        CallLog.Calls._ID,
        CallLog.Calls.DATE,
        ).toTypedArray()

   suspend fun calLogs(): ArrayList<CallLogItems>{
        val sortOrder = "${CallLog.Calls.DATE} DESC"
        var cursor = context.contentResolver.query(CallLog.Calls.CONTENT_URI,projection,null,null,sortOrder)

        var callList = ArrayList<CallLogItems>()
       val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
        if (cursor!!.count>0){
            cursor.moveToFirst()
            do {
                val number = cursor.getString(0)
                val type  = cursor.getInt(1)
                val duration = cursor.getString(2)
                val date = cursor.getLong(4)
                val formattedDate = dateFormat.format(Date(date))

                callList.add(CallLogItems(number,duration,formattedDate,type))
            }while (cursor.moveToNext())

        }
        return callList
    }

}