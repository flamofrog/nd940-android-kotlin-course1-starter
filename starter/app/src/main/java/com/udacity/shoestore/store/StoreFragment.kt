package com.udacity.shoestore.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentStoreBinding

class StoreFragment : Fragment() {

    private lateinit var viewModel: StoreViewModel
    private lateinit var binding: FragmentStoreBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_store, container, false
        )

        viewModel = ViewModelProvider(this).get(StoreViewModel::class.java)
        binding.storeViewModel = viewModel
        binding.lifecycleOwner = this


        return binding.root
    }
}