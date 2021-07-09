package com.samad_talukder.androidfragmentcommunicationwithviewmodels.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
     val number = MutableLiveData<Int>()
}