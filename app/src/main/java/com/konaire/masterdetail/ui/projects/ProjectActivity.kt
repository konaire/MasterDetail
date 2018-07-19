package com.konaire.masterdetail.ui.projects

import android.os.Bundle

import com.konaire.masterdetail.R
import com.konaire.masterdetail.ui.BaseActivity

import kotlinx.android.synthetic.main.activity_project.*

/**
 * Created by Evgeny Eliseyev on 23/04/2018.
 */
class ProjectActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            navigation.showFragment(this, ProjectsListFragment.create(), true)
        }
    }
}