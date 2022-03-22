package com.udacity.shoestore.store.shoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel(shoe: Shoe) : ViewModel() {

    private val _eventViewDetails = MutableLiveData<Boolean>()
    val eventViewDetails: LiveData<Boolean>
        get() = _eventViewDetails

    private val _company = MutableLiveData<String>()
    val company: LiveData<String>
        get() = _company

    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _size = MutableLiveData<Double>()
    val size: LiveData<Double>
        get() = _size

    init {
        _eventViewDetails.value = false
        Timber.i("Creating shoe vm: ${shoe.company}")
        _company.value = shoe.company
        _name.value = shoe.name
        _size.value = shoe.size
    }

    fun onViewDetails() {
        _eventViewDetails.value = true
    }

    fun onViewDetailsComplete() {
        _eventViewDetails.value = false
    }

}