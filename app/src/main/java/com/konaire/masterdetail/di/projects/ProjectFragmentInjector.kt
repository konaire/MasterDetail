package com.konaire.masterdetail.di.projects

import com.konaire.masterdetail.ui.projects.ProjectDetailFragment
import com.konaire.masterdetail.ui.projects.ProjectsListFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Evgeny Eliseyev on 25/04/2018.
 */
@Module
interface ProjectFragmentInjector {
    @ContributesAndroidInjector(modules = [ProjectsListModule::class])
    fun provideProjectsListFragment(): ProjectsListFragment

    @ContributesAndroidInjector(modules = [ProjectDetailModule::class])
    fun provideProjectDetailFragment(): ProjectDetailFragment
}