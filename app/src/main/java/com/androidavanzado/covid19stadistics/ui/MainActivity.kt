package com.androidavanzado.covid19stadistics.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.androidavanzado.covid19stadistics.databinding.ActivityMainBinding
import com.androidavanzado.covid19stadistics.di.Injector
import com.androidavanzado.covid19stadistics.ui.viewmodel.StadisticsViewModel
import com.androidavanzado.covid19stadistics.ui.viewmodel.ViewModelFactory
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel:StadisticsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                Injector.provideRepository(), application
            )
        ).get(StadisticsViewModel::class.java)

        setupUi()
    }

    private fun setupUi(){

        viewModel.getData(getDate())
        viewModel.data.observe(this, androidx.lifecycle.Observer {

            it?.let {
                binding.titleTv.text = setDate(it.date)
                binding.confirmedCasesTv.text = "Casos confirmados: ${it.confirmed}"
                binding.cantDeceasedTv.text = "Cantidad de personas fallecidas: ${it.deaths}"
            }
        })

        viewModel.onMessageError.observe(this, {
            Toast.makeText(this, "msg: $it", Toast.LENGTH_SHORT).show()
        })

        binding.selectDateBtn.setOnClickListener {

        }
    }

    private fun getDate() : String {

        var calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DATE, -1)

        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val dateString: String = df.format(calendar.time)
        return dateString
    }

    private fun setDate(date: Date) : String {

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

        val dateString = "$day de $monthString de $year"

        return dateString
    }
}