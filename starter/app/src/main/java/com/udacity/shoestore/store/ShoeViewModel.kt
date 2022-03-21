package com.udacity.shoestore.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeViewModel : ViewModel() {

    private val _eventViewDetails = MutableLiveData<Boolean>()
    val eventViewDetails: LiveData<Boolean>
        get() = _eventViewDetails

    init {
        _eventViewDetails.value = false
    }

    fun onViewDetails() {
        _eventViewDetails.value = true
    }

    fun onViewDetailsComplete() {
        _eventViewDetails.value = false
    }

}