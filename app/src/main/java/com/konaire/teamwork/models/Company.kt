package com.konaire.teamwork.models

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import com.konaire.teamwork.util.createParcel

/**
 * Created by Evgeny Eliseyev on 14/07/2018.
 */
data class Company(
    @SerializedName("id") val id: Long = 0L,
    @SerializedName("name") val name: String = ""
): Parcelable {
    companion object {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { Company(it) }
    }

    constructor(parcel: Parcel): this(
        parcel.readLong(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeLong(id)
        parcel?.writeString(name)
    }

    override fun describeContents(): Int = 0
}