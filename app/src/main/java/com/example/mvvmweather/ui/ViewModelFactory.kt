package com.example.mvvmweather.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


@Suppress("UNCHECKED_CAST")
/**
 * Developed by Hasham.Tahir on 11/15/17.
 */

class ViewModelProviderFactory<T : Any>(private val viewModel: T) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModel.javaClass)) {
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}