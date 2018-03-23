package com.lab42.sligamer.unitconversioncalculator


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.EditText
import android.widget.TextView

/**
 * Created by Justin Freres on 3/8/2018.
 * Main Activity Class
 * Lab 4-2 Unit Conversions Calculator
 * Plugin Support with kotlin_version = '1.2.21'
 */
class MainActivity : AppCompatActivity() {

    private lateinit var conversion: Conversion

    // DECLARE LAYOUT ELEMENTS
    private lateinit var inputLabel: TextView
    private lateinit var outputLabel: TextView
    private lateinit var outputMeasurement: TextView
    private lateinit var inputMeasurement: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        conversion = Conversion()
        setUpReferenceDisplay()
    }
    private fun setUpReferenceDisplay() {

        inputLabel = findViewById(R.id.feet_txtView)
        inputLabel.text = conversion.inputLabel

        outputLabel = findViewById(R.id.meters_txtView)
        outputLabel.text = conversion.outputLabel

        outputMeasurement = findViewById(R.id.output_textView)
        outputMeasurement.text = conversion.outputValue.toString()

        inputMeasurement = findViewById(R.id.input_editText)
        inputMeasurement.setText(conversion.inputValue.toString())
        inputMeasurement.addTextChangedListener(object : TextWatcher {

            // INPUT NOT A NUMBER CATCH
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    conversion.inputValue = s.toString().toDouble()

                } catch (error: NumberFormatException) {
                    conversion.inputValue = 0.0
                }
                conversion.compute()
                outputMeasurement.text = conversion.outputValue.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when {
            event!!.action == MotionEvent.ACTION_DOWN -> toggleActionBar()
            else -> return true
        }
        return false
    }

    private fun toggleActionBar() {
        val myActionBar = actionBar

        if (myActionBar != null) {
            if(myActionBar.isShowing)
            {
                myActionBar.hide()
            }
            else{
                myActionBar.show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // INFLATE MENU
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item!!.itemId

        when (id) {
            R.id.mItem_feet_meters -> {
                conversion.switchtoFeetMeters()
                resetDisplay()
                return true
            }
            R.id.mItem_inches_cent -> {
                conversion.switchtoInchesCentimeters()
                resetDisplay()
                return true
            }
            R.id.mItem_pounds_grams -> {
                conversion.switchtoPoundsGrams()
                resetDisplay()
                return true
            }
            R.id.mItem_quit -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    private fun resetDisplay(){
        inputLabel.text = conversion.inputLabel
        outputLabel.text = conversion.outputLabel

        outputMeasurement.text = conversion.outputValue.toString()
        inputMeasurement.setText(conversion.inputValue.toString())
    }
}
