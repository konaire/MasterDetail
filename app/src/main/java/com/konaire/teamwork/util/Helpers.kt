package com.konaire.teamwork.util

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Created by Evgeny Eliseyev on 24/04/2018.
 */
inline fun <reified T : Parcelable> createParcel(
    crossinline createFromParcel: (Parcel) -> T?
): Parcelable.Creator<T> =
    object: Parcelable.Creator<T> {
        override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
        override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
    }

fun String.formatAsDate(fromFullFormat: Boolean): String = try {
    val inf = SimpleDateFormat(if (fromFullFormat) "yyyy-MM-dd'T'HH:mm:ssz" else "yyyyMMdd", Locale.getDefault())
    val datef = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val date = inf.parse(this.replace("Z", "UTC"))
    datef.format(date)
} catch (e: Exception) {
    ""
}

fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()