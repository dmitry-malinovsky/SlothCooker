package com.example.dmalinovschi.utils

import android.support.design.widget.TextInputLayout
import com.example.dmalinovschi.adapters.TextInputLayoutAdapter

class InputValidator(var inputAdapter: TextInputLayoutAdapter) {

    fun validateTitle(layout: TextInputLayout): Boolean {
        val title = inputAdapter.getText(layout)
        if (title.isEmpty()) {
            layout.error = "Title can't be empty"
            return false
        } else if (title.length > 30) {
            layout.error = "Title can't be longer than 50"
            return false
        } else {
            layout.error = null
            return true
        }
    }

    fun validateMacronutrients(layout: TextInputLayout): Boolean {
        var protein = inputAdapter.getText(layout)

        if (protein.isEmpty()) {
            layout.error = null
            return true
        } else {
            if (Integer.parseInt(protein) > 100) {
                layout.error = "It cant be that much"
                return false
            } else {
                layout.error = null
                return true
            }
        }
    }

    fun validateCcal(layout: TextInputLayout): Boolean {
        val ccal = inputAdapter.getText(layout)
        if (ccal.isEmpty()) {
            layout.error = null
            return true
        } else {
            if (Integer.parseInt(ccal) > 500) {
                layout.error = "It cant be that much"
                return false
            } else {
                layout.error = null
                return true
            }
        }
    }

    fun validateAction(layout: TextInputLayout): Boolean {
        val action = inputAdapter.getText(layout)
        if (action.isEmpty()) {
            layout.error = "Action can't be empty"
            return false
        } else if (action.length > 30) {
            layout.error = "Action can't be longer than 50"
            return false
        } else {
            layout.error = null
            return true
        }
    }

    fun validateIngredient(layout: TextInputLayout): Boolean {
//        add validation with select from db
        return true
    }

    fun validateWeight(layout: TextInputLayout): Boolean {
        val weight = inputAdapter.getText(layout)
        if (weight.isEmpty()) {
            layout.error = null
            return true
        } else {
            if (Integer.parseInt(weight) > 500) {
                layout.error = "It cant be that much"
                return false
            } else {
                layout.error = null
                return true
            }
        }
    }

    fun validateMeasurement(layout: TextInputLayout): Boolean {
        val measurement = inputAdapter.getText(layout)
        if (measurement.isEmpty()) {
            layout.error = "Measurement can't be empty"
            return false
        } else if (measurement.length > 30) {
            layout.error = "Measurement can't be longer than 50"
            return false
        } else {
            layout.error = null
            return true
        }
    }
}