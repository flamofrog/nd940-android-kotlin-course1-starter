package com.udacity.shoestore.store

import ShoeViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ActivityMainBinding.inflate
import com.udacity.shoestore.databinding.FragmentShoeItemBinding
import com.udacity.shoestore.databinding.FragmentStoreBinding
import com.udacity.shoestore.store.shoe.ShoeViewModel
import timber.log.Timber

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

        viewModel.shoeList.value?.forEach { shoe ->
            Timber.i("Shoe: $shoe")
            val shoeItemBinding: FragmentShoeItemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_item, container, false)
            val viewModelFactory = ShoeViewModelFactory(shoe)
            val shoeViewModel = viewModelFactory.create(ShoeViewModel::class.java) //ViewModelProvider(this, viewModelFactory).get(ShoeViewModel::class.java)

            shoeViewModel.eventViewDetails.observe(viewLifecycleOwner, Observer { viewDetails ->
                if (viewDetails) {
                    findNavController().navigate(StoreFragmentDirections.actionStoreFragmentToShoeDetailFragment())
                    shoeViewModel.onViewDetailsComplete()
                }
            })

            shoeItemBinding.shoeViewModel = shoeViewModel

            binding.StoreLinearLayout.addView(shoeItemBinding.root)
        }

        return binding.root
    }
}