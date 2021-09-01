package com.androidavanzado.covid19stadistics.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.androidavanzado.covid19stadistics.R
import com.androidavanzado.covid19stadistics.databinding.ActivityHomeBinding
import com.androidavanzado.covid19stadistics.di.Injector
import com.androidavanzado.covid19stadistics.ui.viewmodel.StadisticsViewModel
import com.androidavanzado.covid19stadistics.ui.viewmodel.ViewModelFactory
import com.androidavanzado.covid19stadistics.usecase.ValidateDateSelected
import com.androidavanzado.covid19stadistics.util.DateTextFormat
import com.androidavanzado.covid19stadistics.util.DateYesterday
import com.androidavanzado.covid19stadistics.util.MonthToNameMonth
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.Date

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var viewModel:StadisticsViewModel

    private val dateYesterday = DateYesterday()
    private val monthToNameMonth = MonthToNameMonth()
    private val dateTextFormat = DateTextFormat(monthToNameMonth)
    private val validateDateSelected = ValidateDateSelected()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                Injector.provideRepository(), application
            )
        ).get(StadisticsViewModel::class.java)

        setUpUi()
    }

    @SuppressLint("SetTextI18n")
    private fun setUpUi(){

        callGetData(dateYesterday())

        viewModel.data.observe(this, {

            it?.let {
                hideLoading()
                showUiData()
                binding.titleTv.text = dateTextFormat(it.date)

                val confirmedCases = NumberFormat.getInstance().format(it.confirmed)
                binding.confirmedCasesTv.text = "Casos confirmados: $confirmedCases"

                val deathsCases = NumberFormat.getInstance().format(it.deaths)
                binding.cantDeceasedTv.text = "Cantidad de personas fallecidas: $deathsCases"
            }
        })

        viewModel.onMessageError.observe(this, {
            hideLoading()
            Toast.makeText(this, "msg: $it", Toast.LENGTH_SHORT).show()
            callGetData(dateYesterday())
        })

        binding.selectDateBtn.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun callGetData(dateString : String){
        showLoading()
        hideUiData()
        viewModel.getData(dateString)
    }

    private fun showLoading(){
        binding.imageViewLoading.visibility = View.VISIBLE
        Glide.with(this).asGif().load(R.raw.loading).into(binding.imageViewLoading)
    }

    private fun hideLoading(){
        binding.imageViewLoading.visibility = View.GONE
    }

    private fun showUiData(){
        binding.titleTv.visibility = View.VISIBLE
        binding.confirmedCasesTv.visibility = View.VISIBLE
        binding.cantDeceasedTv.visibility = View.VISIBLE
        binding.imageView.visibility = View.VISIBLE
        binding.selectDateBtn.visibility = View.VISIBLE
    }

    private fun hideUiData(){
        binding.titleTv.visibility = View.GONE
        binding.confirmedCasesTv.visibility = View.GONE
        binding.cantDeceasedTv.visibility = View.GONE
        binding.imageView.visibility = View.GONE
        binding.selectDateBtn.visibility = View.GONE
    }

    private fun showDatePickerDialog(){
        val datePicker = DatePickerFragment { day, month, year, -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {

        val validateDate = validateDateSelected(day, month, year)

        if(validateDate){
            var monthString = month.toString()

            if(monthString.length<2){
                monthString = "0$monthString"
            }

            val dateString = "$year-$monthString-$day"

            callGetData(dateString)
        }
        else{
            Toast.makeText(this, "No puede seleccionar una fecha igual o mayor a hoy.", Toast.LENGTH_SHORT).show()
            callGetData(dateYesterday())
        }
    }
}