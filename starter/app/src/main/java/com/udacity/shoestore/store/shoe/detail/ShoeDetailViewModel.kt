package com.udacity.shoestore.store.shoe.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeDetailViewModel : ViewModel() {

    private val _eventOnReturn = MutableLiveData<Boolean>()
    val eventOnReturn: LiveData<Boolean>
        get() = _eventOnReturn

    private val _eventOnSave = MutableLiveData<Boolean>()
    val eventOnSave: LiveData<Boolean>
        get() = _eventOnSave

    private val _name =  MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _company =  MutableLiveData<String>()
    val company: LiveData<String>
        get() = _company

    private val _size =  MutableLiveData<Double>()
    val size: LiveData<Double>
        get() = _size

    private val _description =  MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description

    val sizeString = Transformations.map(size) {
        it.toString()
    }

    fun setupShoe(shoe: Shoe) {
        shoe.apply {
            _name.value = name
            _company.value = company
            _size.value = size
            _description.value = description
        }
    }

    fun onSave() {
        _eventOnSave.value = true
    }

    fun onSaveComplete() {
        _eventOnSave.value = false
    }

    fun onReturn() {
        Timber.i("OnReturn")
        _eventOnReturn.value = true
    }

    fun onReturnComplete() {
        Timber.i("OnReturnComplete")
        _eventOnReturn.value = false
    }

}