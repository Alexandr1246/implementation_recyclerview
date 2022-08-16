package com.example.train.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.train.App
import com.example.train.Navigator

class ViewModelFactory(
    private val app: App
) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel =  when(modelClass){
            UsersListViewModel::class.java ->{
                UsersListViewModel(app.usersService)
            }
            UsersDetailsViewModel::class.java ->{
                UsersDetailsViewModel(app.usersService)
            }
            else->{
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() =ViewModelFactory(requireContext().applicationContext as App)
fun Fragment.navigator() = requireActivity() as Navigator