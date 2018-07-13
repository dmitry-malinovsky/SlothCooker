package com.example.dmalinovschi.adapters

import android.support.design.widget.TextInputLayout

class TextInputLayoutAdapter() {
    fun getText(view: TextInputLayout): String {
        return view.editText!!.text.toString().trim()
    }
}