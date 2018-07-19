package com.konaire.masterdetail.models

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import com.konaire.masterdetail.util.createParcel

/**
 * Created by Evgeny Eliseyev on 14/07/2018.
 */
data class Tag(
    @SerializedName("id") val id: Long = 0L,
    @SerializedName("name") val name: String = "",
    @SerializedName("color") val color: String = ""
): Parcelable {
    companion object {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { Tag(it) }
    }

    constructor(parcel: Parcel): this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeLong(id)
        parcel?.writeString(name)
        parcel?.writeString(color)
    }

    override fun describeContents(): Int = 0
}