package com.androidavanzado.covid19stadistics.ui

import androidx.lifecycle.Observer
import com.androidavanzado.covid19stadistics.util.BaseUnitTest
import com.androidavanzado.covid19stadistics.MyApplication
import com.androidavanzado.covid19stadistics.ui.viewmodel.StadisticsViewModel
import com.androidavanzado.covid19stadistics.util.StadisticsViewModelTestDateUtils
import com.androidavanzado.covid19stadistics.util.DateYesterday
import com.example.apicovidmodule.model.RepositoryApi
import com.example.apicovidmodule.model.StadisticsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

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
            Mockito.verify(dataObserver).onChanged(null)

            //waiting time for the api to return a response
            Thread.sleep(5000)
            Mockito.verify(dataObserver).onChanged(Mockito.refEq(stadisticsApi))
        }
    }

    @Test
    fun when_date_is_older_than_today(){

        testCoroutineRule.runBlockingTest {

            val dateGreater = StadisticsViewModelTestDateUtils().dateGreaterThanToday()
            stadisticsViewModel.getData(dateGreater)
            Mockito.verify(onMessageErrorObserver).onChanged(3)
        }
    }

    @Test
    fun when_api_returns_null(){

        testCoroutineRule.runBlockingTest {

            val dateNoData = StadisticsViewModelTestDateUtils().dateNoData()
            stadisticsViewModel.getData(dateNoData)
            Mockito.verify(dataObserver).onChanged(null)
        }
    }
}



