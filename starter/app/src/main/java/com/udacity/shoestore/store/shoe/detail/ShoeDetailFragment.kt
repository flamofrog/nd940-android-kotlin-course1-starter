package com.udacity.shoestore.store.shoe.detail

import ShoeViewModelFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.store.shoe.ShoeViewModel
import timber.log.Timber

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding

//    private lateinit var viewModelFactory: ShoeViewModelFactory
    private lateinit var viewModel: ShoeDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        viewModel = ViewModelProvider(this).get(ShoeDetailViewModel::class.java)
        binding.shoeDetailViewModel = viewModel

        viewModel.eventOnReturn.observe(viewLifecycleOwner, Observer { onReturn ->
            Timber.i("Observed!")
            if (onReturn) {
                Timber.i("Returning!")
                findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToStoreFragment())
                viewModel.onReturnComplete()
            }
        })

//        val shoeFragmentArgs by navArgs<ShoeFragmentArgs>()
//        viewModelFactory = ShoeViewModelFactory(shoeFragmentArgs.shoe)
//        viewModel = ViewModelProvider(this, viewModelFactory).get(ShoeViewModel::class.java)

        return binding.root
    }
}