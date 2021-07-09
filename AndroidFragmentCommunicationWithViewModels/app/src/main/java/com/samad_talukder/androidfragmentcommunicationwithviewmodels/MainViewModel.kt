package com.samad_talukder.androidfragmentcommunicationwithviewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
     val number = MutableLiveData<Int>()
}