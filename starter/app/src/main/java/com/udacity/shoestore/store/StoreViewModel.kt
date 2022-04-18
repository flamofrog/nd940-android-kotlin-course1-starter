package com.udacity.shoestore.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class StoreViewModel : ViewModel() {

    private val _blankShoe = Shoe("", 0.0, "", "" ,listOf(""))

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    var selectedShoeIndex: Int = -1

    private val _selectedShoe = MutableLiveData<Shoe?>()
    val selectedShoe: LiveData<Shoe?>
        get() = _selectedShoe

    private val _eventOnReturn = MutableLiveData<Boolean>()
    val eventOnReturn: LiveData<Boolean>
        get() = _eventOnReturn

    private val _eventViewDetails = MutableLiveData<Boolean>()
    val eventViewDetails: LiveData<Boolean>
        get() = _eventViewDetails

    private val _eventCreateShoe = MutableLiveData<Boolean>()
    val eventCreateShoe: LiveData<Boolean>
        get() = _eventCreateShoe

    private val _eventSaveShoe = MutableLiveData<Boolean>()
    val eventSaveShoe: LiveData<Boolean>
        get() = _eventSaveShoe

    init {
        _eventViewDetails.value = false
        createShoeList()
    }

    fun addShoe(shoe: Shoe) {
        Timber.i("adding shoe: $shoe")
        _shoeList.value?.add(shoe)
    }

    fun updateShoe(idx: Int) {
        _selectedShoe.value?.let {
            _shoeList.value?.set(idx, it)
        }
    }

    fun onSave() {
        _eventSaveShoe.value = true
    }

    fun onSaveComplete() {
        _eventSaveShoe.value = false
    }

    fun onReturn() {
        _eventOnReturn.value = true
        _selectedShoe.value = _blankShoe
    }

    fun onReturnComplete() {
        _eventOnReturn.value = false
    }

    fun onViewDetails(deviceIndex: Int) {
        _shoeList.value?.get(deviceIndex)?.let {
            val shoe: Shoe = it
            _selectedShoe.value = shoe.copy()
        }
        selectedShoeIndex = deviceIndex
        _eventViewDetails.value = true
    }

    fun onViewDetailsComplete() {
        _eventViewDetails.value = false
        _eventCreateShoe.value = false
    }

    fun getShoeAtIndex(index: Int): Shoe? {
        return _shoeList.value?.get(index)
    }

    fun onCreateShoe() {
        _selectedShoe.value = _blankShoe
        _eventCreateShoe.value = true
    }

    fun onCreateShoeComplete() {
        _eventCreateShoe.value = false
    }

    fun anyFieldsBlank(): Boolean {
        selectedShoe.value?.let {
            if (it.name.isBlank()) {
                return true
            }
            if (it.company.isBlank()) {
                return true
            }
            if (it.size.isNaN() || it.size == 0.0) {
                return true
            }
            if (it.description.isBlank()) {
                return true
            }
        }
        return false
    }

    private fun createShoeList() {
        _shoeList.value = mutableListOf<Shoe>(
            Shoe(
                "Air Jordan 1", 7.5, "Nike", "A wearable shoe",
                listOf<String>("Img1", "Img2")
            ),
            Shoe(
                "Air Jordan 2", 5.5, "Nike", "A wearable shoe",
                listOf<String>("Img1", "Img2")
            ),
            Shoe(
                "Air Jordan 3", 7.5, "Nike", "A wearable shoe",
                listOf<String>("Img1", "Img2")
            ),
            Shoe(
                "Air Jordan 4", 9.5, "Nike", "A wearable shoe",
                listOf<String>("Img1", "Img2")
            ),
            Shoe(
                "Air Jordan 5", 7.5, "Nike", "A wearable shoe",
                listOf<String>("Img1", "Img2")
            ),
            Shoe(
                "Air Jordan 6", 10.5, "Nike", "A wearable shoe",
                listOf<String>("Img1", "Img2")
            ),
            Shoe(
                "Air Jordan 7", 5.5, "Nike", "A wearable shoe",
                listOf<String>("Img1", "Img2")
            ),
            Shoe(
                "Air Jordan 8", 8.5, "Nike", "A wearable shoe",
                listOf<String>("Img1", "Img2")
            ),
            Shoe(
                "Air Jordan 10", 7.5, "Nike", "A wearable shoe",
                listOf<String>("Img1", "Img2")
            ),
        )
    }

}