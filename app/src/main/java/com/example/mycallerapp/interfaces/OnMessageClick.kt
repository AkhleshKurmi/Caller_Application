package com.example.mycallerapp.interfaces

import com.example.mycallerapp.models.MessagesItems

interface OnMessageClick {
    fun onMessageClick(messagesItems: MessagesItems)
}