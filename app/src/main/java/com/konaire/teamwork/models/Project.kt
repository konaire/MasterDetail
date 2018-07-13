package com.konaire.teamwork.models

import com.google.gson.annotations.SerializedName
import com.konaire.teamwork.ui.list.ListItemType
import com.konaire.teamwork.ui.list.ViewType

/**
 * Created by Evgeny Eliseyev on 24/04/2018.
 */
open class Project(
    @SerializedName("id") val id: Long = 0L,
    @SerializedName("name") val name: String = ""
): ViewType {
    override fun getViewType(): Int = ListItemType.PROJECT.ordinal
}

data class ProjectResponse(
    @SerializedName("STATUS") val status: String = "",
    @SerializedName("projects") val projects: MutableList<Project> = ArrayList()
)