package com.udacity.shoestore.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeItemBinding
import com.udacity.shoestore.databinding.FragmentStoreBinding
import timber.log.Timber

class StoreFragment : Fragment() {

    private val viewModel: StoreViewModel by activityViewModels()
    private lateinit var binding: FragmentStoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_store, container, false
        )

        binding.storeViewModel = viewModel
        binding.lifecycleOwner = this


        // Inflate list of shoes
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            shoeList.forEachIndexed { idx, shoe ->
                Timber.i("Shoe: $shoe")
                val shoeItemBinding: FragmentShoeItemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_item, container, false)
                shoeItemBinding.storeViewModel = viewModel
                shoeItemBinding.index = idx

                binding.StoreLinearLayout.addView(shoeItemBinding.root)
            }
        })

        // Listen for on clicks for viewing details
        viewModel.eventViewDetails.observe(viewLifecycleOwner, Observer { viewDetails ->
            if (viewDetails) {
                val selectedShoe = viewModel.selectedShoe.value
                val index = viewModel.selectedShoeIndex
                selectedShoe?.let {
                    findNavController().navigate(StoreFragmentDirections.actionStoreFragmentToShoeDetailFragment(selectedShoe, index))
                }
                viewModel.onViewDetailsComplete()
            }
        })

        // Listen for on clicks for creating a new shoe
        viewModel.eventCreateShoe.observe(viewLifecycleOwner, Observer { createShoe ->
            if (createShoe) {
                findNavController().navigate(StoreFragmentDirections.actionStoreFragmentToShoeCreateFragment())
                viewModel.onCreateShoeComplete()
            }
        })
        return binding.root
    }
}