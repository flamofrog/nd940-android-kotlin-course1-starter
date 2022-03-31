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

    init {
        _eventViewDetails.value = false
        createShoeList()
    }

    fun updateShoe(idx: Int, shoe: Shoe) {
        _shoeList.value?.set(idx, shoe)
    }

    fun onViewDetails(deviceIndex: Int) {
        shoeIndexToView = deviceIndex
        _eventViewDetails.value = true
    }

    fun onViewDetailsComplete() {
        shoeIndexToView = -1
        _eventViewDetails.value = false
    }

    fun getShoeAtIndex(index: Int): Shoe? {
        return _shoeList.value?.get(index)
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