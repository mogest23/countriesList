package com.moges.countrieslist.presentation.views

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

// I would use this class for extensive app testing if I had more time.
@RunWith(AndroidJUnit4::class)
class CountriesFragmentTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.moges.countrieslist", appContext.packageName)
    }
}
