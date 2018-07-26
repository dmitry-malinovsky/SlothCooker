package com.example.dmalinovschi.utils

import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import kotlin.reflect.KFunction1


class ValidatingTextWatcher(var validator: KFunction1<@ParameterName(name = "layout") TextInputLayout, Boolean>, var textInputTitle: TextInputLayout) : TextWatcher {

    override fun afterTextChanged(s: Editable) {
        validator(textInputTitle)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { /* Don't care */
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { /* Don't care */
    }
}