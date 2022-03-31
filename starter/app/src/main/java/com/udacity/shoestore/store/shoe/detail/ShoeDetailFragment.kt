package com.udacity.shoestore.store.shoe.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.store.StoreViewModel
import timber.log.Timber

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var viewModel: ShoeDetailViewModel
    private lateinit var storeViewModel: StoreViewModel

    private lateinit var shoe: Shoe

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        viewModel = ViewModelProvider(this).get(ShoeDetailViewModel::class.java)
        storeViewModel = ViewModelProvider(this).get(StoreViewModel::class.java)


        val shoeFragmentArgs by navArgs<ShoeDetailFragmentArgs>()
        shoe = shoeFragmentArgs.shoe

        viewModel.setupShoe(shoe)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        viewModel.eventOnSave.observe(viewLifecycleOwner, Observer { onSave ->
            Timber.i("Observed On Save")
            if (onSave) {
                Timber.i("Saving!")
                try {
                    shoe.name = binding.nameEdit.text.toString()
                    shoe.company = binding.companyEdit.text.toString()
                    shoe.size = binding.sizeEdit.text.toString().toDouble()
                    shoe.description = binding.descriptionEdit.text.toString()
                    storeViewModel.updateShoe(shoeFragmentArgs.shoeIndex, shoe)
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        "An error occurred whilst saving the shoe.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                viewModel.onSaveComplete()
            }

        })

        viewModel.eventOnReturn.observe(viewLifecycleOwner, Observer { onReturn ->
            Timber.i("Observed On Return")
//            if (!unsavedChanges()) {
            if (onReturn) {
                Timber.i("Returning!")
                viewModel.onReturnComplete()



                findNavController().navigateUp()
            }
//            } else {
//                Timber.i("there are unsaved changes") // TODO: Show dialog
//            }

        })
        return binding.root
    }

    private fun unsavedChanges(): Boolean {
        return when {
            viewModel.name.value != shoe.name -> {
                true
            }
            viewModel.company.value != shoe.company -> {
                true
            }
            viewModel.size.value != shoe.size -> {
                true
            }
            else -> viewModel.description.value != shoe.description
        }
    }
}