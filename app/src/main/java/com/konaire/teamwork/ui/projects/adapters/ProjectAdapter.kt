package com.konaire.teamwork.ui.projects.adapters

import com.konaire.teamwork.models.Project
import com.konaire.teamwork.ui.list.BaseAdapter
import com.konaire.teamwork.ui.list.ListItemType
import com.konaire.teamwork.ui.list.OnItemClickedListener

/**
 * Created by Evgeny Eliseyev on 24/04/2018.
 */
class ProjectAdapter(
    listener: OnItemClickedListener<Project>
): BaseAdapter<Project>(listener) {
    init {
        delegateAdapters[ListItemType.PROJECT.ordinal] = ProjectDelegateAdapter(listener)
    }
}