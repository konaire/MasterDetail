package com.konaire.teamwork.util

import android.content.Context
import android.widget.Toast

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Created by Evgeny Eliseyev on 24/04/2018.
 */
fun String.formatAsDate(): String = try {
    val inf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz", Locale.getDefault())
    val datef = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    val date = inf.parse(this.replace("Z", "UTC"))
    datef.format(date)
} catch (e: Exception) {
    ""
}

fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()