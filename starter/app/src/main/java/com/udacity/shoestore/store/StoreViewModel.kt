package com.udacity.shoestore.store

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class StoreViewModel : ViewModel() {

    private val shoeList = MutableLiveData<ArrayList<Shoe>>()

    init {
        createShoeList()
    }

    private fun createShoeList() {
        shoeList.value?.add(
            Shoe(
                "Example Shoe", 7.5, "Example Company", "A wearable shoe",
                listOf<String>("Img1", "Img2")
            )
        )
    }

}