package com.moges.countrieslist

import org.junit.Test
import org.junit.Assert.*

/**
 * Unit tests for Countries List app
 * This is to demonstrate how we implement our test units.
 * For a more comprehensive test, this will be much more extensive.
 */
class CountriesAppTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `country model should work correctly`() {
        // Given
        val currency = com.moges.countrieslist.models.Currency("USD", "US Dollar", "$")
        val language = com.moges.countrieslist.models.Language("en", "English")

        // When
        val country = com.moges.countrieslist.models.Country(
            capital = "Washington",
            code = "US",
            currency = currency,
            flag = "https://example.com/flag.svg",
            language = language,
            name = "United States",
            region = "North America"
        )

        // Then
        assertEquals("US", country.code)
        assertEquals("United States", country.name)
        assertEquals("Washington", country.capital)
        assertEquals("USD", country.currency.code)
    }

    @Test
    fun `sealed class states should work`() {
        // Test that our sealed classes work as expected
        val loadingState = com.moges.countrieslist.presentation.viewmodels.CountriesState.Loading
        val successState =
            com.moges.countrieslist.presentation.viewmodels.CountriesState.Success(emptyList())
        val errorState =
            com.moges.countrieslist.presentation.viewmodels.CountriesState.Error("Test error")

        assertTrue(loadingState is com.moges.countrieslist.presentation.viewmodels.CountriesState.Loading)
        assertTrue(successState is com.moges.countrieslist.presentation.viewmodels.CountriesState.Success)
        assertTrue(errorState is com.moges.countrieslist.presentation.viewmodels.CountriesState.Error)
    }
}