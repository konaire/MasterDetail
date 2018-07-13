package com.konaire.teamwork.models

import com.google.gson.annotations.SerializedName

import com.konaire.teamwork.ui.list.ListItemType
import com.konaire.teamwork.ui.list.ViewType
import com.konaire.teamwork.util.formatAsDate

/**
 * Created by Evgeny Eliseyev on 24/04/2018.
 */
open class Project(
    @SerializedName("id") val id: Long = 0L,
    @SerializedName("name") val name: String = "",
    @SerializedName("created-on") private val createdDate: String = "",
    @SerializedName("logo") val logo: String = ""
): ViewType {
    override fun getViewType(): Int = ListItemType.PROJECT.ordinal

    fun formattedCreatedDate(): String = createdDate.formatAsDate()
}

data class ProjectResponse(
    @SerializedName("STATUS") private val status: String = "",
    @SerializedName("projects") val projects: MutableList<Project> = ArrayList()
)