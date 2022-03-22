import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.store.shoe.ShoeViewModel

class ShoeViewModelFactory(private val shoe: Shoe) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoeViewModel::class.java)) {
            return ShoeViewModel(shoe) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}