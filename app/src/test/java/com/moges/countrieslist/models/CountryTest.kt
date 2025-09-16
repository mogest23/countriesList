package com.moges.countrieslist.models

import org.junit.Test
import org.junit.Assert.*

class CountryTest {
    
    @Test
    fun `country should have all required fields`() {
        // Given
        val currency = Currency("USD", "US Dollar", "$")
        val language = Language("en", "English")
        
        // When
        val country = Country(
            capital = "Washington",
            code = "US",
            currency = currency,
            flag = "https://example.com/flag.svg",
            language = language,
            name = "United States",
            region = "North America"
        )
        
        // Then
        assertEquals("Washington", country.capital)
        assertEquals("US", country.code)
        assertEquals("United States", country.name)
        assertEquals("North America", country.region)
        assertEquals("USD", country.currency.code)
        assertEquals("en", country.language.code)
    }
    
    @Test
    fun `currency should have all required fields`() {
        // Given & When
        val currency = Currency("EUR", "Euro", "€")
        
        // Then
        assertEquals("EUR", currency.code)
        assertEquals("Euro", currency.name)
        assertEquals("€", currency.symbol)
    }
    
    @Test
    fun `language should have all required fields`() {
        // Given & When
        val language = Language("es", "Spanish")
        
        // Then
        assertEquals("es", language.code)
        assertEquals("Spanish", language.name)
    }
}
