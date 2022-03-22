package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

private const val TAG = "Main activity"
private const val INITIAL_TIP = 5

class MainActivity : AppCompatActivity() {
    private lateinit var baseET: EditText
    private lateinit var tipPercentageTagTV: TextView
    private lateinit var tipTV: TextView
    private lateinit var totalCostTV: TextView
    private lateinit var tipSB: SeekBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        baseET = findViewById(R.id.baseET)
        tipPercentageTagTV = findViewById(R.id.tipPercentTagTV)
        tipTV = findViewById(R.id.tipTV)
        totalCostTV = findViewById(R.id.totalCostTV)
        tipSB = findViewById(R.id.tipSB)

        tipSB.progress = INITIAL_TIP
        tipPercentageTagTV.text = "$INITIAL_TIP%"


        tipSB.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "On progress changed $p1%")
                tipPercentageTagTV.text = "$p1"
                makeTipPlusTotal()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })

        //object keyword is used to create anonymous class that are 1 time use classes
        //that are commonly used to implement interfaces.
        baseET.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                makeTipPlusTotal()
            }

        })
    }

    private fun makeTipPlusTotal() {
        if(baseET.text.isEmpty()){
            tipTV.text = ""
            totalCostTV.text = ""
            return
        }
        val baseCost = baseET.text.toString().toDouble()
        val tipPerc = tipSB.progress
        val totalTip = (baseCost * tipPerc) / 100
        val total = baseCost + totalTip

        tipTV.text = "%.2f".format(totalTip)
        totalCostTV.text = "%.2f".format(total)
    }
}