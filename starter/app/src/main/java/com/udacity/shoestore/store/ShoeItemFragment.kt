package com.udacity.shoestore.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeItemBinding
import timber.log.Timber

class ShoeItemFragment : Fragment() {

    private lateinit var binding: FragmentShoeItemBinding
    private lateinit var viewModel: ShoeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_item, container, false
        )
        viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)
        binding.shoeViewModel = viewModel



        viewModel.eventViewDetails.observe(viewLifecycleOwner, Observer { viewDetails ->
            if (viewDetails) {
                findNavController().navigate(StoreFragmentDirections.actionStoreFragmentToShoeDetailFragment())
                viewModel.onViewDetailsComplete()
            }
        })

        return binding.root
    }


}