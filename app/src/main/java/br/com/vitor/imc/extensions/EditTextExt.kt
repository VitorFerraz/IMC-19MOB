package br.com.vitor.imc.extensions

import android.widget.EditText

fun EditText.value() = this.text.toString()
fun EditText.valueDouble() = this.text.toString().toDouble()