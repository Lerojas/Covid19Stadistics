package com.androidavanzado.covid19stadistics.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import au.com.carsales.basemodule.extension.visible
import au.com.carsales.basemodule.util.loadImageAsGif
import au.com.carsales.basemodule.util.loadImageWithoutPlaceholder
import com.androidavanzado.covid19stadistics.R
import com.androidavanzado.covid19stadistics.appComponent
import com.androidavanzado.covid19stadistics.databinding.ActivityHomeBinding
import com.androidavanzado.covid19stadistics.ui.viewmodel.*
import com.androidavanzado.covid19stadistics.usecase.ValidateDateSelected
import com.androidavanzado.covid19stadistics.util.DateTextFormat
import com.androidavanzado.covid19stadistics.util.DateYesterday
import java.text.NumberFormat
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel : StadisticsViewModel

    private val dateYesterday = DateYesterday()
    private val dateTextFormat = DateTextFormat()
    private val validateDateSelected = ValidateDateSelected()

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent().inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewModel = getViewModel(viewModelFactory)
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
                binding.confirmedCasesTv.text = "${getString(R.string.messageConfirmedCases)} $confirmedCases"

                val deathsCases = NumberFormat.getInstance().format(it.deaths)
                binding.cantDeceasedTv.text = "${getString(R.string.messageDeathsCases)} $deathsCases"
            }
        })

        viewModel.onMessageError.observe(this, {responseFailure ->
            hideLoading()
            val messageFailure =
                when (responseFailure) {
                    1 -> {
                        getString(R.string.messageNoData)
                    }
                    3 -> {
                        getString(R.string.messageDateCanNotSelected)
                    }
                    else -> {
                        getString(R.string.messageFailure)
                    }
                }

            showDialog(messageFailure)
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
        binding.imageViewLoading.visible()
        binding.imageViewLoading.loadImageAsGif(R.raw.loading)
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
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {

        var monthString = (month + 1).toString()

        if(monthString.length<2){
            monthString = "0$monthString"
        }

        val dateString = "$year-$monthString-$day"

        callGetData(dateString)
    }

    private fun showDialog(message: String){

        val dialog = AlertDialog.Builder(this)
            .setTitle(getString(R.string.tittleAlertDialog))
            .setMessage(message)
            .setPositiveButton(getString(R.string.textPossitiveButton)) { view, _ ->
                view.dismiss()
                callGetData(dateYesterday())
            }
            .setCancelable(false)
            .create()

        dialog.show()
    }
}