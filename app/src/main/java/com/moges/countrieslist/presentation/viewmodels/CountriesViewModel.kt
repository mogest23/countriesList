package com.moges.countrieslist.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moges.countrieslist.data.repo.CountriesRepo
import com.moges.countrieslist.models.Country
import kotlinx.coroutines.launch

// Using sealed class to handle states will make it easier to add more states in the future
sealed class CountriesState {
    object Loading : CountriesState()
    data class Success(val countries: List<Country>) : CountriesState()
    data class Error(val message: String) : CountriesState()
}

class CountriesViewModel(
    private val countriesRepo: CountriesRepo,
) : ViewModel() {
    private val _countriesState = MutableLiveData<CountriesState>(CountriesState.Loading)
    val countriesState: LiveData<CountriesState> = _countriesState

    init {
        // Fetch countries on initialization
        getCountries()
    }


    fun getCountries() {
        _countriesState.value = CountriesState.Loading
        viewModelScope.launch {
            try {
                val countries = countriesRepo.getCountries()
                _countriesState.value = CountriesState.Success(countries)
            } catch (e: Exception) {
                // TODO: Possible update here can be to map error messages to proper user friendly messages for the user
                _countriesState.value = CountriesState.Error(e.message ?: "Unknown error")
            }
        }
    }
}