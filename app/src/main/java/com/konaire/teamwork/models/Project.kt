package com.konaire.teamwork.models

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan

import com.google.gson.annotations.SerializedName

import com.konaire.teamwork.ui.list.ListItemType
import com.konaire.teamwork.ui.list.ViewType
import com.konaire.teamwork.util.createParcel
import com.konaire.teamwork.util.formatAsDate

/**
 * Created by Evgeny Eliseyev on 24/04/2018.
 */
data class Project(
    @SerializedName("id") val id: Long = 0L,
    @SerializedName("name") val name: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("status") val status: String = "",
    @SerializedName("created-on") private val createdDate: String = "",
    @SerializedName("startDate") private val startDate: String = "",
    @SerializedName("endDate") private val endDate: String = "",
    @SerializedName("logo") val logo: String = "",
    @SerializedName("starred") val starred: Boolean = false,
    @SerializedName("company") val company: Company = Company(),
    @SerializedName("tags") val tags: MutableList<Tag> = ArrayList()
): Parcelable, ViewType {
    companion object {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { Project(it) }
    }

    constructor(parcel: Parcel): this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt() != 0,
        parcel.readParcelable(Company.CREATOR::class.java.classLoader),
        parcel.createTypedArrayList(Tag.CREATOR)
    )

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeLong(id)
        parcel?.writeString(name)
        parcel?.writeString(description)
        parcel?.writeString(status)
        parcel?.writeString(createdDate)
        parcel?.writeString(startDate)
        parcel?.writeString(endDate)
        parcel?.writeString(logo)
        parcel?.writeInt(if (starred) 1 else 0)
        parcel?.writeParcelable(company, 0)
        parcel?.writeTypedList(tags)
    }

    override fun describeContents(): Int = 0

    override fun getViewType(): Int = ListItemType.PROJECT.ordinal

    fun formattedCreatedDate(): String = createdDate.formatAsDate(true)

    fun formattedStartDate(): String = startDate.formatAsDate(false)

    fun formattedEndDate(): String = endDate.formatAsDate(false)

    @SuppressLint("Range")
    fun formattedTags(): CharSequence {
        val builder = StringBuilder()
        var separator = ""
        for (tag in tags) {
            builder.append(separator).append(tag.name)
            separator = " "
        }

        var lastIndex = 0
        val spannable = SpannableString(builder.toString())
        for (tag in tags) {
            spannable.setSpan(
                BackgroundColorSpan(Color.parseColor(tag.color)),
                lastIndex, lastIndex + tag.name.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            lastIndex += tag.name.length + 1
        }

        return spannable
    }

    class Status {
        companion object {
            const val ALL = "ALL"
            const val ACTIVE = "ACTIVE"
            const val ARCHIVED = "ARCHIVED"
        }
    }
}

data class ProjectsResponse(
    @SerializedName("projects") val projects: MutableList<Project> = ArrayList()
): BaseResponse()

data class ProjectResponse(
    @SerializedName("project") val project: Project = Project()
): BaseResponse()