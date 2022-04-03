package com.udacity.shoestore.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class StoreViewModel : ViewModel() {



    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    var shoeIndexToView: Int = -1

    private val _eventViewDetails = MutableLiveData<Boolean>()
    val eventViewDetails: LiveData<Boolean>
        get() = _eventViewDetails

    private val _eventCreateShoe = MutableLiveData<Boolean>()
    val eventCreateShoe: LiveData<Boolean>
        get() = _eventCreateShoe

    init {
        _eventViewDetails.value = false
        createShoeList()
    }

    fun addShoe(shoe: Shoe) {
        _shoeList.value?.add(shoe)
    }

    fun updateShoe(shoe: Shoe, idx: Int) {
        _shoeList.value?.set(idx, shoe)
    }

    fun onViewDetails(deviceIndex: Int) {
        shoeIndexToView = deviceIndex
        _eventViewDetails.value = true
    }

    fun onViewDetailsComplete() {
        shoeIndexToView = -1
        _eventViewDetails.value = false
        _eventCreateShoe.value = false
    }

    fun getShoeAtIndex(index: Int): Shoe? {
        return _shoeList.value?.get(index)
    }

    fun onCreateShoe() {
        Timber.i("Create Shoe clicked!")
        _eventCreateShoe.value = true
    }

    fun onCreateShoeComplete() {
        _eventCreateShoe.value = false
    }

    private fun createShoeList() {
        _shoeList.value = mutableListOf<Shoe>(
            Shoe(
                "Example Shoe", 7.5, "Example Company", "A wearable shoe",
                listOf<String>("Img1", "Img2")
            ),
            Shoe(
                "Second Shoe", 6.0, "Another Company", "A wearable shoe",
                listOf<String>("Img1", "Img2")
            )
        )
    }

}