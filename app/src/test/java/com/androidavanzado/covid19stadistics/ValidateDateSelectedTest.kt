package com.androidavanzado.covid19stadistics

import com.androidavanzado.covid19stadistics.usecase.ValidateDateSelected
import org.junit.Assert
import org.junit.Test

class ValidateDateSelectedTest {

    @Test
    fun date_selected_is_older_that_today(){
        val date = "30-10-2021"
        Assert.assertFalse(ValidateDateSelected().invoke(date))
    }

    @Test
    fun date_selected_is_equals_that_today(){
        val date = "30-10-2021"
        Assert.assertFalse(ValidateDateSelected().invoke(date))
    }

    @Test
    fun date_selected_is_less_that_today(){
        val date = "30-10-2021"
        Assert.assertTrue(ValidateDateSelected().invoke(date))
    }
}