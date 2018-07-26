package com.example.dmalinovschi.utils

import android.text.Editable
import android.text.TextWatcher
import kotlin.reflect.KFunction0


class TextValidator(var validator: KFunction0<Boolean>) : TextWatcher {

    override fun afterTextChanged(s: Editable) {
        validator()
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { /* Don't care */
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { /* Don't care */
    }
}