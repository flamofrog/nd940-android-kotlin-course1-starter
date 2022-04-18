package com.udacity.shoestore.store

import android.os.Bundle
import android.view.*
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

        binding.apply {
            storeViewModel = viewModel
            lifecycleOwner = this.lifecycleOwner

        }

        setHasOptionsMenu(true)

        // Inflate list of shoes
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            shoeList.forEachIndexed { idx, shoe ->
                Timber.i("Shoe: $shoe")
                val shoeItemBinding: FragmentShoeItemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_item, container, false)

                shoeItemBinding.apply {
                    storeViewModel = viewModel
                    index = idx
                }

                binding.StoreLinearLayout.addView(shoeItemBinding.root)
            }
        })

        // Listen for on clicks for viewing details
        viewModel.eventViewDetails.observe(viewLifecycleOwner, Observer { viewDetails ->
            if (viewDetails) {
                viewModel.onViewDetailsComplete()
                val selectedShoe = viewModel.selectedShoe.value
                val index = viewModel.selectedShoeIndex
                selectedShoe?.let {
                    findNavController().navigate(StoreFragmentDirections.actionStoreFragmentToShoeDetailFragment(selectedShoe, index))
                }
            }
        })

        // Listen for on clicks for creating a new shoe
        viewModel.eventCreateShoe.observe(viewLifecycleOwner, Observer { createShoe ->
            if (createShoe) {
                viewModel.onCreateShoeComplete()
                findNavController().navigate(StoreFragmentDirections.actionStoreFragmentToShoeCreateFragment())
            }
        })
        return binding.root
    }

    private fun onLogout() {
        findNavController().navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logoutMenuItem -> onLogout()
        }
        return super.onOptionsItemSelected(item)
    }
}