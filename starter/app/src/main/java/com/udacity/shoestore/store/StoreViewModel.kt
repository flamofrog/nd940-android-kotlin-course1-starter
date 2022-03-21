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

    init {
        createShoeList()
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