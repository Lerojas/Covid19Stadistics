package com.androidavanzado.covid19stadistics


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import au.com.carsales.basemodule.context
import com.androidavanzado.covid19stadistics.ui.HomeActivity
import com.androidavanzado.covid19stadistics.ui.SplashActivity
import com.androidavanzado.covid19stadistics.ui.viewmodel.StadisticsViewModel
import com.androidavanzado.covid19stadistics.ui.viewmodel.ViewModelFactory
import com.androidavanzado.covid19stadistics.ui.viewmodel.getViewModel
import com.androidavanzado.covid19stadistics.util.DateTextFormat
import com.example.apicovidmodule.model.RepositoryApi
import com.example.apicovidmodule.storage.DataSourceApi
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.Matchers.*

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityRuleSplash: ActivityTestRule<SplashActivity> = ActivityTestRule(SplashActivity::class.java)
    var activityRuleHome: ActivityTestRule<HomeActivity> = ActivityTestRule(HomeActivity::class.java)
    private lateinit var dateYesteday : Date
    //lateinit var context: Context


    @Before
    fun initValidString(){

        //context = mock()

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DATE, -1)
        dateYesteday = calendar.time

    }

    @Test
    fun validateTextTitleTv(){
        Thread.sleep(8000)
        val title = DateTextFormat().invoke(dateYesteday)
        val confirmedCases = "Casos confirmados: 234.896.921"
        val deathCases = "Cantidad de personas fallecidas: 4.801.353"

        //validate textView when loading information
        onView(withId(R.id.titleTv)).check(matches(withText(title)))
        onView(withId(R.id.confirmedCasesTv)).check(matches(withText(confirmedCases)))
        onView(withId(R.id.cantDeceasedTv)).check(matches(withText(deathCases)))

        Thread.sleep(1000)
        //onView(withId(R.id.)).perform(PickerActions.setDate(2017, 6, 30));

    }
}

