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
}