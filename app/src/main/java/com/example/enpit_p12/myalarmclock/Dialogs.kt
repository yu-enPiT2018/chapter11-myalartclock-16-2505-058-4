package com.example.enpit_p12.myalarmclock

import android.app.DatePickerDialog
import android.support.v7.app.AlertDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.support.v4.app.DialogFragment
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.DatePicker
import android.widget.TimePicker
import org.jetbrains.anko.toast
import org.jetbrains.anko.toast
import java.time.Month
import java.util.*

class SimpleAlertDialog :DialogFragment(){
    interface OnClickListener{
        fun onPositiveClick()
        fun onNegativeClick()
    }
    private lateinit var listener: OnClickListener
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is SimpleAlertDialog.OnClickListener){
            listener =context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(this.activity!!).apply {
            setMessage("時間になりました")
            setPositiveButton("起きる"){
                dialog, which ->
                listener.onPositiveClick()
            }
            setNegativeButton("あと五分"){

                dialog, which ->
                listener.onNegativeClick()
            }
        }
        return builder.create()
    }
}

class DatePickerFragment : DialogFragment(),
        DatePickerDialog.OnDateSetListener{
    interface  OnDateSelectedListener{
        fun onSelected(year:Int,month: Int,date: Int)
    }
    private lateinit var listener: OnDateSelectedListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is OnDateSelectedListener){
            listener = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c= Calendar.getInstance()
        val year = c.get(Calendar.YEAR)

        val month =c.get(Calendar.MONTH)
        val date= c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(context,this,year,month,date)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, date: Int) {
        listener.onSelected(year,month, date)

    }

}

class TimePickerFragment:DialogFragment(),TimePickerDialog.OnTimeSetListener{

    interface OnTimeSelectedListener{
        fun onSelected(hourOfDays: Int,minute:Int)
    }
    private lateinit var listener :OnTimeSelectedListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is TimePickerFragment.OnTimeSelectedListener){
            listener =context
        }
    }

    override fun
            onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c=Calendar.getInstance()
        val hour=c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        return TimePickerDialog(context,this,hour,minute,true)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

        listener.onSelected(hourOfDay,minute)
    }
}
