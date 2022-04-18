package com.udacity.shoestore.store.shoe.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.store.StoreViewModel
import timber.log.Timber

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private val storeViewModel: StoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        val shoeFragmentArgs by navArgs<ShoeDetailFragmentArgs>()

        binding.lifecycleOwner = this
        binding.viewModel = storeViewModel

        storeViewModel.shoeList.observe(viewLifecycleOwner, Observer {
            Timber.d("shoelist was updated")
        })

        storeViewModel.eventSaveShoe.observe(viewLifecycleOwner, Observer { onSave ->
            Timber.i("Observed On Save")
            if (onSave) {
                storeViewModel.onSaveComplete()
                Timber.i("Saving!")
                if (storeViewModel.anyFieldsBlank()) {
                    Toast.makeText(context, "Some fields are blank or shoe size is 0.", Toast.LENGTH_SHORT).show()
                } else {
                    storeViewModel.updateShoe(shoeFragmentArgs.shoeIndex)
                    Toast.makeText(context, "Shoe saved successfully!.", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
            }
        })

        storeViewModel.eventOnReturn.observe(viewLifecycleOwner, Observer { onReturn ->
            Timber.i("Observed On Return")
            if (onReturn) {
                storeViewModel.onReturnComplete()
                Timber.i("Returning!")
                findNavController().navigateUp()
            }
        })
        return binding.root
    }
}