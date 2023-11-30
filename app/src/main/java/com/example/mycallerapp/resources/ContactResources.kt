package com.example.mycallerapp.resources

import android.annotation.SuppressLint
import android.content.Context
import android.provider.ContactsContract
import com.example.mycallerapp.models.ContactItems

class ContactResources(private val context :Context) {

     var projection = listOf<String>(ContactsContract.CommonDataKinds.Phone.NUMBER,
                                          ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                                          ContactsContract.CommonDataKinds.Phone._ID,
                                          ContactsContract.CommonDataKinds.Phone.CONTACT_STATUS_ICON).toTypedArray()

    @SuppressLint("Range")
     suspend fun contectInfo(): ArrayList<ContactItems> {
        var contects = ArrayList<ContactItems>()


            val cr = context.contentResolver
            val cur = cr.query(

                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,  projection,
                null, null, null
            )
            if (cur!!.count > 0) {
                cur!!.moveToFirst()
                do {
                    val number = cur.getString(0)
                    val name = cur.getString(1)
                    contects.add(ContactItems(name, number))

                } while (cur.moveToNext())
            }


// Close the cursor when done



        return contects
    }
}