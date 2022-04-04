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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.store.StoreViewModel
import timber.log.Timber

class ShoeCreateFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private val storeViewModel: StoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        binding.lifecycleOwner = this
        binding.viewModel = storeViewModel

        storeViewModel.eventSaveShoe.observe(viewLifecycleOwner, Observer { onSave ->
            Timber.i("Observed On Save")
            if (onSave) {
                Timber.i("Saving!")
                try {
                    val shoe = Shoe(
                        binding.nameEdit.text.toString(),
                        binding.sizeEdit.text.toString().toDouble(),
                        binding.companyEdit.text.toString(),
                        binding.descriptionEdit.text.toString(),
                        mutableListOf<String>("")
                    )
                    storeViewModel.addShoe(shoe)
                    Toast.makeText(context, "Shoe saved successfully!.", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                } catch (e: Exception) {
                    Toast.makeText(context, "An error occurred whilst saving the shoe.", Toast.LENGTH_SHORT).show()
                }
                storeViewModel.onSaveComplete()
            }

        })

        storeViewModel.eventOnReturn.observe(viewLifecycleOwner, Observer { onReturn ->
            Timber.i("Observed On Return")
            if (onReturn) {
                Timber.i("Returning!")
                storeViewModel.onReturnComplete()
                findNavController().navigateUp()
            }
        })
        return binding.root
    }
}