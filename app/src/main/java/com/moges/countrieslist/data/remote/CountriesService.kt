package com.moges.countrieslist.data.remote

import com.moges.countrieslist.models.Country
import retrofit2.http.GET


interface CountriesService {
    @GET("countries.json")
    suspend fun getCountries(): List<Country>
    // We could also return Response<List<Country>>
    // but let's keep it simple for now
    // For a growing app, it would also make sense to use a DTO and Map to our Domain model
    // It would allow us to update Domain model without breaking the API request
}