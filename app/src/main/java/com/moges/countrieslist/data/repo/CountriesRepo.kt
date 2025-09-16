package com.moges.countrieslist.data.repo

import com.moges.countrieslist.data.remote.CountriesService
import com.moges.countrieslist.models.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountriesRepo(private val countriesService: CountriesService) {
    suspend fun getCountries(): List<Country> =
        withContext(Dispatchers.IO) { countriesService.getCountries() }
}