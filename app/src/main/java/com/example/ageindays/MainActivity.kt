package com.example.ageindays

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {

            // Ensuring button click is working with creating a Toast on button
            // Toast.makeText(this, "Button was clicked", Toast.LENGTH_LONG).show()

            btnDatePicker.setOnClickListener { view->
                clickDatePicker(view)
            }
        }
        
    }
    
    
    fun clickDatePicker(view: View) {

        // creating variable for Calendar object
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->


                Toast.makeText(this, "The chosen year is $selectedYear, and the day is $selectedDayOfMonth",
                    Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                tvSelectedDate.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate!!.time / 60000

                val selectedDateInDays = selectedDateInMinutes / 1440

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToMinutes = currentDate!!.time / 60000

                val currentDateToDays = currentDateToMinutes / 1440

                val differenceInDays = currentDateToDays - selectedDateInDays

                tvSelectedDateInDays.text = differenceInDays.toString()

            }
        ,year
        ,month
        ,day)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}