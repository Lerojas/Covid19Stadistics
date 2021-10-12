package com.androidavanzado.covid19stadistics.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import au.com.carsales.basemodule.extension.initObserver
import com.androidavanzado.covid19stadistics.util.BaseUnitTest
import com.androidavanzado.covid19stadistics.MyApplication
import com.androidavanzado.covid19stadistics.ui.viewmodel.StadisticsViewModel
import com.androidavanzado.covid19stadistics.usecase.ValidateDateSelected
import com.androidavanzado.covid19stadistics.util.DateTomorrow
import com.androidavanzado.covid19stadistics.util.DateYesterday
import com.example.apicovidmodule.model.RepositoryApi
import com.example.apicovidmodule.model.StadisticsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

@ExperimentalCoroutinesApi
class StadisticsViewModelTest : BaseUnitTest() {

    private lateinit var stadisticsViewModel : StadisticsViewModel

    @Mock
    lateinit var repositoryApi: RepositoryApi

    @Mock
    lateinit var application: MyApplication

    @Mock
    lateinit var dataObserver: Observer<StadisticsApi>
    @Mock
    lateinit var onMessageErrorObserver : Observer<Int>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        stadisticsViewModel = StadisticsViewModel(repositoryApi, application).apply {
            _values.observeForever(dataObserver)
            onMessageError.observeForever(onMessageErrorObserver)
        }
    }

    @Test
    fun when_date_is_correct_and_api_return_success(){

        testCoroutineRule.runBlockingTest {

            val dateYesterday = DateYesterday().invoke()
            val result = StadisticsModelFactory().getStadisticsDataSuccess()
            val stadisticsApi = StadisticsModelFactory().getStadisticsObjet()

            Mockito.`when`(repositoryApi.getData(dateYesterday)).thenReturn(result)

            stadisticsViewModel.getData(dateYesterday)
            Thread.sleep(5000)
            Mockito.verify(dataObserver).onChanged(Mockito.refEq(stadisticsApi))
        }
    }

    @Test
    fun when_date_is_older_than_today(){

        testCoroutineRule.runBlockingTest {

            val dateGreater = DateTomorrow().dateGreaterThanToday()
            stadisticsViewModel.getData(dateGreater)
            Mockito.verify(onMessageErrorObserver).onChanged(3)
        }
    }

    @Test
    fun when_api_returns_null(){

        testCoroutineRule.runBlockingTest {

            val dateNoData = DateTomorrow().dateNoData()
            stadisticsViewModel.getData(dateNoData)
            Mockito.verify(dataObserver).onChanged(null)
        }
    }
}



