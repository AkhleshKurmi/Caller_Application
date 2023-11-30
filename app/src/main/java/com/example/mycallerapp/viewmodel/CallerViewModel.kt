package com.example.mycallerapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycallerapp.models.CallLogItems
import com.example.mycallerapp.models.ContactItems
import com.example.mycallerapp.models.MessagesItems
import com.example.mycallerapp.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CallerViewModel(private val context: Context) : ViewModel() {

    private var repository = Repository(context)
    private var contactList : MutableLiveData<ArrayList<ContactItems>> = MutableLiveData<ArrayList<ContactItems>>()
    private var callLogList : MutableLiveData<ArrayList<CallLogItems>> = MutableLiveData<ArrayList<CallLogItems>>()
    private var messageList :MutableLiveData<ArrayList<MessagesItems>> = MutableLiveData<ArrayList<MessagesItems>>()
    fun showContects() : MutableLiveData<ArrayList<ContactItems>>{
        viewModelScope.launch(Dispatchers.IO) {
            contactList.postValue(repository.contectList())
        }
        return contactList
    }

    fun showCallLog() : MutableLiveData<ArrayList<CallLogItems>>{
        viewModelScope.launch(Dispatchers.IO) {
            callLogList.postValue(repository.callLogs())
        }
        return callLogList
    }

    fun showMessages() : MutableLiveData<ArrayList<MessagesItems>>{
        viewModelScope.launch(Dispatchers.IO) {
            messageList.postValue(repository.messageList())
        }
        return messageList
    }
}