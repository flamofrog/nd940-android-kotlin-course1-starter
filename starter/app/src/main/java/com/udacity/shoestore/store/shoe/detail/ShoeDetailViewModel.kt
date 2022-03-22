package com.udacity.shoestore.store.shoe.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ShoeDetailViewModel : ViewModel() {

    private val _eventOnReturn = MutableLiveData<Boolean>()
    val eventOnReturn: LiveData<Boolean>
        get() = _eventOnReturn

    fun onReturn() {
        Timber.i("OnReturn")
        _eventOnReturn.value = true
    }

    fun onReturnComplete() {
        Timber.i("OnReturnComplete")
        _eventOnReturn.value = false
    }

}