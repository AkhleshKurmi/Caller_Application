package com.example.mycallerapp.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycallerapp.viewmodel.CallerViewModel

class Factory(var context: Context) :ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

    if (modelClass.isAssignableFrom(CallerViewModel::class.java)) {
    return CallerViewModel(context) as T
   }
        throw IllegalArgumentException ("Illegal Class")

    }
}