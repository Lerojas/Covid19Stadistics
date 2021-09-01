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
import com.bumptech.glide.Glide
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var viewModel:StadisticsViewModel

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

        callGetData(getDate())

        viewModel.data.observe(this, {

            it?.let {
                hideLoading()
                showUiData()
                binding.titleTv.text = setDate(it.date)
                binding.confirmedCasesTv.text = "Casos confirmados: ${it.confirmed}"
                binding.cantDeceasedTv.text = "Cantidad de personas fallecidas: ${it.deaths}"
            }
        })

        viewModel.onMessageError.observe(this, {
            hideLoading()
            Toast.makeText(this, "msg: $it", Toast.LENGTH_SHORT).show()
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

    @SuppressLint("SimpleDateFormat")
    private fun getDate(): String {

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DATE, -1)

        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        return df.format(calendar.time)
    }

    @SuppressLint("SimpleDateFormat")
    private fun setDate(date: Date): String {

        val dayFormat: DateFormat = SimpleDateFormat("dd")
        val day: String = dayFormat.format(date)
        val monthFormat: DateFormat = SimpleDateFormat("MM")
        val month: String = monthFormat.format(date)
        val yearFormat: DateFormat = SimpleDateFormat("yyyy")
        val year: String = yearFormat.format(date)

        val monthString = when (month) {
            "01" -> "Enero"
            "02" -> "Febrero"
            "03" -> "Marzo"
            "04" -> "Abril"
            "05" -> "Mayo"
            "06" -> "Junio"
            "07" -> "Julio"
            "08" -> "Agosto"
            "09" -> "Septiembre"
            "10" -> "Octubre"
            "11" -> "Noviembre"
            "12" -> "Diciembre"
            else -> "Invalid month"
        }

        return "$day de $monthString de $year"
    }

    private fun showDatePickerDialog(){
        val datePicker = DatePickerFragment { day, month, year, -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {

        var monthString = month.toString()

        if(monthString.length<2){
            monthString = "0$monthString"
        }

        val dateString = "$year-$monthString-$day"
        callGetData(dateString)
    }
}