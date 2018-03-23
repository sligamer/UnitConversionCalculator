package com.lab42.sligamer.unitconversioncalculator

/**
 * Created by Justin Freres on 3/8/2018.
 * Conversions Class
 * Lab 4-2 Unit Conversions Calculator
 * Plugin Support with kotlin_version = '1.2.21'
 */
class Conversion {

    companion object {
        const val FEET = 1
        const val INCHES = 2
        const val POUNDS = 3
        const val METERS_PER_FEET = 0.3048
        const val CENTIMETERS_PER_INCH = 2.56
        const val GRAMS_PER_LB = 453.592

        private var isA: Int? = null
    }

    var inputValue: Double? = 0.0
    var inputLabel: String? = null

    var outputLabel: String? = null
    var outputValue: Double? = 0.0

    init {
        isA = FEET
        inputLabel = "FEET"
        outputLabel = "METERS"
        inputValue = 0.0
        outputValue = 0.0
    }

    fun switchtoFeetMeters()
    {
        isA = FEET
        inputLabel = "FEET"
        outputLabel = "METERS"
        compute()
    }

    fun switchtoInchesCentimeters(){

        isA = INCHES
        inputLabel = "INCHES"
        outputLabel = "CENTIMETERS"
        compute()
    }

    fun switchtoPoundsGrams(){

        isA = POUNDS
        inputLabel = "POUNDS"
        outputLabel = "GRAMS"
        compute()
    }

    fun compute()
    {
        when(isA) {
            FEET -> {
                outputValue = inputValue!!.times(METERS_PER_FEET)
            }
            INCHES -> {
                outputValue = inputValue!!.times(CENTIMETERS_PER_INCH)
            }
            POUNDS -> {
                outputValue = inputValue!!.times(GRAMS_PER_LB)
            }
        }
    }
}