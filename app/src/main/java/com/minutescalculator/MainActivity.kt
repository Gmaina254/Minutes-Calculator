package com.minutescalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var tvTimeInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePicker: Button = findViewById(R.id.btndatepicker)

        tvSelectedDate =  findViewById(R.id.userDate)
        tvTimeInMinutes = findViewById(R.id.minutes)

        datePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val calDateDialog = DatePickerDialog(this,
//            DatePickerDialog.OnDateSetListener
            { _, selctedYear, selctedMonth, selctedDayOfMonth ->

                val pickedDate = "$selctedDayOfMonth:${selctedMonth+1}:$selctedYear"



                tvSelectedDate?.text = pickedDate

                val pcDate = SimpleDateFormat("dd:MM:yy", Locale.ENGLISH)
                val theDate = pcDate.parse(pickedDate)
                theDate?.let {
                    val selectedDateToMinutes = theDate.time / 60000

                    val currentDate = System.currentTimeMillis()
//                Toast.makeText(this, "$selectedDateToMinutes selected", Toast.LENGTH_LONG).show()
                    currentDate?.let {
                        val currentDateInMins = currentDate / 60000

                        val diffInMIns = (currentDateInMins) - (selectedDateToMinutes)

                        tvTimeInMinutes?.text = diffInMIns.toString()
                    }


                }

            },
            year,
            month,
            day
        )
        calDateDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
        calDateDialog.show()
    }


}