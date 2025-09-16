package com.moges.countrieslist.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moges.countrieslist.data.repo.CountriesRepo

@Suppress("UNCHECKED_CAST")
class FactoryCountriesViewModel(
    private val countriesRepo: CountriesRepo,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountriesViewModel::class.java)) {
            return CountriesViewModel(countriesRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}