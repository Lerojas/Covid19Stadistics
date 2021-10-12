package com.androidavanzado.covid19stadistics

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.androidavanzado.covid19stadistics.ui.SplashActivity
import com.androidavanzado.covid19stadistics.util.DateTextFormat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before
import org.junit.Rule
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityRuleSplash: ActivityTestRule<SplashActivity> = ActivityTestRule(SplashActivity::class.java)
    private lateinit var dateYesteday : Date

    @Before
    fun initValidString(){
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DATE, -1)
        dateYesteday = calendar.time

    }

    @Test
    fun validateTextTitleTv(){

        //waiting time for the api to return a response
        Thread.sleep(8000)

        //declaration of test variables
        val title = DateTextFormat().invoke(dateYesteday)
        val confirmedCases = "Casos confirmados: "
        val deathCases = "Cantidad de personas fallecidas: "

        //validate textView when loading information
        onView(withId(R.id.titleTv)).check(matches(withText(title)))
        onView(withId(R.id.confirmedCasesTv)).check(matches(withSubstring(confirmedCases)))
        onView(withId(R.id.cantDeceasedTv)).check(matches(withSubstring(deathCases)))
    }
}

