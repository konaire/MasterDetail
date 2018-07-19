package com.konaire.masterdetail.ui.projects.adapters

import com.konaire.masterdetail.models.Project
import com.konaire.masterdetail.ui.list.BaseAdapter
import com.konaire.masterdetail.ui.list.ListItemType
import com.konaire.masterdetail.ui.list.OnItemClickedListener

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